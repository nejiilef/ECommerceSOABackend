package com.project.ECommrce.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ECommrce.Entities.Commande;
import com.project.ECommrce.Entities.Panier;
import com.project.ECommrce.Repositories.CommandeRepository;
import com.project.ECommrce.Repositories.PanierRepository;

@Service
public class CommandeService implements ICommandeService{

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private PanierRepository panierRepository;
	
	@Override
	public Commande passerCommande(Long idPanier, Commande c) {
		// TODO Auto-generated method stub
		Panier p=this.panierRepository.findById(idPanier).orElseThrow();
		c.setPanier(p);
		return this.commandeRepository.save(c);
	}

	@Override
	public List<Commande> getAllCommandes() {
		// TODO Auto-generated method stub
		return this.commandeRepository.findAll();
	}

	@Override
	public Commande getCommandeById(Long idCommande) {
		// TODO Auto-generated method stub
		return this.commandeRepository.findById(idCommande).orElseThrow();
	}

	@Override
	public Commande updateCommande(Long idCommande,Long idPanier, Commande c) {
		// TODO Auto-generated method stub
		Panier p=this.panierRepository.findById(idPanier).orElseThrow();
		Commande com=this.commandeRepository.findById(idCommande).orElseThrow();
		com.setNomClient(c.getNomClient());
		com.setPrenomClient(c.getPrenomClient());
		com.setTelephoneClient(c.getTelephoneClient());
		com.setPanier(p);
		return this.commandeRepository.save(com);
	}

	@Override
	public void deleteCommande(Long idCommande) {
		this.commandeRepository.delete(this.commandeRepository.findById(idCommande).orElseThrow());
		
	}

}
