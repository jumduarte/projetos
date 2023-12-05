package com.projeto.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entities.Projetos;
import com.projeto.repository.ProjetosRepository;

	@Service
	public class ProjetosService {
	    private final ProjetosRepository projetosRepository;
	    
	    @Autowired
	    public ProjetosService(ProjetosRepository projetosRepository) {
	        this.projetosRepository = projetosRepository;
	    }

	    public List<Projetos> getAllProjetos() {
	        return projetosRepository.findAll();
	    }

	    public Projetos getProjetosById(Long id) {
	        Optional<Projetos> projetos = projetosRepository.findById(id);
	        return projetos.orElse(null);
	    }

	    public Projetos salvarCursos(Projetos projetos) {
	        return projetosRepository.save(projetos);
	    }

	    public Projetos updateProjetos(Long id, Projetos updatedProjetos) {
	        Optional<Projetos> existingProjetos = projetosRepository.findById(id);
	        if (existingProjetos.isPresent()) {
	            updatedProjetos.setId(id);
	            return projetosRepository.save(updatedProjetos);
	        }
	        return null;
	    }
	    public boolean deleteProjetos(Long id) {
	        Optional<Projetos> existingProjetos = projetosRepository.findById(id);
	        if (existingProjetos.isPresent()) {
	        	projetosRepository.deleteById(id);
	           return true;
	        }
	        return false;
	    }
	}
