package com.project.ECommrce.Services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.ECommrce.Entities.Utilisateur;

import com.project.ECommrce.Repositories.UtilisateurRepository;

@Service
public class UtilisateurService implements UserDetailsService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> utilisateurOpt=utilisateurRepository.findByEmail(username);
		if(utilisateurOpt.isPresent()) {
			Utilisateur utilisateur=utilisateurOpt.get();
			 List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")); 
			return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(),authorities);   
		}
		throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + username);
	    
	}
	 public Long getIdUser(String email) {
	    	Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);
	    	Long id=null;
	        if (utilisateurOpt.isPresent()) {
	        	id=utilisateurOpt.get().getId();
	        }
	        return id;
	        }
	
}
