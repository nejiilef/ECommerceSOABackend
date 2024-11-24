package com.project.ECommrce.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    private String nom;
    private String nomImage;
	private String typeImage;
	private String pathImage;
	
	public categorie() {}

	public categorie(String nom,String nomImage, String typeImage, String pathImage) {
        this.nomImage = nomImage;
        this.typeImage = typeImage;
        this.pathImage = pathImage;
        this.nom = nom;
    }
}
