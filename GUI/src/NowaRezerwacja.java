import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class NowaRezerwacja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nrPokojuTextField;
	private JTextField statusTextField;
	private JTextField poczatekTextField;
	private JTextField koniecTextField;
	private JTextField idklientaTextField;
	private CallableStatement myStmt;
	private boolean flaga=false;
	private boolean blaga=false;
	private RezerwacjaDAO rezerwacjaDAO;
	private ListaRezerwacji listaRezerwacji;
	
	private Rezerwacja previousRezerwacja = null;
	private boolean updateMode = false;
	private JTextField daneTextField;
	public NowaRezerwacja(ListaRezerwacji tlistaRezerwacji, RezerwacjaDAO tRezerwacjaDAO, Rezerwacja tpreviousRezerwacja, boolean tupdateMode) {
		this();
		rezerwacjaDAO = tRezerwacjaDAO;
		listaRezerwacji = tlistaRezerwacji;
		previousRezerwacja = tpreviousRezerwacja;
		updateMode = tupdateMode;
		if (updateMode) {
			setTitle("Update Rezerwacja");
			populateGui(previousRezerwacja);
		}
	}
	private void populateGui(Rezerwacja Rezerwacja) {
		nrPokojuTextField.setText(String.valueOf(Rezerwacja.getNr_pokoju()));
		statusTextField.setText(Rezerwacja.getStatus());
		poczatekTextField.setText(String.valueOf(Rezerwacja.getData_zameldowania()));
		koniecTextField.setText(String.valueOf(Rezerwacja.getData_wymeldowania()));
		daneTextField.setText(Rezerwacja.getDane_o_rezydentach());
		idklientaTextField.setText(String.valueOf(Rezerwacja.getKlientid()));
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NowaRezerwacja dialog = new NowaRezerwacja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NowaRezerwacja() {
		setTitle("Nowa Rezerwacja");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nr pokoju");
		lblNewLabel.setBounds(10, 11, 54, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dane o rezyd.");
		lblNewLabel_1.setBounds(10, 111, 77, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblIdentyfikator = new JLabel("status");
		lblIdentyfikator.setBounds(10, 36, 69, 14);
		contentPanel.add(lblIdentyfikator);
		
		nrPokojuTextField = new JTextField();
		nrPokojuTextField.setBounds(104, 8, 170, 20);
		contentPanel.add(nrPokojuTextField);
		nrPokojuTextField.setColumns(10);
		
		statusTextField = new JTextField();
		statusTextField.setBounds(104, 33, 170, 21);
		contentPanel.add(statusTextField);
		statusTextField.setColumns(10);
		
		JButton btnListaKlientw = new JButton("Lista Klient\u00F3w");
		btnListaKlientw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaKlientow lista = new ListaKlientow();
				lista.setVisible(true);
			}
		});
		btnListaKlientw.setBounds(289, 57, 109, 23);
		contentPanel.add(btnListaKlientw);
		
		JLabel lblDataMeldunku = new JLabel("Pocz\u0105tek");
		lblDataMeldunku.setBounds(10, 61, 77, 14);
		contentPanel.add(lblDataMeldunku);
		
		JLabel lblDataWymeldowania = new JLabel("Koniec");
		lblDataWymeldowania.setBounds(10, 86, 100, 14);
		contentPanel.add(lblDataWymeldowania);
		
		poczatekTextField = new JTextField();
		poczatekTextField.setBounds(104, 58, 170, 20);
		contentPanel.add(poczatekTextField);
		poczatekTextField.setColumns(10);
		
		koniecTextField = new JTextField();
		koniecTextField.setBounds(104, 83, 170, 20);
		contentPanel.add(koniecTextField);
		koniecTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Lista Rezerwacji");
		btnNewButton.setBounds(289, 82, 109, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nowy Klient");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nowy_Klient nwk = new Nowy_Klient();
				nwk.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(289, 7, 109, 23);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblIdKlienta = new JLabel("Id Klienta");
		lblIdKlienta.setBounds(10, 186, 46, 14);
		contentPanel.add(lblIdKlienta);
		
		idklientaTextField = new JTextField();
		idklientaTextField.setBounds(104, 183, 46, 20);
		contentPanel.add(idklientaTextField);
		idklientaTextField.setColumns(10);
		
		JLabel lblCena = new JLabel("Cena:");
		lblCena.setBounds(10, 204, 46, 14);
		contentPanel.add(lblCena);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(50, 161, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		daneTextField = new JTextField();
		daneTextField.setBounds(104, 108, 170, 20);
		contentPanel.add(daneTextField);
		daneTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveRezerwacja();
					}
				});
				
				JButton btnWymelduj = new JButton("Wymelduj");
				btnWymelduj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						blaga=true;
						saveRezerwacja();
						blaga=false;
					}
				});
				buttonPane.add(btnWymelduj);
				
				JButton btnZamelduj = new JButton("Zamelduj");
				btnZamelduj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						flaga=true;
						saveRezerwacja();
						flaga=false;
						
					}
				});
				buttonPane.add(btnZamelduj);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	protected void saveRezerwacja() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int nr_pokoju = Integer.parseInt(nrPokojuTextField.getText());
		String status = statusTextField.getText();
		java.util.Date poczatek = null;
		java.util.Date koniec = null;
		LocalDate datep=null;
		LocalDate datek=null;
		java.sql.Date pocz=null;
		java.sql.Date kon=null;
		try {
			poczatek = format.parse(poczatekTextField.getText());
			koniec = format.parse(koniecTextField.getText());
			pocz = new Date(poczatek.getTime());
			kon = new Date(koniec.getTime());
			 datep = poczatek.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			 datek = koniec.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException exc) {
			JOptionPane.showMessageDialog(
					listaRezerwacji,
					"B³¹d podczas interakcji z Dat¹: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}

		String dane_o_rezydentach = daneTextField.getText();

		float cena = ChronoUnit.DAYS.between(datep,datek)*200;
		int idklienta = Integer.parseInt(idklientaTextField.getText());
		int idpracownika = 1;
		Rezerwacja tempRezerwacja = null;
		if (updateMode) {
			tempRezerwacja = previousRezerwacja;
			tempRezerwacja.setNr_pokoju(nr_pokoju);
			if (flaga || blaga) {
				if(flaga) {
					tempRezerwacja.setStatus("Zameld.");
				}
			} else if(blaga) {
				tempRezerwacja.setStatus("Wymeld.");
			} else {
				tempRezerwacja.setStatus(status);
			}
			tempRezerwacja.setData_zameldowania(pocz);
			tempRezerwacja.setData_wymeldowania(kon);
			tempRezerwacja.setDane_o_rezydentach(dane_o_rezydentach);
			tempRezerwacja.setCena(cena);
			tempRezerwacja.setKlientid(idklienta);
			tempRezerwacja.setPracownikid(idpracownika);
		} else {
			tempRezerwacja = new Rezerwacja(5,nr_pokoju,status,pocz,kon,dane_o_rezydentach,cena,idklienta,idpracownika);
		}
		try {
				boolean flag=rezerwacjaDAO.checking(pocz, kon, nr_pokoju);
				if(flag) {
			if (updateMode) {
				rezerwacjaDAO.updateRezerwacja(tempRezerwacja);
			} else {
				rezerwacjaDAO.addRezerwacja(tempRezerwacja);			
			}
			setVisible(false);
			dispose();
			listaRezerwacji.refreshRezerwacja();
			if(updateMode) {
			JOptionPane.showMessageDialog(
					listaRezerwacji, 
					"Rezerwacja edytowana poprawnie.",
					"EdytowanoRezerwacjê", 
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						listaRezerwacji, 
						"Rezerwacja dodana poprawnie.",
						"DodanoRezerwacjê", 
						JOptionPane.INFORMATION_MESSAGE);
			}
				} else {
					JOptionPane.showMessageDialog(listaRezerwacji, "TEN POKÓJ JEST ZAREZERWOWANY W TYM CZASIE","Error",JOptionPane.WARNING_MESSAGE);
				}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					listaRezerwacji,
					"B³¹d podczas interakcji z Rezerwacj¹: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
