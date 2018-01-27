import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KlientDAO {
	
	public List<Klient> getAllKlient() throws Exception {
		List<Klient> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from klient");
			
			while (myRs.next()){
				Klient tempKlient = convertRowToKlient(myRs);
				lista.add(tempKlient);
			}
			return lista;
	}
		finally {
			myStmt1.close();
			}
	}
	
	public List<Klient> searchKlient(String nazwisko) throws Exception {
		List<Klient> lista = new ArrayList<>();
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		try {
			nazwisko += "%";
			myStmt1 = Logowanie.myConn.prepareStatement("Select * From klient where nazwisko like ?");
			myStmt1.setString(1, nazwisko);
			myRs = myStmt1.executeQuery();
			while (myRs.next()) {
				Klient tempKlient = convertRowToKlient(myRs);
				lista.add(tempKlient);
			}
			return lista;
		}
		finally {
		}

		}

	
	public void addKlient(Klient klient) throws Exception {
		PreparedStatement myStmt2 = null;
		try {
			myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_klienta(?,?,?,?,?)}");
			myStmt2.setString(1, klient.getImie());
			myStmt2.setString(2, klient.getNazwisko());
			myStmt2.setString(3, klient.getNr_tel());
			myStmt2.setString(4, klient.getEmail());
			myStmt2.setInt(5, klient.getAdresid());
			
			myStmt2.execute();
		}
		finally {
			myStmt2.close();
		}
	}
	public void updateKlient(Klient klient) throws Exception {
		PreparedStatement myStmt3 = null;
		try {
			myStmt3 = Logowanie.myConn.prepareStatement("update klient set imie=?, nazwisko=?, nr_telefonu=?, email=?, adresid=? where id=?");
			myStmt3.setString(1, klient.getImie());
			myStmt3.setString(2, klient.getNazwisko());
			myStmt3.setString(3, klient.getNr_tel());
			myStmt3.setString(4, klient.getEmail());
			myStmt3.setInt(5, klient.getAdresid());
			myStmt3.setInt(6, klient.getId());
			
			myStmt3.executeUpdate();
		}
		finally {
			myStmt3.close();
		}
	}
	
	public void deleteKlient(int klientID) throws Exception {
		PreparedStatement myStmt4 = null;
		PreparedStatement myStmt5 = null;
		
		try {
			myStmt4 = Logowanie.myConn.prepareStatement("delete from klient where id=?");
			myStmt5 = Logowanie.myConn.prepareStatement("Delete from rezerwacja where klientid=?");
			myStmt4.setInt(1, klientID);
			myStmt5.setInt(1, klientID);
			myStmt5.executeUpdate();
			myStmt4.executeUpdate();
		}
		finally {
			myStmt5.close();
			myStmt4.close();
		}
	}
	private Klient convertRowToKlient(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		String nr_telefonu = myRs.getString("nr_telefonu");
		String email = myRs.getString("email");
		int adresid = myRs.getInt("adresid");
		
		Klient tempKlient = new Klient(id, imie, nazwisko, nr_telefonu, email, adresid);
		
		return tempKlient;
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
