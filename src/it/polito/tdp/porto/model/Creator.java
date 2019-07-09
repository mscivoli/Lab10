package it.polito.tdp.porto.model;

public class Creator {
	
	private Author autore1;
	private Author autore2;
	private Paper paper;
	
	public Creator(Author autore, Author articolo, Paper p1) {
		super();
		this.autore1 = autore;
		this.autore2 = articolo;
		this.paper = p1;
	}
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public Author getAutore1() {
		return autore1;
	}
	public void setAutore1(Author autore) {
		this.autore1 = autore;
	}
	public Author getAutore2() {
		return autore2;
	}
	public void setAutore2(Author articolo) {
		this.autore2 = articolo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore1 == null) ? 0 : autore1.hashCode());
		result = prime * result + ((autore2 == null) ? 0 : autore2.hashCode());
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
		Creator other = (Creator) obj;
		if (autore1 == null) {
			if (other.autore1 != null)
				return false;
		} else if (!autore1.equals(other.autore1))
			return false;
		if (autore2 == null) {
			if (other.autore2 != null)
				return false;
		} else if (!autore2.equals(other.autore2))
			return false;
		return true;
	}
	
	

}
