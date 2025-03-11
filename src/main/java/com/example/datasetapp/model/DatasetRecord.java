package com.example.datasetapp.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "datasets")
@Data
public class DatasetRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataset_name", nullable = false)
    private String datasetName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json_data", nullable = false)
    private String jsonData;
}
