import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ListaPracownikow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private PracownikDAO pracownikDAO;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaPracownikow dialog = new ListaPracownikow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaPracownikow() {
		try {
			pracownikDAO = new PracownikDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		setBounds(100, 100, 655, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Podaj Nazwisko:");
		lblNewLabel.setBounds(10, 11, 92, 28);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(112, 15, 116, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Szukaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazwisko = textField.getText();
				List<Pracownik> pracownik = null;
				try {
					if(nazwisko != null && nazwisko.trim().length() > 0) {
						pracownik = pracownikDAO.searchPracownik(nazwisko);
					} else {
						pracownik = pracownikDAO.getAllPracownik();
					}	
					PracownikTable model = new PracownikTable(pracownik);
					table.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ListaPracownikow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(238, 14, 89, 23);
		contentPanel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 619, 168);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnUsuPracownika = new JButton("Usu\u0144 Pracownika");
			btnUsuPracownika.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int row = table.getSelectedRow();
						if (row<0) {
							JOptionPane.showMessageDialog(ListaPracownikow.this, "Musisz wybraæ pracownika", "ERROR", JOptionPane.ERROR_MESSAGE );
							return;
						}
						int response = JOptionPane.showConfirmDialog(ListaPracownikow.this, "Czy na pewno chcesz usun¹æ tego klienta?", "PotwierdŸ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						
						if (response!=JOptionPane.YES_OPTION) {
							return;
						}
						
						Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikTable.OBJECT_COL);
						pracownikDAO.deletePracownik(tempPracownik.getId());
						refreshPracownik();
						JOptionPane.showMessageDialog(ListaPracownikow.this, "Pracownik usuniêty pomyœlnie", "Pracownik usuniêty", JOptionPane.INFORMATION_MESSAGE);
						
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(ListaPracownikow.this, "B³¹d w czasie usuwania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			buttonPane.add(btnUsuPracownika);
			
			JButton btnEdytujPracownika = new JButton("Edytuj Pracownika");
			btnEdytujPracownika.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row<0) {
						JOptionPane.showMessageDialog(ListaPracownikow.this, "Musisz wybraæ Klienta", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikTable.OBJECT_COL);
					Nowy_Pracownik dialog = new Nowy_Pracownik(ListaPracownikow.this, pracownikDAO, tempPracownik, true);
					dialog.setVisible(true);
				}
			});
			buttonPane.add(btnEdytujPracownika);
			
			JButton btnDodajPracownika = new JButton("Dodaj Pracownika");
			btnDodajPracownika.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pracownik tempPracownik = null;
					Nowy_Pracownik dialog = new Nowy_Pracownik(ListaPracownikow.this, pracownikDAO, tempPracownik , false);
					dialog.setVisible(true);
				}
			});
			buttonPane.add(btnDodajPracownika);
			{
				JButton okButton = new JButton("OK");
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
	public void refreshPracownik() {
		List<Pracownik> pracownik = null;
		try {
			pracownik = pracownikDAO.getAllPracownik();
			PracownikTable model = new PracownikTable(pracownik);
			table.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
