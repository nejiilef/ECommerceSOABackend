package com.project.ECommrce.Entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    private String designation;
    private String nomImage;
    private String typeImage;
	private String pathImage;
	private float prix;
	@ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private categorie categorie;
    
}
