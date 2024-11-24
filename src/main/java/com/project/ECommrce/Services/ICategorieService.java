package com.project.ECommrce.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.ECommrce.Entities.categorie;

public interface ICategorieService {

	public categorie addCategorie(MultipartFile file,String nom) throws IOException;
public categorie getCategorieById(Long categorieId);
	public List<categorie> getAllCategories(); 
	void deleteCategorie(Long categorieId);
	categorie updateCategorie(Long categorieId,MultipartFile file, String nom) throws IOException;
}
