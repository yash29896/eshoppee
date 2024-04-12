package com.eshoppee.inventoryservice.controller;

import com.eshoppee.inventoryservice.dto.InventoryRequest.InventoryRequest;
import com.eshoppee.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInInventory(@PathVariable("sku-code") String skuCode)
    {
        return inventoryService.isInInventory(skuCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryCreateRequest)
    {
        inventoryService.addToInventory(inventoryCreateRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody InventoryRequest inventoryCreateRequest, @PathVariable("id") Long id)
    {
        inventoryService.updateInventory(inventoryCreateRequest, id);
    }

}
