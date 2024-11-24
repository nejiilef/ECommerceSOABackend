package com.project.ECommrce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ECommrce.Entities.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>{

}
