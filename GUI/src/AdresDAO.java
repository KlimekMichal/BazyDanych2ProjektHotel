import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AdresDAO {
	
	public List<Adres> getAllAdres() throws Exception {
		List<Adres> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from adres");
			
			while (myRs.next()){
				Adres tempAdres = convertRowToAdres(myRs);
				lista.add(tempAdres);
			}
			return lista;
	}
		finally {
			myStmt1.close();
			}
	}
	
	public List<Adres> searchAdres(String ulica) throws Exception {
		List<Adres> lista = new ArrayList<>();
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		try {
			ulica += "%";
			myStmt1 = Logowanie.myConn.prepareStatement("Select * From adres where ulica like ?");
			myStmt1.setString(1, ulica);
			myRs = myStmt1.executeQuery();
			while (myRs.next()) {
				Adres tempAdres = convertRowToAdres(myRs);
				lista.add(tempAdres);
			}
			return lista;
		}
		finally {
		}

		}

	
	public void addAdres(Adres adres) throws Exception {
		PreparedStatement myStmt2 = null;
		try {
			myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_adres(?,?,?,?,?,?)}");
			myStmt2.setString(1, adres.getUlica());
			myStmt2.setString(2, adres.getNr_domu());
			myStmt2.setString(3, adres.getNr_mieszkania());
			myStmt2.setString(4, adres.getKod_pocztowy());
			myStmt2.setString(5, adres.getMiasto());
			myStmt2.setString(6, adres.getPanstwo());
			
			myStmt2.execute();
		}
		finally {
			myStmt2.close();
		}
	}
	public void updateAdres(Adres adres) throws Exception {
		PreparedStatement myStmt3 = null;
		try {
			myStmt3 = Logowanie.myConn.prepareStatement("update adres set ulica=?, nr_domu=?, nr_mieszkania=?, kod_pocztowy=?, miasto=?, pañstwo=? where id=?");
			myStmt3.setString(1, adres.getUlica());
			myStmt3.setString(2, adres.getNr_domu());
			myStmt3.setString(3, adres.getNr_mieszkania());
			myStmt3.setString(4, adres.getKod_pocztowy());
			myStmt3.setString(5, adres.getMiasto());
			myStmt3.setString(6, adres.getPanstwo());
			myStmt3.setInt(7, adres.getId());
			
			myStmt3.executeUpdate();
		}
		finally {
			myStmt3.close();
		}
	}
	
	public void deleteAdres(int adresID) throws Exception {
		PreparedStatement myStmt4 = null;
		
		try {
			myStmt4 = Logowanie.myConn.prepareStatement("delete from adres where id=?");
			myStmt4.setInt(1, adresID);
			try {
				myStmt4.executeUpdate();
			} catch(Exception exc) {
				JOptionPane.showMessageDialog(null, exc);
			}
			
		}
		finally {
			myStmt4.close();
		}
	}
	private Adres convertRowToAdres(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String ulica = myRs.getString("ulica");
		String nr_domu = myRs.getString("nr_domu");
		String nr_mieszkania = myRs.getString("nr_mieszkania");
		String kod_pocztowy = myRs.getString("kod_pocztowy");
		String miasto = myRs.getString("miasto");
		String panstwo = myRs.getString("pañstwo");	
		Adres tempAdres = new Adres(id, ulica, nr_domu, nr_mieszkania, kod_pocztowy, miasto, panstwo);
		
		return tempAdres;
	}
	private static void close(Connection myConn, Statement myStmt1, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt1 != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}
	
	private void close(Statement myStmt1, ResultSet myRs) throws SQLException {
		close(null, myStmt1, myRs);		
	}
}
