
public class Klient {

	int id;
	String imie;
	String nazwisko;
	String nr_telefonu;
	String email;
	int adresid;

	public Klient(int id, String imie, String nazwisko, String nr_telefonu, String email, int adresid) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_telefonu = nr_telefonu;
		this.email = email;
		this.adresid = adresid;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNr_tel() {
		return nr_telefonu;
	}

	public void setNr_tel(String nr_tel) {
		this.nr_telefonu = nr_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdresid() {
		return adresid;
	}

	public void setAdresid(int adresid) {
		this.adresid = adresid;
	}
	
	public String toString() {
		return String
				.format("Klient [id=%s, Imie=%s, Nazwisko=%s, Nr telefonu=%s, email=%s, adresid=%s]",
						id, imie, nazwisko, nr_telefonu, email, adresid);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
