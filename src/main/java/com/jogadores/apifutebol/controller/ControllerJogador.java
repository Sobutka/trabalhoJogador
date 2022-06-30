package com.jogadores.apifutebol.controller;

import java.util.ArrayList;
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
import com.jogadores.apifutebol.repository.RepositoryJogador;




@RestController
@RequestMapping("/api")
public class ControllerJogador {

	@Autowired
    RepositoryJogador jogadorRepository;
	
	@GetMapping(path="/jogador")
    public ResponseEntity<List<Jogador>> getAllJogadores() 
	{
        try {
            List<Jogador> jogadores = new ArrayList<Jogador>();
            jogadorRepository.findAll().forEach(jogadores::add);
 
            if (jogadores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jogadores, HttpStatus.OK);
 
 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/jogador/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") long id) 
	{
        Optional<Jogador> jogador = jogadorRepository.findById(id);
 
        if (jogador.isPresent()) {
            return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("/jogador")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) 
	{
        try {
            Jogador _jogador = jogadorRepository
            		.save(new Jogador(jogador.getNome(), jogador.getDatanascimento(), jogador.getEmail()));
            return new ResponseEntity<>(_jogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	 @DeleteMapping("/jogador/{id}")
	    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") long id) {
		try {
			jogadorRepository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
		} 
	 
		@PutMapping("/jogador/{id}")
	    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") long id, @RequestBody Jogador jogador)
		{
	        Optional<Jogador> jogadord = jogadorRepository.findById(id);
	 
	        if (jogadord.isPresent()) {
	            Jogador _jogador = jogadord.get();
	            _jogador.setNome(jogador.getNome());
	            _jogador.setEmail(jogador.getEmail());
	            _jogador.setDatanascimento(jogador.getDatanascimento());
	            return new ResponseEntity<>(jogadorRepository.save(_jogador), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	    @DeleteMapping("/jogador")
	    public ResponseEntity<HttpStatus> deleteAllJogadores() {
	        try {
	        	jogadorRepository.deleteAll();
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
