package com.project.ECommrce.Services;

import java.util.List;

import com.project.ECommrce.Entities.Panier;

public interface IPanierService {

	Panier createPanier();
	Panier getPanierById(Long id);
	Panier addArticleToPanier(Long idPanier,Long idArticle);
	Panier deleteArticleFromPanier(Long idArticle,Long idPanier);
	void calculPrixTotale(Long idPanier);
	void deletePanier(Long idPanier);
	List<Panier> getAllPaniers();
}
