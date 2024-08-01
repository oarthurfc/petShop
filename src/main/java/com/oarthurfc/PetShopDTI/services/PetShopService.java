package com.oarthurfc.PetShopDTI.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oarthurfc.PetShopDTI.DTOs.PetShopDTO;
import com.oarthurfc.PetShopDTI.models.PetShop;
import com.oarthurfc.PetShopDTI.repositories.PetShopRepository;

import jakarta.transaction.Transactional;

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
    
        return convertToDTO(savedPetShop);
    }
    

    public List<PetShopDTO> findAll() {
        return petShopRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PetShopDTO convertToDTO(PetShop petShop) {
        PetShopDTO dto = new PetShopDTO();
        dto.setNome(petShop.getNome());
        dto.setDistancia(petShop.getDistancia());
        dto.setPrecoBasePequeno(petShop.getPrecoBasePequeno());
        dto.setPrecoBaseGrande(petShop.getPrecoBaseGrande());
        dto.setTipoAdicional(petShop.getTipoAdicional());
        dto.setAdicional(petShop.getAdicional());

        LocalDate hoje = LocalDate.now();
        boolean fimDeSemana = hoje.getDayOfWeek() == DayOfWeek.SATURDAY || hoje.getDayOfWeek() == DayOfWeek.SUNDAY;

        dto.setPrecoFinalPequeno(petShop.calcularPreco(petShop.getPrecoBasePequeno(), fimDeSemana));
        dto.setPrecoFinalGrande(petShop.calcularPreco(petShop.getPrecoBaseGrande(), fimDeSemana));

        return dto;
    }
}
