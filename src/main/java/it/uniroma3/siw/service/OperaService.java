package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repos.OperaRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperaService {
	@Autowired
	protected OperaRepo oRepo;
	
	@Transactional
	public Opera prendi(Long id) {
		return (oRepo.findById(id)).orElse(null);
	}
	
	@Transactional
	public Opera metti(Opera o) {
		return oRepo.save(o);
	}
	
	@Transactional
	public List<Opera> tutti(){
		List<Opera> result = new ArrayList<>();
		Iterable<Opera> iterable = this.oRepo.findAll();
        for(Opera o : iterable)
            result.add(o);
        return result;
		
	}

	public boolean alreadyExists(Opera o) {
		if(prendi(o.getId()) != null)
			return true;
		return false;
	}

	public void elimina(Long id) {
		oRepo.deleteById(id);
		
	}
	
}
