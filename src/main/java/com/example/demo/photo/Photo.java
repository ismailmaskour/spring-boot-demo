package com.example.demo.photo;

import com.example.demo.utils.Constant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Photo")
@Table(name = "photo", schema = Constant.SCHEMA)
public class Photo {

    @Id
    @SequenceGenerator(name = "photo_sequence", sequenceName = "photo_sequence", schema = Constant.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_sequence")
    @Column(name = "identifiant", updatable = false)
    private Long identifiant;
    
    private String name;
    
    private Long size;
    
    private String extension;

    @Lob
    private String data;

}
