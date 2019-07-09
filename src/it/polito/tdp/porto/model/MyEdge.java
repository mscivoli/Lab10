package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class MyEdge extends DefaultEdge{
	
	private Author a1;
	private Author a2;
	private Paper p1;
	
	public MyEdge() {
		
	}

	public Author getA1() {
		return a1;
	}

	public void setA1(Author a1) {
		this.a1 = a1;
	}

	public Author getA2() {
		return a2;
	}

	public void setA2(Author a2) {
		this.a2 = a2;
	}

	public Paper getP1() {
		return p1;
	}

	public void setP1(Paper p1) {
		this.p1 = p1;
	}
	
	

}
