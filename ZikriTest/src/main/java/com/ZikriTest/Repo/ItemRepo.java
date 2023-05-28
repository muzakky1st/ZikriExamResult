package com.ZikriTest.Repo;

import com.ZikriTest.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Product, Long> {
}
