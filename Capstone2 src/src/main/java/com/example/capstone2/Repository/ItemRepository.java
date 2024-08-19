package com.example.capstone2.Repository;

import com.example.capstone2.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {



    Item getItemByName(String itemName);

    List<Item> getItemByCategory(String itemCategory);

}
