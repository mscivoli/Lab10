package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}
			conn.close();

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}
			conn.close();

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	public List<Author> loadAllAuthors(){
		String sql = "select id, lastname, firstname " + 
				"from author";
		List<Author> ltemp = new LinkedList<Author>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Author atemp = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				ltemp.add(atemp);
			}
			conn.close();
			
			return ltemp;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Creator> getAllConnessioni(Map<Integer, Author> mapId, Map<Integer, Paper> mapPaper){
		
		String sql = "select eo1.authorid as author1, eo2.authorid as author2, eo1.eprintid as p1 " + 
				"from creator eo1, creator eo2 " + 
				"where eo1.eprintid = eo2.eprintid and eo1.authorid < eo2.authorid " + 
				"group by eo1.authorid, eo2.authorid";

		List<Creator> ltemp = new LinkedList<Creator>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Creator ctemp = new Creator(mapId.get(rs.getInt("author1")), mapId.get(rs.getInt("author2")), mapPaper.get(rs.getInt("p1")));
				if(!ltemp.contains(ctemp))
					ltemp.add(ctemp);
			}
			conn.close();
			
			return ltemp;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	public List<Paper> loadAllPaper() {
		String sql = "select eprintid, title, issn, publication, type, types " + 
				"from paper";
		List<Paper> ltemp = new LinkedList<Paper>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Paper ptemp = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"), rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				ltemp.add(ptemp);
			}
			conn.close();
			
			return ltemp;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	
	
	
	
	
	
	
}