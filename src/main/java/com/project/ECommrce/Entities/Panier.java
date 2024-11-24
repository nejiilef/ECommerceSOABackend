package com.project.ECommrce.Entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany
    @JoinTable(name = "panier_article",joinColumns = @JoinColumn(name = "panier_id"),inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
	private float prixTotale;
    
}
