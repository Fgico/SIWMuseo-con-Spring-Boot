package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.repos.DipendenteRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {
	@Autowired
	protected DipendenteRepo oRepo;
	
	@Transactional
	public Dipendente prendi(Long id) {
		return (oRepo.findById(id)).orElse(null);
	}
	
	@Transactional
	public Dipendente metti(Dipendente o) {
		return oRepo.save(o);
	}
	
	@Transactional
	public List<Dipendente> tutti(){
		List<Dipendente> result = new ArrayList<>();
		Iterable<Dipendente> iterable = this.oRepo.findAll();
        for(Dipendente o : iterable)
            result.add(o);
        return result;
		
	}

	public boolean alreadyExists(Dipendente o) {
		if(prendi(o.getId()) != null)
			return true;
		return false;
	}
	
	
}
