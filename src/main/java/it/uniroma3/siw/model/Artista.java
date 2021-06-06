package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Artista implements myEntity{
	
	public Artista() {
		setCreazioni(new LinkedList<Opera>());
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
		Artista other = (Artista) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Artista(String nome, String cognome) {
		setNome(nome);
		setCognome(cognome);
		setCreazioni(new LinkedList<Opera>());
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id; 
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public LocalDate getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getLuogoMorte() {
		return luogoMorte;
	}

	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}

	public List<Opera> getCreazioni() {
		return creazioni;
	}

	public void setCreazioni(List<Opera> creazioni) {
		this.creazioni = creazioni;
	}

	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@Column
	private String nazionalita;
	
	@Column
	private LocalDate dataNascita;
	
	@Column
	private String luogoNascita;
	
	@Column(nullable = true)
	private LocalDate dataMorte;
	
	@Column(nullable = true)
	private String luogoMorte;
	
	@OneToMany (mappedBy = "creatore")
	@Cascade(value=CascadeType.DELETE)
	private List<Opera> creazioni;
	
}
