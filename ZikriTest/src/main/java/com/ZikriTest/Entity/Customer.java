package com.ZikriTest.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer qty;
    private Double saldo_awal;
    private Double sisa;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;

    public void setProduct(Long id) {
    }
}
