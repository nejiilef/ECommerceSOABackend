package com.project.ECommrce.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nomClient;
    private String prenomClient;
    private int telephoneClient;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "panier_id", referencedColumnName = "id")
    private Panier panier;
}
