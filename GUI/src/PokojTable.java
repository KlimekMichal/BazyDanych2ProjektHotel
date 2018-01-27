import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PokojTable extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int LICZBA_LOZEK_COL = 1;
	private static final int TYP_LOZEK_COL = 2;
	private static final int CENA_COL = 3;
	private static final int STATUS_COL = 4;
	private static final int REZERWACJAID_COL = 5;
	
	private String[] nazwy_kolumn = {"Id ", "liczba ³ó¿ek", "typ ³ó¿ek","cena","status","Id rezerwacji"};
	private List<Pokoj> pokoje;
	public PokojTable(List<Pokoj> pokojy) {
		pokoje = pokojy;
	}
	public int getColumnCount() {
		return nazwy_kolumn.length;
	}
	public int getRowCount() {
		return pokoje.size();
	}
	public String getColumnName(int col) {
		return nazwy_kolumn[col];
	}
	
	public Object getValueAt(int row, int col) {
		Pokoj tempPokoj = pokoje.get(row);
		switch(col) {
		case ID_COL:
			return tempPokoj.getId();
		case LICZBA_LOZEK_COL:
			return tempPokoj.getLiczba_lozek();
		case TYP_LOZEK_COL:
			return tempPokoj.getTyp_lozek();
		case CENA_COL:
			return tempPokoj.getCena();
		case STATUS_COL:
			return tempPokoj.getStatus();
		case REZERWACJAID_COL:
			return tempPokoj.getRezerwacjaid();
		case OBJECT_COL:
			return tempPokoj;
		default:
			return tempPokoj.getTyp_lozek();
		}
	}
	/*public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}
