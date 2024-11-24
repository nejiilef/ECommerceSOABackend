package com.project.ECommrce.Services;

import com.project.ECommrce.DTO.SignUpRequest;
import com.project.ECommrce.Entities.Utilisateur;

public interface IAuthService {

	Utilisateur createUtilisateur(SignUpRequest signupRequest);
}
