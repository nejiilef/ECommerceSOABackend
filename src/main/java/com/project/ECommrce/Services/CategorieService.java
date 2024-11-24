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

import com.project.ECommrce.Entities.categorie;
import com.project.ECommrce.Repositories.CategorieRepository;

@Service
public class CategorieService implements ICategorieService{

	@Autowired
	private CategorieRepository categorieRepository;
	private final String uploadDir = "uploads/";
	
	@Override
	public categorie addCategorie(MultipartFile file, String nom) throws IOException {
	    if (file.isEmpty() || nom == null || nom.trim().isEmpty()) {
	        throw new IllegalArgumentException("File or name cannot be empty");
	    }
	    // Ensure the directory exists
	    Files.createDirectories(Paths.get(uploadDir));

	    // Save the file to the directory
	    Path copyLocation = Paths.get(uploadDir + file.getOriginalFilename());
	    Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

	    // Create the categorie object
	    categorie cat = new categorie(nom, file.getOriginalFilename(), file.getContentType(), copyLocation.toString());
	    return categorieRepository.save(cat);
	}


	@Override
	public List<categorie> getAllCategories() {
		// TODO Auto-generated method stub
		return this.categorieRepository.findAll();
	}

	@Override
	public void deleteCategorie(Long categorieId) {
		this.categorieRepository.delete(this.categorieRepository.findById(categorieId).orElseThrow());	
	}

	@Override
	public categorie updateCategorie(Long categorieId, MultipartFile file, String nom) throws IOException {
		categorie cat=this.categorieRepository.findById(categorieId).orElseThrow();
		 if (file.isEmpty() || nom == null || nom.trim().isEmpty()) {
		        throw new IllegalArgumentException("File or name cannot be empty");
		    }
		    // Ensure the directory exists
		    Files.createDirectories(Paths.get(uploadDir));

		    // Save the file to the directory
		    Path copyLocation = Paths.get(uploadDir + file.getOriginalFilename());
		    Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		cat.setNom(nom);
		cat.setNomImage(file.getOriginalFilename());
		cat.setPathImage(copyLocation.toString());
		cat.setTypeImage(file.getContentType());
		this.categorieRepository.save(cat);
		return cat;
	}

	@Override
	public categorie getCategorieById(Long categorieId) {
		// TODO Auto-generated method stub
		return this.categorieRepository.findById(categorieId).orElseThrow();
	}

}
