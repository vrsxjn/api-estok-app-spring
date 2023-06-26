package com.example.estok_app.api_estok_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String descricao;
  private String imagem;
  private Double valor_item;
  private Double valor_unitario;
  private Integer quantidade;
  private String site;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public String getImagem() {
    return imagem;
  }
  public void setImagem(String imagem) {
    this.imagem = imagem;
  }
  public Double getValor_item() {
    return valor_item;
  }
  public void setValor_item(Double valor_item) {
    this.valor_item = valor_item;
  }
  public Double getValor_unitario() {
    return valor_unitario;
  }
  public void setValor_unitario(Double valor_unitario) {
    this.valor_unitario = valor_unitario;
  }
  public Integer getQuantidade() {
    return quantidade;
  }
  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
  public String getSite() {
    return site;
  }
  public void setSite(String site) {
    this.site = site;
  }

  
}
