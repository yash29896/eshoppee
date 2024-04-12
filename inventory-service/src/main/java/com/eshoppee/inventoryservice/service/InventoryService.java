package com.eshoppee.inventoryservice.service;

import com.eshoppee.inventoryservice.dto.InventoryRequest.InventoryRequest;
import com.eshoppee.inventoryservice.model.Inventory;
import com.eshoppee.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInInventory(String skuCode)
    {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Transactional
    public void addToInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void updateInventory(InventoryRequest inventoryRequest, Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        if(inventory != null)
        {
            inventory.setQuantity(inventoryRequest.getQuantity());
            inventoryRepository.save(inventory);
        }
    }
}
