package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Opera implements myEntity{
	
	public Opera() {
		setCollezioniPartecipanti(new LinkedList<Collezione>());
	}
	
	@Column
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opera other = (Opera) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Opera(String titolo) {
		setTitolo(titolo);
		setCollezioniPartecipanti(new LinkedList<Collezione>());
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String titolo;
	
	@Column
	private String descrizione;
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getAnnoCreazione() {
		return annoCreazione;
	}

	public void setAnnoCreazione(LocalDate annoCreazione) {
		this.annoCreazione = annoCreazione;
	}

	public Artista getCreatore() {
		return creatore;
	}

	public void setCreatore(Artista creatore) {
		this.creatore = creatore;
	}

	public List<Collezione> getCollezioniPartecipanti() {
		return collezioniPartecipanti;
	}

	public void setCollezioniPartecipanti(List<Collezione> collezioniPartecipanti) {
		this.collezioniPartecipanti = collezioniPartecipanti;
	}

	@Column
	private LocalDate annoCreazione;
	
	@ManyToOne
	private Artista creatore;
	
	@ManyToMany (mappedBy = "opereCurate")
	private List<Collezione> collezioniPartecipanti;
	

}
