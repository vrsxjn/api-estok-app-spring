package com.example.estok_app.api_estok_app.json;

public class UserResponse {
    private String nome;
    private String telefone;
    private String email;
    private String token;


    public UserResponse(String nome, String telefone, String email, String token) {
      this.nome = nome;
      this.telefone = telefone;
      this.email = email;
      this.token = token;
    }


    public String getNome() {
      return nome;
    }


    public void setNome(String nome) {
      this.nome = nome;
    }


    public String getTelefone() {
      return telefone;
    }


    public void setTelefone(String telefone) {
      this.telefone = telefone;
    }


    public String getEmail() {
      return email;
    }


    public void setEmail(String email) {
      this.email = email;
    }


    public String getToken() {
      return token;
    }


    public void setToken(String token) {
      this.token = token;
    }

}
