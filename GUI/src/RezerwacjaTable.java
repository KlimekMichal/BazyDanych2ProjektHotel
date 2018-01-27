import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RezerwacjaTable extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int NR_POKOJU_COL = 1;
	private static final int STATUS_COL = 2;
	private static final int DATA_ZAMELDOWANIA_COL = 3;
	private static final int DATA_WYMELDOWANIA_COL = 4;
	private static final int DANE_O_REZYDENTACH_COL = 5;
	private static final int CENA_COL = 6;
	private static final int KLIENTID_COL = 7;
	private static final int PRACOWNIKID_COL = 8;
	
	private String[] nazwy_kolumn = {"Id ", "Nr pokoju", "status","data zameldowania","data wymeldowania","dane_o_rezydentach","cena","klientid","pracownikid"};
	private List<Rezerwacja> rezerwacje;
	public RezerwacjaTable(List<Rezerwacja> rezerwacjai) {
		rezerwacje = rezerwacjai;
	}
	public int getColumnCount() {
		return nazwy_kolumn.length;
	}
	public int getRowCount() {
		return rezerwacje.size();
	}
	public String getColumnName(int col) {
		return nazwy_kolumn[col];
	}
	
	public Object getValueAt(int row, int col) {
		Rezerwacja tempRezerwacja = rezerwacje.get(row);
		switch(col) {
		case ID_COL:
			return tempRezerwacja.getId();
		case NR_POKOJU_COL:
			return tempRezerwacja.getNr_pokoju();
		case STATUS_COL:
			return tempRezerwacja.getStatus();
		case DATA_ZAMELDOWANIA_COL:
			return tempRezerwacja.getData_zameldowania();
		case DATA_WYMELDOWANIA_COL:
			return tempRezerwacja.getData_wymeldowania();
		case DANE_O_REZYDENTACH_COL:
			return tempRezerwacja.getDane_o_rezydentach();
		case CENA_COL:
			return tempRezerwacja.getCena();
		case KLIENTID_COL:
			return tempRezerwacja.getKlientid();
		case PRACOWNIKID_COL:
			return tempRezerwacja.getPracownikid();
		case OBJECT_COL:
			return tempRezerwacja;
		default:
			return tempRezerwacja.getId();
		}
	}
	/*public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}