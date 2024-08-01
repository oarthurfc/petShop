package com.oarthurfc.PetShopDTI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oarthurfc.PetShopDTI.DTOs.PetShopDTO;
import com.oarthurfc.PetShopDTI.models.PetShop;
import com.oarthurfc.PetShopDTI.repositories.PetShopRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetShopService {

    @Autowired
    private PetShopRepository petShopRepository;

    @Transactional
    public PetShopDTO save(PetShopDTO petShopDTO) {
        PetShop petShop = new PetShop(
            petShopDTO.getNome(),
            petShopDTO.getDistancia(),
            petShopDTO.getPrecoBasePequeno(),
            petShopDTO.getPrecoBaseGrande(),
            petShopDTO.getTipoAdicional(),
            petShopDTO.getAdicional()
        );

        PetShop savedPetShop = petShopRepository.save(petShop);

        return convertToDTO(savedPetShop, LocalDate.now()); // Usando a data atual para conversão inicial
    }

    @Transactional(readOnly = true)
    public List<PetShopDTO> findAll() {
        LocalDate hoje = LocalDate.now();
        return petShopRepository.findAll().stream()
                .map(petShop -> convertToDTO(petShop, hoje))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PetShopDTO findById(Long id) {
        LocalDate hoje = LocalDate.now();
        Optional<PetShop> result = petShopRepository.findById(id);
        if (result.isPresent()) {
            return convertToDTO(result.get(), hoje);
        } else {
            throw new RuntimeException("PetShop não encontrado com id: " + id);
        }
    }

    private PetShopDTO convertToDTO(PetShop petShop, LocalDate data) {
        PetShopDTO dto = new PetShopDTO();
        dto.setNome(petShop.getNome());
        dto.setDistancia(petShop.getDistancia());
        dto.setPrecoBasePequeno(petShop.getPrecoBasePequeno());
        dto.setPrecoBaseGrande(petShop.getPrecoBaseGrande());
        dto.setTipoAdicional(petShop.getTipoAdicional());
        dto.setAdicional(petShop.getAdicional());

        boolean fimDeSemana = data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY;

        dto.setPrecoFinalPequeno(petShop.calcularPreco(petShop.getPrecoBasePequeno(), fimDeSemana));
        dto.setPrecoFinalGrande(petShop.calcularPreco(petShop.getPrecoBaseGrande(), fimDeSemana));

        return dto;
    }

    public PetShopDTO calcularMelhorPetShop(String data, int qtdPequenos, int qtdGrandes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data, formatter);

        List<PetShop> petShops = petShopRepository.findAll();

        return petShops.stream()
            .map(petShop -> {
                PetShopDTO dto = convertToDTO(petShop, date);
                double precoFinalPequeno = dto.getPrecoFinalPequeno() * qtdPequenos;
                double precoFinalGrande = dto.getPrecoFinalGrande() * qtdGrandes;
                double precoTotal = precoFinalPequeno + precoFinalGrande;

                dto.setPrecoFinalPequeno(precoFinalPequeno);
                dto.setPrecoFinalGrande(precoFinalGrande);
                dto.setPrecoTotal(precoTotal);

                return dto;
            })
            .min(Comparator.comparing(PetShopDTO::getPrecoTotal)
                .thenComparing(PetShopDTO::getDistancia))
            .orElseThrow(() -> new RuntimeException("Nenhum PetShop encontrado"));
    }
}
