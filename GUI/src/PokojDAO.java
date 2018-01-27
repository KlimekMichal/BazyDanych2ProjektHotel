import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PokojDAO {
	
	public List<Pokoj> getAllPokoj() throws Exception {
		List<Pokoj> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from pokój");
			
			while (myRs.next()){
				Pokoj tempPokoj = convertRowToPokoj(myRs);
				lista.add(tempPokoj);
			}
			return lista;
	}
		finally {
			myStmt1.close();
			}
	}
	
	public List<Pokoj> searchPokoj(String typ) throws Exception {
		List<Pokoj> lista = new ArrayList<>();
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		try {
			typ += "%";
			myStmt1 = Logowanie.myConn.prepareStatement("Select * From pokój where typ_lozek like ?");
			myStmt1.setString(1, typ);
			myRs = myStmt1.executeQuery();
			while (myRs.next()) {
				Pokoj tempPokoj = convertRowToPokoj(myRs);
				lista.add(tempPokoj);
			}
			return lista;
		}
		finally {
		}

		}

	
	public void addPokoj(Pokoj pokoj) throws Exception {
		PreparedStatement myStmt2 = null;
		try {
			myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_pokój(?,?,?,?,?)}");
			myStmt2.setInt(1, pokoj.getLiczba_lozek());
			myStmt2.setString(2, pokoj.getTyp_lozek());
			myStmt2.setFloat(3, pokoj.getCena());
			myStmt2.setString(4, pokoj.getStatus());
			myStmt2.setInt(5, pokoj.getRezerwacjaid());
			
			myStmt2.execute();
		}
		finally {
			myStmt2.close();
		}
	}
	public void updatePokoj(Pokoj pokoj) throws Exception {
		PreparedStatement myStmt3 = null;
		try {
			myStmt3 = Logowanie.myConn.prepareStatement("update pokój set liczba_³ó¿ek=?, typ_³ó¿ek=?, cena=?, status=?, rezerwacjaid=? where id=?");
			myStmt3.setInt(1, pokoj.getLiczba_lozek());
			myStmt3.setString(2, pokoj.getTyp_lozek());
			myStmt3.setFloat(3, pokoj.getCena());
			myStmt3.setString(4, pokoj.getStatus());
			myStmt3.setInt(5, pokoj.getRezerwacjaid());
			myStmt3.setInt(6, pokoj.getId());
			
			myStmt3.executeUpdate();
		}
		finally {
			myStmt3.close();
		}
	}
	
	public void deletePokoj(int pokojID) throws Exception {
		PreparedStatement myStmt4 = null;
		PreparedStatement myStmt5 = null;
		
		try {
			myStmt4 = Logowanie.myConn.prepareStatement("delete from pokój where id=?");
			myStmt5 = Logowanie.myConn.prepareStatement("Delete from wyposa¿enie where pokojid=?");
			myStmt4.setInt(1, pokojID);
			myStmt5.setInt(1, pokojID);
			myStmt5.executeUpdate();
			myStmt4.executeUpdate();
		}
		finally {
			myStmt5.close();
			myStmt4.close();
		}
	}
	private Pokoj convertRowToPokoj(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		int liczba_lozek = myRs.getInt("liczba_³ó¿ek");
		String typ_lozek = myRs.getString("typ_³ó¿ek");
		float cena = myRs.getFloat("cena");
		String status = myRs.getString("status");
		int rezerwacjaid = myRs.getInt("rezerwacjaid");
		
		Pokoj tempPokoj = new Pokoj(id, liczba_lozek, typ_lozek, cena, status, rezerwacjaid);
		
		return tempPokoj;
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
