package com.ZikriTest.Controller;

import com.ZikriTest.Entity.Customer;
import com.ZikriTest.Entity.Product;
import com.ZikriTest.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/new-item")
    public Product newItem(@RequestBody Product product){
       return itemService.itemCreate(product);
    }

    @GetMapping("/get-all-item")
    public List<Product> getAllItem()throws Exception{
        return itemService.readAllItem();
    }

    @PutMapping("/update-item/{id}")
    public Product UpdateItem(@RequestBody Product product, @PathVariable("id") Long id){
        return itemService.updateItem(id, product);
    }

    @DeleteMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        itemService.hapusItem(id);
        return "Delete Success!";
    }
}
