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
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private  ItemRepo itemRepo;

    public String newCustomer(Customer customer, Long id){
        Double sisa = null;

        String result = null;

       Optional<Product> listProduct = itemRepo.findById(id);

        if (listProduct.isPresent()){
            Product item = listProduct.get();
            Double totalHarga = customer.getQty() * item.getPrice();
            if (item.getStock() >= customer.getQty() && customer.getSaldo_awal() >= totalHarga){
                Customer transCustomer = new Customer();

                transCustomer.setName(customer.getName());
                transCustomer.setSaldo_awal(customer.getSaldo_awal());
                transCustomer.setQty(customer.getQty());
                customerRepo.save(transCustomer);

                transCustomer.setQty(customer.getQty());
                sisa = customer.getSaldo_awal() - (item.getPrice() * customer.getQty());
                transCustomer.setSisa(sisa);
                transCustomer.setProduct(item.getId());
                customerRepo.save(transCustomer);

                item.setStock(item.getStock() - customer.getQty());
                itemRepo.save(item);
                String msg1 = "<tr> User dengan nama : "+ customer.getName() + " berhasil order";
                String msg2 = "<tr> Jumlah pesanan " + customer.getQty();
                String msg3 = "<tr> Total Harga : " + customer.getQty() * item.getPrice();
                String msg4 = "<tr> Nama Item : " + item.getProduct_name();
                result = msg1 + msg2 + msg3 + msg4;
                return result;
            }else {
                result = "Stok produk tidak mencukupi atau saldo tidak mencukupi";
                return result;
            }
        }else {
           result = "Produk dengan ID " + id + " tidak ditemukan";
           return result;
        }
    }

    public List<Customer> readAllCustomer() {
        return customerRepo.findAll();
    }

    public void deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepo.findById(id);

        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();

            Product item = customer.getProduct();

            //kembalikan stock produk
             item.setStock(item.getStock() - customer.getQty());
             itemRepo.save(item);

             customerRepo.deleteById(id);
            System.out.println("Customer ID : " + id + " berhasil di hapus");
        }else{
            System.out.println("Customer ID : " + id + " tidak ditemukan");
        }
    }
}
