package com.oarthurfc.PetShopDTI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oarthurfc.PetShopDTI.models.PetShop;

@Repository
public interface PetShopRepository extends JpaRepository<PetShop, Long> {
        
}
