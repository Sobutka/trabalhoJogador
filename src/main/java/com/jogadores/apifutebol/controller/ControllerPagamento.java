package com.jogadores.apifutebol.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogadores.apifutebol.model.Jogador;
import com.jogadores.apifutebol.model.Pagamento;
import com.jogadores.apifutebol.repository.RepositoryJogador;
import com.jogadores.apifutebol.repository.RepositoryPagamento;




@RestController
@RequestMapping("/api")
public class ControllerPagamento {

	@Autowired
	RepositoryPagamento pagamentoRepository;

	@Autowired
	RepositoryJogador jogadorRepository;

	
	@GetMapping("/jogador/{id}/pagamento") 
	public ResponseEntity<List<Pagamento>> getPagamentoByJogadoId(@PathVariable("id") long id)
	{
		Optional<Jogador> jogadordata = jogadorRepository.findById(id);
		if (jogadordata.isPresent()) {
			List<Pagamento> pagamentos = pagamentoRepository.findByJogador(jogadordata.get());
			if (pagamentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(pagamentos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/pagamento/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") long id, @RequestBody Pagamento pagamento)
	{
        Optional<Pagamento> pagamentodata = pagamentoRepository.findById(id);
 
        if (pagamentodata.isPresent()) {
            Pagamento _pagamento = pagamentodata.get();
            _pagamento.setAno(pagamento.getAno());
            _pagamento.setMes(pagamento.getMes());
            _pagamento.setValor(pagamento.getValor());
            return new ResponseEntity<>(pagamentoRepository.save(_pagamento), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("/jogador/{id}/pagamento")
	public ResponseEntity<Pagamento> createPagamento(@PathVariable("id") long id, @RequestBody Pagamento pagamento) {
		Optional<Jogador> jogadordata = jogadorRepository.findById(id);
		if (jogadordata.isPresent()) {
			try {
				Pagamento _pagamento = pagamentoRepository.save(
						new Pagamento(pagamento.getAno(), pagamento.getMes(), pagamento.getValor(), jogadordata.get()));
				return new ResponseEntity<>(_pagamento, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/pagamento/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") long id) {
        try {
            pagamentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

}
