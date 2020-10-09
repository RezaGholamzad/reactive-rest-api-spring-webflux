package com.dzone.reactiverestapis.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Table
public class Product implements Serializable {
    @PrimaryKey
    private Integer id;
    private String title;
}
