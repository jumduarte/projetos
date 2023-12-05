package com.projeto.controller;

	import java.util.List;

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

import com.projeto.entities.Projetos;
import com.projeto.services.ProjetosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

	@Tag(name = "Projetos", description = "API REST DE GERENCIAMENTO DE PROJETOS")
	@RestController
	@RequestMapping("/projetos")
	public class ProjetosController {
	    
	    private final ProjetosService projetosService;
	    
	    @Autowired
	    public ProjetosController(ProjetosService projetosService) {
	        this.projetosService = projetosService;
	    }

	    @GetMapping("/{id}")
	    @Operation(summary = "Localiza projetos por ID")
	    public ResponseEntity<Projetos> getProductById(@PathVariable Long id) {
	    	Projetos projetos = projetosService.getProjetosById(id);
	        if (projetos != null) {
	            return ResponseEntity.ok(projetos);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @GetMapping("/")
	    @Operation(summary = "Apresenta todos os projetos")
	    public ResponseEntity<List<Projetos>> getAllProjetos() {
	        List<Projetos> projetos = projetosService.getAllProjetos();
	        return ResponseEntity.ok(projetos);
	    }
	    @PostMapping("/")
	    @Operation(summary = "Cadastra um projeto")
	    public ResponseEntity<Projetos> criarProjetos(@RequestBody @Valid Projetos projetos) {
	    	Projetos criarProjetos = projetosService.salvarProjetos(projetos);
	        return ResponseEntity.status(HttpStatus.CREATED).body(criarProjetos);
	    }
	   

	    @PutMapping("/{id}")
	    @Operation(summary = "Altera o projeto")
	    public ResponseEntity<Projetos> updateProjetos(@PathVariable Long id, @RequestBody @Valid Projetos projetos) {
	    	Projetos updatedProjetos = projetosService.updateProjetos(id, projetos);
	        if (updatedProjetos != null) {
	            return ResponseEntity.ok(updatedProjetos);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    @Operation(summary = "Deleta o projeto")
	    public ResponseEntity<String> deleteProjetos(@PathVariable Long id) {
	        boolean deleted = projetosService.deleteProjetos(id);
	        if (deleted) {
	        	 return ResponseEntity.ok().body("O projeto foi excluído com sucesso.");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	}
