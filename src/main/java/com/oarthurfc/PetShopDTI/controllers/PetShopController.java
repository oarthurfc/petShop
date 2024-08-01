package com.oarthurfc.PetShopDTI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oarthurfc.PetShopDTI.DTOs.PetShopDTO;
import com.oarthurfc.PetShopDTI.services.PetShopService;

import java.util.List;

@RestController
@RequestMapping("/api/petshops")
public class PetShopController {

    @Autowired
    private PetShopService petShopService;

    // Endpoint para salvar um novo PetShop
    @PostMapping
    public ResponseEntity<PetShopDTO> createPetShop(@RequestBody PetShopDTO petShopDTO) {
        try {
            PetShopDTO savedPetShop = petShopService.save(petShopDTO);
            return new ResponseEntity<>(savedPetShop, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para buscar todos os PetShops
    @GetMapping
    public ResponseEntity<List<PetShopDTO>> getAllPetShops() {
        List<PetShopDTO> petShops = petShopService.findAll();
        return new ResponseEntity<>(petShops, HttpStatus.OK);
    }

    // Endpoint para buscar um PetShop por ID
    @GetMapping("/{id}")
    public ResponseEntity<PetShopDTO> getPetShopById(@PathVariable Long id) {
        PetShopDTO petShop = petShopService.findById(id);
        if (petShop != null) {
            return new ResponseEntity<>(petShop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}