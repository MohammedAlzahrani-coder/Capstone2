package com.example.capstone2.Controller;

import com.example.capstone2.Model.Item;
import com.example.capstone2.Service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/get")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        itemService.createItem(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item item) {
        itemService.updateItem(id, item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get_item_by_id/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/get_by_name/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable String name) {
        Item item = itemService.getItemByName(name);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/get_by_category/{category}")
    public ResponseEntity<List<Item>> getItemByCategory(@PathVariable String category) {
        List<Item> items = itemService.getItemByCategory(category);
        return ResponseEntity.ok(items);
    }


    @GetMapping("/sorted-by-value")
    public ResponseEntity<List<Item>> getItemsSortedByValue() {
        List<Item> items = itemService.getItemsSortedByValue();
        return ResponseEntity.ok(items);
    }




}
