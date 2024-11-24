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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ECommrce.Entities.Article;
import com.project.ECommrce.Services.IArticleService;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired 
	private IArticleService articleService;
	
	@PostMapping("/article/{idCategorie}")
	public ResponseEntity<String> addArticle(
			@PathVariable(value="idCategorie") Long id,
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("designation") String designation,
	        @RequestParam("prix") float prix) {

	    try {
	        Article a = this.articleService.addArticle(id, file, designation, prix);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Article created successfully: " + a.getDesignation());
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the full stack trace
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Article");
	    }
	}
	@GetMapping("/article/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable Long articleId) {
        Article a = this.articleService.getArticleById(articleId); 
        return ResponseEntity.ok()
            .body(a);
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
	 @GetMapping("/articleImage/{articleId}")
	    public ResponseEntity<?> getArticleImage(@PathVariable Long articleId) {
	        Article a = this.articleService.getArticleById(articleId); 
	        Path path = Paths.get(a.getPathImage()); 
	        Resource resource;
	        try { 
	            resource = new UrlResource(path.toUri());
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la récupération du photo", e);
	        }
	        
	        return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + a.getNomImage() + "\"")
	            .header(HttpHeaders.CONTENT_TYPE, determineContentType(a.getNomImage())) // Spécifiez le type de contenu
	            .body(resource);
	    }
	 @GetMapping("/articles")
	    public ResponseEntity<List<Article>> getArticles() {
	        List<Article> a= this.articleService.getAllArticles();
	        return ResponseEntity.ok(a);
	    }
	 @GetMapping("/articles/{idCategorie}")
	    public ResponseEntity<List<Article>> getArticlesByCategorie(@PathVariable(value="idCategorie") Long id) {
	        List<Article> a= this.articleService.getAllArticlesByCategorie(id);
	        return ResponseEntity.ok(a);
	    }
	 @DeleteMapping("/article/{id}")
	 public ResponseEntity<String> deleteArticle(@PathVariable(value="id") Long id){
		 this.articleService.deleteArticle(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Article deleted successfully");
	 }
	 @PutMapping("/article/{id}/{idCategorie}")
		public ResponseEntity<Article> updateArticle(@PathVariable(value="id") Long id,@PathVariable(value="idCategorie") Long idCategorie,@RequestParam("file") MultipartFile file,
		        @RequestParam("designation") String designation,
		        @RequestParam("prix") float prix) throws IOException{
			Article a=this.articleService.updateArticle(id, idCategorie, file, designation, prix);
			return ResponseEntity.status(HttpStatus.OK).body(a);
			
		}

}
