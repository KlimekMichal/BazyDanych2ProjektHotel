import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PracownikDAO {
	
	public List<Pracownik> getAllPracownik() throws Exception {
		List<Pracownik> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from pracownik");
			
			while (myRs.next()){
				Pracownik tempPracownik = convertRowToPracownik(myRs);
				lista.add(tempPracownik);
			}
			return lista;
	}
		finally {
			myStmt1.close();
			}
	}
	
	public List<Pracownik> searchPracownik(String nazwisko) throws Exception {
		List<Pracownik> lista = new ArrayList<>();
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		try {
			nazwisko += "%";
			myStmt1 = Logowanie.myConn.prepareStatement("Select * From pracownik where nazwisko like ?");
			myStmt1.setString(1, nazwisko);
			myRs = myStmt1.executeQuery();
			while (myRs.next()) {
				Pracownik tempPracownik = convertRowToPracownik(myRs);
				lista.add(tempPracownik);
			}
			return lista;
		}
		finally {
		}

		}
	
	public void addPracownik(Pracownik Pracownik) throws Exception {
		PreparedStatement myStmt2 = null;
		try {
			myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_Pracownika(?,?,?,?,?,?,?,?,?,?)}");
			myStmt2.setString(1, Pracownik.getImie());
			myStmt2.setString(2, Pracownik.getNazwisko());
			myStmt2.setString(3, Pracownik.getStanowisko());
			myStmt2.setString(4, Pracownik.getNr_telefonu());
			myStmt2.setString(5, Pracownik.getEmail());
			myStmt2.setString(6, Pracownik.getWyksztalcenie());
			myStmt2.setDate(7, (Date) Pracownik.getPoczatek_pracy());
			myStmt2.setDate(8, (Date) Pracownik.getKoniec_pracy());
			myStmt2.setFloat(9, Pracownik.getPensja());
			myStmt2.setInt(10, Pracownik.getAdres_id());		
			myStmt2.execute();
		}
		finally {
			myStmt2.close();
		}
	}
	public void updatePracownik(Pracownik pracownik) throws Exception {
		PreparedStatement myStmt3 = null;
		try {
			myStmt3 = Logowanie.myConn.prepareStatement("update pracownik set imie=?, nazwisko=?, stanowisko=?,nr_telefonu=?, email=?,wykszta³cenie=?,poczatek_pracy=?,koniec_pracy=?,pensja=?, adres_id=? where id=?");
			myStmt3.setString(1, pracownik.getImie());
			myStmt3.setString(2, pracownik.getNazwisko());
			myStmt3.setString(3, pracownik.getStanowisko());
			myStmt3.setString(4, pracownik.getNr_telefonu());
			myStmt3.setString(5, pracownik.getEmail());
			myStmt3.setString(6, pracownik.getWyksztalcenie());
			myStmt3.setDate(7, (Date) pracownik.getPoczatek_pracy());
			myStmt3.setDate(8, (Date) pracownik.getKoniec_pracy());
			myStmt3.setFloat(9, pracownik.getPensja());
			myStmt3.setInt(10, pracownik.getAdres_id());		
			myStmt3.setInt(11, pracownik.getId());
			
			myStmt3.executeUpdate();
		}
		finally {
			myStmt3.close();
		}
	}
	public void deletePracownik(int pracownikID) throws Exception {
		PreparedStatement myStmt4 = null;
		PreparedStatement myStmt5 = null;
		
		try {
			myStmt4 = Logowanie.myConn.prepareStatement("delete from pracownik where id=?");
			myStmt4.setInt(1, pracownikID);
			myStmt4.executeUpdate();
		}
		finally {
			myStmt4.close();
		}
	}
	private Pracownik convertRowToPracownik(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		String stanowisko = myRs.getString("stanowisko");
		String nr_telefonu = myRs.getString("nr_telefonu");
		String email = myRs.getString("email");
		String wyksztalcenie = myRs.getString("wykszta³cenie");
		Date poczatek_pracy = myRs.getDate("poczatek_pracy");
		Date koniec_pracy = myRs.getDate("koniec_pracy");
		float pensja = myRs.getFloat("pensja");
		int adresid = myRs.getInt("adres_id");
		
		Pracownik tempPracownik = new Pracownik(id, imie, nazwisko, stanowisko, nr_telefonu, email, wyksztalcenie, poczatek_pracy, koniec_pracy, pensja, adresid);
		
		return tempPracownik;
	}

}
