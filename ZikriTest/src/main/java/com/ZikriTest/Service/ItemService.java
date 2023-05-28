package com.ZikriTest.Service;

import com.ZikriTest.Entity.Customer;
import com.ZikriTest.Entity.Product;
import com.ZikriTest.Repo.CustomerRepo;
import com.ZikriTest.Repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ItemService {
   @Autowired
   private ItemRepo itemRepo;

   @Autowired
   private CustomerRepo customerRepo;

    public Product itemCreate(Product product){
        return itemRepo.save(product);
    }

    public List<Product> readAllItem(){
        return itemRepo.findAll();
    }

    public Product updateItem(Long id, Product dto) {
        Optional<Product> optionalItem = itemRepo.findById(id);

        if (optionalItem.isPresent()) {
            Product item = optionalItem.get();

            item.setProduct_name(dto.getProduct_name());
            item.setPrice(dto.getPrice());
            item.setStock(dto.getStock());

            itemRepo.save(item);
            System.out.println("Product dengan ID " + id + " berhasil di update");
        } else {
            System.out.println("Product ID " + id + " tidak ditemukan");
        }
        return dto;
    }

        public void hapusItem (Long id){
            Optional<Product> itemOptional = itemRepo.findById(id);

            if (itemOptional.isPresent()) {
                itemRepo.deleteById(id);
                System.out.println("Produk ID " + id + " berhasil di hapus");
            } else {
                System.out.println("Produk ID " + id + " tiddak ditemukan");
            }
        }
    }
