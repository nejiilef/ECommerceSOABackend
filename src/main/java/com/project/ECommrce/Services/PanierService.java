package com.project.ECommrce.Services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ECommrce.Entities.Article;
import com.project.ECommrce.Entities.Panier;
import com.project.ECommrce.Repositories.ArticleRepository;
import com.project.ECommrce.Repositories.PanierRepository;

@Service
public class PanierService implements IPanierService{

	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private ArticleRepository articleRepository;
	@Override
	public Panier getPanierById(Long id) {
		// TODO Auto-generated method stub
		return this.panierRepository.findById(id).orElseThrow();
	}

	@Override
	public Panier addArticleToPanier(Long idPanier, Long idArticle) {
		Article a = this.articleRepository.findById(idArticle).orElseThrow(() -> new RuntimeException("Article with id " + idArticle + " not found"));
		Panier p=this.panierRepository.findById(idPanier).orElseThrow(() -> new RuntimeException("Panier with id " + idPanier + " not found"));
		Set<Article> articles=p.getArticles();
		articles.add(a);
		p.setArticles(articles);
		this.calculPrixTotale(idPanier);
		return this.panierRepository.save(p);
	}

	@Override
	public Panier deleteArticleFromPanier(Long idArticle, Long idPanier) {
		Article a = this.articleRepository.findById(idArticle).orElseThrow(() -> new RuntimeException("Article with id " + idArticle + " not found"));
		Panier p=this.panierRepository.findById(idPanier).orElseThrow(() -> new RuntimeException("Panier with id " + idPanier + " not found"));
		Set<Article> articles=p.getArticles();
		articles.remove(a);
		p.setArticles(articles);
		this.calculPrixTotale(idPanier);
		return this.panierRepository.save(p);
	
	}

	@Override
	public void calculPrixTotale(Long idPanier) {
		Panier p=this.panierRepository.findById(idPanier).orElseThrow(() -> new RuntimeException("Panier with id " + idPanier + " not found"));
		float prix=0;
		for(Article a:p.getArticles()) {
			prix+=a.getPrix();
		}
		p.setPrixTotale(prix);
		this.panierRepository.save(p);
	}

	@Override
	public Panier createPanier() {
		Panier p=new Panier();
		return this.panierRepository.save(p);
	}

	@Override
	public void deletePanier(Long idPanier) {
		this.panierRepository.delete(this.panierRepository.findById(idPanier).orElseThrow());
	}

	@Override
	public List<Panier> getAllPaniers() {
		// TODO Auto-generated method stub
		return this.panierRepository.findAll();
	}

}
