package com.project.ECommrce.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ECommrce.Entities.Panier;
import com.project.ECommrce.Services.IPanierService;

@RestController
@RequestMapping("/api")
public class PanierController {
	
	@Autowired
	private IPanierService panierService;
	
	@PostMapping(value="/panier")
	public Panier createPanier() {
		return this.panierService.createPanier();
	}
	@GetMapping("/panier/{id}") 
	public ResponseEntity<Panier> getPanierById(@PathVariable Long id) { 
		Panier p=this.panierService.getPanierById(id);
		return ResponseEntity.ok(p); 
	}
	@GetMapping("/panier") 
	public List<Panier> getAllPaniers() { 
		
		return this.panierService.getAllPaniers(); 
	}
	@DeleteMapping("/panier/{id}")
	 public ResponseEntity<String> deletePanier(@PathVariable(value="id") Long id){
		 this.panierService.deletePanier(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Panier deleted successfully");
	 }
	@PostMapping(value="/panier/{idPanier}/{idArticle}")
	public Panier addArticleToPanier(@PathVariable(value="idPanier") Long idPanier,@PathVariable(value="idArticle") Long idArticle) {
		return this.panierService.addArticleToPanier(idPanier, idArticle);
	}
	@DeleteMapping("/panier/{idPanier}/{idArticle}")
	 public Panier deleteArticleFromPanier(@PathVariable(value="idPanier") Long idPanier,@PathVariable(value="idArticle") Long idArticle){
		 return this.panierService.deleteArticleFromPanier(idArticle, idPanier);
	 }
	
}
