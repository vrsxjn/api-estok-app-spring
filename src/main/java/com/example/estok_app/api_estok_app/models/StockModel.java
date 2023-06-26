package com.example.estok_app.api_estok_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String descricao;
  private Integer quantidade_total;
  private String data_entrada;
  private String data_validade;
  private String tipo;
  private String status_estoque;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public Integer getQuantidade_total() {
    return quantidade_total;
  }
  public void setQuantidade_total(Integer quantidade_total) {
    this.quantidade_total = quantidade_total;
  }
  public String getData_entrada() {
    return data_entrada;
  }
  public void setData_entrada(String data_entrada) {
    this.data_entrada = data_entrada;
  }
  public String getData_validade() {
    return data_validade;
  }
  public void setData_validade(String data_validade) {
    this.data_validade = data_validade;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public String getStatus_estoque() {
    return status_estoque;
  }
  public void setStatus_estoque(String status_estoque) {
    this.status_estoque = status_estoque;
  }
  
  
}
