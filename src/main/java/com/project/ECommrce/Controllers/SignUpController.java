package com.project.ECommrce.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ECommrce.DTO.SignUpRequest;
import com.project.ECommrce.Entities.Utilisateur;
import com.project.ECommrce.Services.AuthService;


@RestController
public class SignUpController {
	private final AuthService authService;

	@Autowired
	public SignUpController(AuthService authService) {
		this.authService=authService;
	}
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        
            Utilisateur utilisateur = authService.createUtilisateur(signUpRequest);
            if (utilisateur != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
            }
        
        return ResponseEntity.badRequest().body("L'utilisateur existe déjà ou les informations sont incorrectes");
    }

}
