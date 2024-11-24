package com.project.ECommrce.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ECommrce.Entities.Article;
import com.project.ECommrce.Entities.categorie;
import com.project.ECommrce.Repositories.ArticleRepository;
import com.project.ECommrce.Repositories.CategorieRepository;

@Service
public class ArticleService implements IArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	private final String uploadDir = "uploads/";
	@Override
	public Article addArticle(Long idCategorie, MultipartFile file, String designation, float prix) throws IOException {
		 if (file.isEmpty()) {
		        throw new IllegalArgumentException("File cannot be empty");
		    }
		    // Ensure the directory exists
		    Files.createDirectories(Paths.get(uploadDir));

		    // Save the file to the directory
		    Path copyLocation = Paths.get(uploadDir + file.getOriginalFilename());
		    Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		    categorie c=this.categorieRepository.findById(idCategorie).orElseThrow();
		    Article a=new Article();
		    a.setCategorie(c);
		    a.setDesignation(designation);
		    a.setNomImage(file.getOriginalFilename());
		    a.setPathImage(copyLocation.toString());
		    a.setTypeImage(file.getContentType());
		    a.setPrix(prix);
		    return this.articleRepository.save(a);
	}

	@Override
	public Article getArticleById(Long id) {
		// TODO Auto-generated method stub
		return this.articleRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return this.articleRepository.findAll();
	}

	@Override
	public Article updateArticle(Long articleId, Long categorieId, MultipartFile file, String designation, float prix)
			throws IOException {
		Article a = this.articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article with id " + articleId + " not found"));
		categorie cat = this.categorieRepository.findById(categorieId).orElseThrow(() -> new RuntimeException("Category with id " + categorieId + " not found"));
		if (file.isEmpty()) {
	        throw new IllegalArgumentException("File or name cannot be empty");
	    }
	    // Ensure the directory exists
	    Files.createDirectories(Paths.get(uploadDir));

	    // Save the file to the directory
	    Path copyLocation = Paths.get(uploadDir + file.getOriginalFilename());
	    Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
	    a.setCategorie(cat);
	    a.setDesignation(designation);
	    a.setNomImage(file.getOriginalFilename());
	    a.setPathImage(copyLocation.toString());
	    a.setTypeImage(file.getContentType());
	    a.setPrix(prix);
		return this.articleRepository.save(a);
	}

	@Override
	public void deleteArticle(Long id) {
		this.articleRepository.delete(this.articleRepository.findById(id).orElseThrow());
	}

	@Override
	public List<Article> getAllArticlesByCategorie(Long idCategorie) {
		return this.articleRepository.findByCategorieId(idCategorie);
	}

}
