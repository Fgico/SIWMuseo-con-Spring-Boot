package it.uniroma3.siw.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Collezione implements myEntity {
	
	public Collezione() {
		setOpereCurate(new LinkedList<Opera>());
	}
	
	public Collezione(String nome) {
		setNome(nome);
		setOpereCurate(new LinkedList<Opera>());
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
		Collezione other = (Collezione) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Dipendente curatore;
	
	@Column
	private String nome;
	
	@Column 
	private String descrizione;
	
	@ManyToMany
	private List<Opera> opereCurate;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Dipendente getCuratore() {
		return curatore;
	}

	public void setCuratore(Dipendente curatore) {
		this.curatore = curatore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Opera> getOpereCurate() {
		return opereCurate;
	}

	public void setOpereCurate(List<Opera> opereCurate) {
		this.opereCurate = opereCurate;
	}

}
