package com.jogadores.apifutebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jogadores.apifutebol.model.Jogador;

public interface RepositoryJogador extends JpaRepository<Jogador, Long> {

}
