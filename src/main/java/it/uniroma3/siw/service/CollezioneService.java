package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.repos.CollezioneRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollezioneService {
	@Autowired
	protected CollezioneRepo oRepo;
	
	@Transactional
	public Collezione prendi(Long id) {
		return (oRepo.findById(id)).orElse(null);
	}
	
	@Transactional
	public Collezione metti(Collezione o) {
		return oRepo.save(o);
	}
	
	@Transactional
	public List<Collezione> tutti(){
		List<Collezione> result = new ArrayList<>();
		Iterable<Collezione> iterable = this.oRepo.findAll();
        for(Collezione o : iterable)
            result.add(o);
        return result;
		
	}

	public boolean alreadyExists(Collezione o) {
		if(prendi(o.getId()) != null)
			return true;
		return false;
	}

	public void elimina(Long id) {
		oRepo.deleteById(id);
		
	}
	
}
