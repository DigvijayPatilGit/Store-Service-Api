package com.example.Store_app_Rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;
    private String ownerName;
    private String location;
    private double revenue;



}
