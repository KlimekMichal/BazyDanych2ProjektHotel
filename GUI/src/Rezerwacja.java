import java.sql.Date;

public class Rezerwacja {
	int id;
	int nr_pokoju;
	String status;
	Date data_zameldowania;
	Date data_wymeldowania;
	String dane_o_rezydentach;
	float cena;
	int klientid;
	int pracownikid;
	
	public Rezerwacja(int id, int nr_pokoju, String status, Date data_zameldowania, Date data_wymeldowania,
			String dane_o_rezydentach, float cena, int klientid, int pracownikid) {
		super();
		this.id = id;
		this.nr_pokoju = nr_pokoju;
		this.status = status;
		this.data_zameldowania = data_zameldowania;
		this.data_wymeldowania = data_wymeldowania;
		this.dane_o_rezydentach = dane_o_rezydentach;
		this.cena = cena;
		this.klientid = klientid;
		this.pracownikid = pracownikid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNr_pokoju() {
		return nr_pokoju;
	}
	public void setNr_pokoju(int nr_pokoju) {
		this.nr_pokoju = nr_pokoju;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getData_zameldowania() {
		return data_zameldowania;
	}
	public void setData_zameldowania(Date data_zameldowania) {
		this.data_zameldowania = data_zameldowania;
	}
	public Date getData_wymeldowania() {
		return data_wymeldowania;
	}
	public void setData_wymeldowania(Date data_wymeldowania) {
		this.data_wymeldowania = data_wymeldowania;
	}
	public String getDane_o_rezydentach() {
		return dane_o_rezydentach;
	}
	public void setDane_o_rezydentach(String dane_o_rezydentach) {
		this.dane_o_rezydentach = dane_o_rezydentach;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public int getKlientid() {
		return klientid;
	}
	public void setKlientid(int klientid) {
		this.klientid = klientid;
	}
	public int getPracownikid() {
		return pracownikid;
	}
	public void setPracownikid(int pracownikid) {
		this.pracownikid = pracownikid;
	}

}
