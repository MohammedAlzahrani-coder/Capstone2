package com.example.capstone2.Service;


import com.example.capstone2.Model.Item;
import com.example.capstone2.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);

    }

    public void updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(item.getName());
        existingItem.setCategory(item.getCategory());
        existingItem.setDescription(item.getDescription());
        itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item getItemByName(String name) {
        return itemRepository.getItemByName(name);
    }

    public List<Item> getItemByCategory(String category) {
        return itemRepository.getItemByCategory(category);
    }


    public List<Item> getItemsSortedByValue() {
        return itemRepository.findAll()
                .stream()
                .sorted((item1, item2) -> Double.compare(item2.getValue(), item1.getValue()))
                .collect(Collectors.toList());
    }

}