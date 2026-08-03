package br.com.yuri.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContaPutDto {

	private UUID idConta;  // 
    private String nome;
    private LocalDate data;
    private BigDecimal valor;
    private String descricao;
    private Integer tipo;
    
	public UUID getIdConta() {  
		return idConta;
	}
	
	public void setIdConta(UUID idConta) {  
		this.idConta = idConta;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}