
public class Pokoj {
	int id;
	int liczba_lozek;
	String typ_lozek;
	Float cena;
	String status;
	int rezerwacjaid;
	
	public Pokoj(int id, int liczba_lozek, String typ_lozek, Float cena, String status, int rezerwacjaid) {
		super();
		this.id = id;
		this.liczba_lozek = liczba_lozek;
		this.typ_lozek = typ_lozek;
		this.cena = cena;
		this.status = status;
		this.rezerwacjaid = rezerwacjaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLiczba_lozek() {
		return liczba_lozek;
	}

	public void setLiczba_lozek(int liczba_lozek) {
		this.liczba_lozek = liczba_lozek;
	}

	public String getTyp_lozek() {
		return typ_lozek;
	}

	public void setTyp_lozek(String typ_lozek) {
		this.typ_lozek = typ_lozek;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRezerwacjaid() {
		return rezerwacjaid;
	}

	public void setRezerwacjaid(int rezerwacjaid) {
		this.rezerwacjaid = rezerwacjaid;
	}

}
