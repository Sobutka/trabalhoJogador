package com.jogadores.apifutebol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Pagamento")
public class Pagamento {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long cod_pagamento;

	@Column
	int ano;
	
	@Column
	int mes;
	
	@Column
	float valor;
	
	@ManyToOne
    @JoinColumn(name = "codjogador") 
	Jogador jogador;
	
	public Pagamento() {}
	
	public Pagamento(int ano, int mes, float valor, Jogador jogador) {
		this.ano = ano;
		this.mes = mes;
		this.valor = valor;
		this.jogador = jogador;
	}
	
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Long getCod_pagamento() {
		return cod_pagamento;
	}
}
