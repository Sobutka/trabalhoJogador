package com.jogadores.apifutebol.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jogadores.apifutebol.model.Jogador;
import com.jogadores.apifutebol.model.Pagamento;


public interface RepositoryPagamento  extends JpaRepository<Pagamento, Long>{

	List<Pagamento> findByJogador(Jogador jogador);
}
