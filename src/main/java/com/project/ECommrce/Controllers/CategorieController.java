package com.project.ECommrce.Controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ECommrce.DTO.CategorieDTO;
import com.project.ECommrce.Entities.categorie;
import com.project.ECommrce.Services.ICategorieService;




@RestController
@RequestMapping("/api")
public class CategorieController {

	@Autowired 
	private ICategorieService categorieService;
	
	@PostMapping("/categorie")
	public ResponseEntity<String> addCategorie(
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("nom") String nom) {

	    try {
	        categorie cat = categorieService.addCategorie(file, nom);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Categorie created successfully: " + cat.getNom());
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the full stack trace
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create categorie");
	    }
	}
	@PutMapping("/categorie/{id}")
	public ResponseEntity<categorie> updateCategorie(@PathVariable(value="id") Long id,@RequestParam("file") MultipartFile file,
	        @RequestParam("nom") String nom) throws IOException{
		categorie cat=this.categorieService.updateCategorie( id,file,nom);
		return ResponseEntity.status(HttpStatus.OK).body(cat);
		
	}

	 @GetMapping("/categorieImage/{categorieId}")
	    public ResponseEntity<?> getCategorieImage(@PathVariable Long categorieId) {
	        categorie cat = this.categorieService.getCategorieById(categorieId); 
	        Path path = Paths.get(cat.getPathImage()); 
	        Resource resource;
	        try { 
	            resource = new UrlResource(path.toUri());
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la récupération du photo", e);
	        }
	        
	        return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + cat.getNomImage() + "\"")
	            .header(HttpHeaders.CONTENT_TYPE, determineContentType(cat.getNomImage())) // Spécifiez le type de contenu
	            .body(resource);
	    }
	 @GetMapping("/categorie/{categorieId}")
	    public ResponseEntity<?> getCategorie(@PathVariable Long categorieId) {
	        categorie cat = this.categorieService.getCategorieById(categorieId); 
	       
	        
	        return ResponseEntity.ok()
	            .body(cat);
	    }
	 
	 private String determineContentType(String fileName) {
		    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
		        return "image/jpeg";
		    } else if (fileName.endsWith(".png")) {
		        return "image/png";
		    } else if (fileName.endsWith(".gif")) {
		        return "image/gif";
		    }
		    // Default to binary data if the type cannot be determined
		    return "application/octet-stream";
		}
	 @DeleteMapping("/categorie/{id}")
	 public ResponseEntity<String> deleteCategorie(@PathVariable(value="id") Long id){
		 this.categorieService.deleteCategorie(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Categorie deleted successfully");
	 }
	 @GetMapping("/categories")
	    public ResponseEntity<List<categorie>> getCategories() {
	        List<categorie> c = this.categorieService.getAllCategories();
	        return ResponseEntity.ok(c);
	    }
	    
}
