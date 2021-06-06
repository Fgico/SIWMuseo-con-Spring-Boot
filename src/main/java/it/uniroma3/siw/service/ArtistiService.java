package it.uniroma3.siw.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repos.ArtistaRepo;

@Service
public class ArtistiService {
	@Autowired
	private ArtistaRepo repo;
	
	@Transactional
	public Artista salva(Artista a) {
		return repo.save(a);
	}
	
	@Transactional
	public Artista getArtista(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Artista> getAll(){
		List<Artista> list = new ArrayList<Artista>();
		Iterable<Artista> i = repo.findAll();
		for (Artista a :i) {
			list.add(a);
		}
		return list;
	}

	public boolean alreadyExists(Artista o) {

		return false;
	}

	public void elimina(Long id) {
		repo.deleteById(id);
		return;
	}
	
}
