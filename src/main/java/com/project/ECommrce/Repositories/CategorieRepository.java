package com.project.ECommrce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ECommrce.Entities.categorie;

@Repository
public interface CategorieRepository extends JpaRepository<categorie, Long>{
	
}
