package com.jogadores.apifutebol.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Jogador")
public class Jogador {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long cod_jogador;
	
	@Column
	String nome;
	
	@Column
	Date datanascimento;
	
	@Column
	String email;
	
	
	public Jogador() {}
	
	public Jogador(String nome, Date datanascmto, String email) {
		this.nome = nome;
		this.datanascimento = datanascmto;
		this.email=email;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascmnto) {
		this.datanascimento = datanascmnto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Long getCod_jogador() {
		return cod_jogador;
	}
}
