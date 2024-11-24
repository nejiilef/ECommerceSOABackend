package com.project.ECommrce.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ECommrce.DTO.SignUpRequest;
import com.project.ECommrce.Entities.Utilisateur;
import com.project.ECommrce.Repositories.UtilisateurRepository;

@Service
public class AuthService implements IAuthService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	public Utilisateur createUtilisateur(SignUpRequest signupRequest) {
		if(utilisateurRepository.existsByEmail(signupRequest.getEmail())) {
			return null;
		}
		Utilisateur utilisateur=new Utilisateur();
		BeanUtils.copyProperties(signupRequest, utilisateur);
		String hashPassword = passwordEncoder.encode(signupRequest.getMotDePasse());
	    utilisateur.setMotDePasse(hashPassword);
	    Utilisateur createdUtilisateur=utilisateurRepository.save(utilisateur);
	    utilisateur.setId(createdUtilisateur.getId());;
		return utilisateur;
	}
	
	
}
