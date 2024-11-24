package com.project.ECommrce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ECommrce.Entities.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long>{

}
