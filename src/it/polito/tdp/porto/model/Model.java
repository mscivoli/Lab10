package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	
	private Graph<Author, MyEdge> grafo;
	private List<Author> listaAutori;
	private Map<Integer, Author> mapId;
	private List<Creator> listaConnessioni;
	private Map<Integer, Paper> mapPaper;
	private List<Paper> listaPaper;
	
	
	public void creaGrafo() {
		
		this.grafo = new SimpleGraph<>(MyEdge.class);
		
		PortoDAO dao = new PortoDAO();
		listaAutori = dao.loadAllAuthors();
		listaPaper = dao.loadAllPaper();
		
		mapId = new HashMap<Integer, Author>();
		mapPaper = new HashMap<Integer, Paper>();
		
		for(Author a : listaAutori) {
			mapId.put(a.getId(), a);
		}
		
		for(Paper p : listaPaper) {
			mapPaper.put(p.getEprintid(), p);
		}
		
		Graphs.addAllVertices(grafo, this.listaAutori);
		
		listaConnessioni = dao.getAllConnessioni(mapId, mapPaper);
		
		for(Creator ctemp : listaConnessioni) {
			MyEdge m = new MyEdge();
			m.setA1(ctemp.getAutore1());
			m.setA2(ctemp.getAutore2());
			m.setP1(ctemp.getPaper());
			
			this.grafo.addEdge(ctemp.getAutore1(), ctemp.getAutore2(), m);
			//Graphs.addEdgeWithVertices(grafo, ctemp.getAutore1(), ctemp.getAutore2());
			//Graphs.addEdgeWithVertices(grafo, ctemp.getAutore1(), ctemp.getAutore2());
		}
		
		System.out.println("grafo creato");
		System.out.println("vertici: "+grafo.vertexSet().size());
		System.out.println("edge: "+grafo.edgeSet().size());	
		
	}
	
	public List<MyEdge> calcolaCamminoMinimo(int a1, int a2){
		
		Author autore1 = mapId.get(a1);
		Author autore2 = mapId.get(a2);
		
		DijkstraShortestPath<Author, MyEdge> dijstra = new DijkstraShortestPath<>(this.grafo) ;
		GraphPath<Author, MyEdge> path = dijstra.getPath(autore1, autore2) ;
		
		
		return path.getEdgeList();
	}
	
	
	
	public List<Author> getCoautori(int autore) {
		Author atemp = mapId.get(autore);
		
		return Graphs.neighborListOf(grafo, atemp);
	}
	
	public List<Paper> getListaPaper2(int a1, int a2){
		
		List<MyEdge> atemp = this.calcolaCamminoMinimo(a1, a2);
		List<Paper> ptemp = new LinkedList<Paper>();
		for(MyEdge m : atemp) {
			ptemp.add(m.getP1());
		}
		
		
		return ptemp;
		
	}
	
	
	
	public List<Paper> getListaPaperCammino(int a1, int a2){
		Author au1 = mapId.get(a1);
		Author au2 = mapId.get(a2);
		Paper p = null;
		
		List<MyEdge> atemp = this.calcolaCamminoMinimo(a1, a2);
		List<Paper> ptemp = new LinkedList<Paper>();
		
		for(MyEdge e : atemp) {
			Author source = this.grafo.getEdgeSource(e);
			Author target = this.grafo.getEdgeTarget(e);
			Creator c = new Creator(source, target, p);
			int pos = listaConnessioni.indexOf(c);
			ptemp.add(listaConnessioni.get(pos).getPaper());
		}
		return ptemp;
	}
	

}




