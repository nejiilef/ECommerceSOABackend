package com.project.ECommrce.Services;

import java.util.List;

import com.project.ECommrce.Entities.Commande;

public interface ICommandeService {
	Commande passerCommande(Long idPanier,Commande c);
	List<Commande> getAllCommandes();
	Commande getCommandeById(Long idCommande);
	Commande updateCommande(Long idCommande,Long idPanier,Commande c);
	void deleteCommande(Long idCommande);
}
