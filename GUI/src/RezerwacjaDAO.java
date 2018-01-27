import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RezerwacjaDAO {
	
	public List<Rezerwacja> getAllRezerwacja() throws Exception {
		List<Rezerwacja> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from rezerwacja");
			
			while (myRs.next()){
				Rezerwacja tempRezerwacja = convertRowToRezerwacja(myRs);
				lista.add(tempRezerwacja);
			}
			return lista;
	}
		finally {
			myStmt1.close();
			}
	}
	
	public List<Rezerwacja> searchRezerwacja(String numer) throws Exception {
		List<Rezerwacja> lista = new ArrayList<>();
		PreparedStatement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.prepareStatement("Select * From rezerwacja where klientid like ?");
			myStmt1.setString(1, numer);
			myRs = myStmt1.executeQuery();
			while (myRs.next()) {
				Rezerwacja tempRezerwacja = convertRowToRezerwacja(myRs);
				lista.add(tempRezerwacja);
			}
			return lista;
		}
		finally {
		}

		}
	
	public void addRezerwacja(Rezerwacja rezerwacja) throws Exception {
		PreparedStatement myStmt2 = null;
		try {
			myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_rezerwacje(?,?,?,?,?,?,?,?)}");
			myStmt2.setInt(1, rezerwacja.getNr_pokoju());
			myStmt2.setString(2, rezerwacja.getStatus());
			myStmt2.setDate(3, rezerwacja.getData_zameldowania());
			myStmt2.setDate(4, rezerwacja.getData_wymeldowania());
			myStmt2.setString(5, rezerwacja.getDane_o_rezydentach());
			myStmt2.setFloat(6, rezerwacja.getCena());
			myStmt2.setInt(7, rezerwacja.getKlientid());
			myStmt2.setInt(8, rezerwacja.getPracownikid());
			myStmt2.execute();
		}
		finally {
			myStmt2.close();
		}
	}
	public void updateRezerwacja(Rezerwacja rezerwacja) throws Exception {
		PreparedStatement myStmt3 = null;
		try {
			myStmt3 = Logowanie.myConn.prepareStatement("update rezerwacja set nr_pokoju=?, status=?, data_zameldowania=?,data_wymeldowania=?, dane_o_rezydentach=?,cena=?,klientid=?,pracownikid=? where id=?");
			myStmt3.setInt(1, rezerwacja.getNr_pokoju());
			myStmt3.setString(2, rezerwacja.getStatus());
			myStmt3.setDate(3, rezerwacja.getData_zameldowania());
			myStmt3.setDate(4, rezerwacja.getData_wymeldowania());
			myStmt3.setString(5, rezerwacja.getDane_o_rezydentach());
			myStmt3.setFloat(6, rezerwacja.getCena());
			myStmt3.setInt(7, rezerwacja.getKlientid());
			myStmt3.setInt(8, rezerwacja.getPracownikid());
			myStmt3.setInt(9, rezerwacja.getId());
			
			myStmt3.executeUpdate();
		}
		finally {
			myStmt3.close();
		}
	}
	public void deleteRezerwacja(int rezerwacjaID) throws Exception {
		PreparedStatement myStmt4 = null;
		try {
			myStmt4 = Logowanie.myConn.prepareStatement("delete from rezerwacja where id=?");
			myStmt4.setInt(1, rezerwacjaID);
			myStmt4.executeUpdate();
		}
		finally {
			myStmt4.close();
		}
	}
	public boolean checking(Date datap,Date datak, int nrpokoju) throws Exception {
		List<Rezerwacja> lista = new ArrayList<>();
		Statement myStmt1 = null;
		ResultSet myRs = null;
		try {
			myStmt1 = Logowanie.myConn.createStatement();
			myRs = myStmt1.executeQuery("select * from rezerwacja");
			
			while (myRs.next()){
				Rezerwacja tempRezerwacja = convertRowToRezerwacja(myRs);
				lista.add(tempRezerwacja);
			}
			for(int i=0;i<lista.size();i++) {
				if(lista.get(i).getNr_pokoju()==nrpokoju) {
					if((lista.get(i).getData_zameldowania().before(datak) && lista.get(i).getData_zameldowania().after(datap)) ||
							(lista.get(i).getData_wymeldowania().after(datap) && lista.get(i).getData_wymeldowania().before(datak))) {
						return false;					
					}
				}
			}
			return true;
	}
		finally {
			myStmt1.close();
			}
	}
	private Rezerwacja convertRowToRezerwacja(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		int nr_pokoju = myRs.getInt("nr_pokoju");
		String status = myRs.getString("status");
		Date data_zameldowania = myRs.getDate("data_zameldowania");
		Date data_wymeldowania = myRs.getDate("data_wymeldowania");
		String dane_o_rezydentach = myRs.getString("dane_o_rezydentach");
		float cena = myRs.getFloat("cena");
		int klientid = myRs.getInt("klientid");
		int pracownikid = myRs.getInt("pracownikid");
		
		Rezerwacja tempRezerwacja = new Rezerwacja(id, nr_pokoju, status, data_zameldowania, data_wymeldowania, dane_o_rezydentach, cena, klientid, pracownikid);
		
		return tempRezerwacja;
	}

}
