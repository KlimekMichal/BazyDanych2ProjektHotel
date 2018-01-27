import java.sql.Date;

public class Pracownik {
	int id;
	String imie;
	String nazwisko;
	String stanowisko;
	String nr_telefonu;
	String email;
	String wyksztalcenie;
	java.util.Date poczatek_pracy;
	java.util.Date koniec_pracy;
	float pensja;
	int adres_id;

	public Pracownik(int id, String imie, String nazwisko, String stanowisko, String nr_telefonu, String email,
			String wyksztalcenie, java.util.Date poczatek, java.util.Date koniec, float pensja, int adres_id) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.stanowisko = stanowisko;
		this.nr_telefonu = nr_telefonu;
		this.email = email;
		this.wyksztalcenie = wyksztalcenie;
		this.poczatek_pracy = poczatek;
		this.koniec_pracy = koniec;
		this.pensja = pensja;
		this.adres_id = adres_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getStanowisko() {
		return stanowisko;
	}
	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}
	public String getNr_telefonu() {
		return nr_telefonu;
	}
	public void setNr_telefonu(String nr_telefonu) {
		this.nr_telefonu = nr_telefonu;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWyksztalcenie() {
		return wyksztalcenie;
	}
	public void setWyksztalcenie(String wyksztalcenie) {
		this.wyksztalcenie = wyksztalcenie;
	}
	public java.util.Date getPoczatek_pracy() {
		return poczatek_pracy;
	}
	public void setPoczatek_pracy(Date poczatek_pracy) {
		this.poczatek_pracy = poczatek_pracy;
	}
	public java.util.Date getKoniec_pracy() {
		return koniec_pracy;
	}
	public void setKoniec_pracy(Date koniec_pracy) {
		this.koniec_pracy = koniec_pracy;
	}
	public float getPensja() {
		return pensja;
	}
	public void setPensja(float pensja) {
		this.pensja = pensja;
	}
	public int getAdres_id() {
		return adres_id;
	}
	public void setAdres_id(int adres_id) {
		this.adres_id = adres_id;
	}

}
