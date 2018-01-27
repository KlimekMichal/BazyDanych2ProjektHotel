
public class Adres {
	int id;
	String ulica;
	String nr_domu;
	String nr_mieszkania;
	String kod_pocztowy;
	String miasto;
	String panstwo;
	
	public Adres(int id, String ulica, String nr_domu, String nr_mieszkania, String kod_pocztowy, String miasto,
			String panstwo) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.nr_domu = nr_domu;
		this.nr_mieszkania = nr_mieszkania;
		this.kod_pocztowy = kod_pocztowy;
		this.miasto = miasto;
		this.panstwo = panstwo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNr_domu() {
		return nr_domu;
	}

	public void setNr_domu(String nr_domu) {
		this.nr_domu = nr_domu;
	}

	public String getNr_mieszkania() {
		return nr_mieszkania;
	}

	public void setNr_mieszkania(String nr_mieszkania) {
		this.nr_mieszkania = nr_mieszkania;
	}

	public String getKod_pocztowy() {
		return kod_pocztowy;
	}

	public void setKod_pocztowy(String kod_pocztowy) {
		this.kod_pocztowy = kod_pocztowy;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(String panstwo) {
		this.panstwo = panstwo;
	}

}
