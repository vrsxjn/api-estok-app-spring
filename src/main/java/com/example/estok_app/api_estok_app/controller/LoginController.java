package com.example.estok_app.api_estok_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estok_app.api_estok_app.json.Response;
import com.example.estok_app.api_estok_app.json.UserResponse;
import com.example.estok_app.api_estok_app.jwt.JwtController;
import com.example.estok_app.api_estok_app.models.LoginModel;
import com.example.estok_app.api_estok_app.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginModel register) {
        userRepository.save(register);
        return ResponseEntity.status(200).body(new Response(200, "Cadastro feito com Sucesso"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginUser) {
        String email = loginUser.getEmail();
        String senha = loginUser.getSenha();

        if (email == null || senha == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email e senha são obrigatórios");
        }

        LoginModel user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }

        if (!user.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Senha incorreta");
        }

        String token = JwtController.generateToken(user.getId());

        user.setToken(token);
        userRepository.save(user);

        UserResponse userResponse = new UserResponse(user.getNome(), user.getTelefone(), user.getEmail(), token);
        return ResponseEntity.ok(userResponse);
    }
}
