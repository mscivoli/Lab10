package it.polito.tdp.porto.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo();
		
		/*List<Author> ltemp = new LinkedList<Author>();
		ltemp = model.getCoautori(199);
		for(Author a : ltemp) {
			System.out.println(a.toString());
		}
		*/
		
		/*List<Author> ltemp1 = new LinkedList<Author>();
		ltemp1 = model.calcolaCamminoMinimo(199, 2042);
		if(ltemp1 != null) {
			for(Author a : ltemp1) {
				System.out.println(a.toString());
			}
		}
		else System.out.println("Nessun cammino tra i due autori");
		*/
		System.out.println();
		
		List<Paper> ltemp2 = new LinkedList<Paper>();
		ltemp2 = model.getListaPaperCammino(199, 2042);
		for(Paper p : ltemp2) {
			System.out.println(p.toString());
		}
		System.out.println();
		System.out.println("Stampo nel secondo modo");
		System.out.println();
		
		List<Paper> ltemp3 = new LinkedList<Paper>();
		ltemp3 = model.getListaPaper2(199, 2042);
		for(Paper p : ltemp3) {
			System.out.println(p.toString());
		}
	}

}
