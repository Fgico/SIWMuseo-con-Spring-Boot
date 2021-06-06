package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Dipendente implements myEntity {
	
	public Dipendente() {
		setCollezioniCurate(new LinkedList<Collezione>());
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long matricola;

	@Column
	private String nome;
	
	@Column 
	private String cognome;
	
	@Column
	private String email;
	
	@Column
	private String luogoNascita;
	
	@Column
	private long numeroTelefono;
	
	@Column
	private LocalDate dataNascita;

	public Long getId() {
		return matricola;
	}

	public void setId(long matricola) {
		this.matricola = matricola;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public long getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(long numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (matricola ^ (matricola >>> 32));
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
		Dipendente other = (Dipendente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	@OneToMany (mappedBy = "curatore")
	private List<Collezione> collezioniCurate;

	public List<Collezione> getCollezioniCurate() {
		return collezioniCurate;
	}

	public void setCollezioniCurate(List<Collezione> collezioniCurate) {
		this.collezioniCurate = collezioniCurate;
	}

}
