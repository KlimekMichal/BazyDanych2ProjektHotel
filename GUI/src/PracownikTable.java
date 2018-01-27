import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PracownikTable extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int STANOWISKO_COL = 3;
	private static final int NR_TELEFONU_COL = 4;
	private static final int EMAIL_COL = 5;
	private static final int WYKSZTALCENIE_COL = 6;
	private static final int POCZATEK_PRACY_COL = 7;
	private static final int KONIEC_PRACY_COL = 8;
	private static final int PENSJA_COL = 9;
	private static final int ADRESID_COL = 10;
	
	private String[] nazwy_kolumn = {"Id ", "Imie", "Nazwisko","Stanowisko","Nr Telefonu","Email","wyksztalcenie","poczatek_pracy","koniec_pracy","pensja","adresId"};
	private List<Pracownik> pracownicy;
	public PracownikTable(List<Pracownik> pracowniki) {
		pracownicy = pracowniki;
	}
	public int getColumnCount() {
		return nazwy_kolumn.length;
	}
	public int getRowCount() {
		return pracownicy.size();
	}
	public String getColumnName(int col) {
		return nazwy_kolumn[col];
	}
	
	public Object getValueAt(int row, int col) {
		Pracownik tempPracownik = pracownicy.get(row);
		switch(col) {
		case ID_COL:
			return tempPracownik.getId();
		case FIRST_NAME_COL:
			return tempPracownik.getImie();
		case LAST_NAME_COL:
			return tempPracownik.getNazwisko();
		case STANOWISKO_COL:
			return tempPracownik.getStanowisko();
		case NR_TELEFONU_COL:
			return tempPracownik.getNr_telefonu();
		case EMAIL_COL:
			return tempPracownik.getEmail();
		case WYKSZTALCENIE_COL:
			return tempPracownik.getWyksztalcenie();
		case POCZATEK_PRACY_COL:
			return tempPracownik.getPoczatek_pracy();
		case KONIEC_PRACY_COL:
			return tempPracownik.getKoniec_pracy();
		case PENSJA_COL:
			return tempPracownik.getPensja();
		case ADRESID_COL:
			return tempPracownik.getAdres_id();
		case OBJECT_COL:
			return tempPracownik;
		default:
			return tempPracownik.getNazwisko();
		}
	}
	/*public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}