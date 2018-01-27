import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AdresTable extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int ULICA_COL = 1;
	private static final int NR_DOMU_COL = 2;
	private static final int NR_MIESZKANIA_COL = 3;
	private static final int KOD_POCZTOWY_COL = 4;
	private static final int MIASTO_COL = 5;
	private static final int PANSTWO_COL = 6;
	
	private String[] nazwy_kolumn = {"Id ", "Imie", "Nazwisko","Nr Telefonu","Email","adresId"};
	private List<Adres> klienci;
	public AdresTable(List<Adres> Adresy) {
		klienci = Adresy;
	}
	public int getColumnCount() {
		return nazwy_kolumn.length;
	}
	public int getRowCount() {
		return klienci.size();
	}
	public String getColumnName(int col) {
		return nazwy_kolumn[col];
	}
	
	public Object getValueAt(int row, int col) {
		Adres tempAdres = klienci.get(row);
		switch(col) {
		case ID_COL:
			return tempAdres.getId();
		case ULICA_COL:
			return tempAdres.getUlica();
		case NR_DOMU_COL:
			return tempAdres.getNr_domu();
		case NR_MIESZKANIA_COL:
			return tempAdres.getNr_mieszkania();
		case KOD_POCZTOWY_COL:
			return tempAdres.getKod_pocztowy();
		case MIASTO_COL:
			return tempAdres.getMiasto();
		case PANSTWO_COL:
			return tempAdres.getPanstwo();
		case OBJECT_COL:
			return tempAdres;
		default:
			return tempAdres.getUlica();
		}
	}
	/*public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}
