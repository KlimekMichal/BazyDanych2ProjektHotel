import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaRezerwacji extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	
	private RezerwacjaDAO rezerwacjaDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaRezerwacji dialog = new ListaRezerwacji();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaRezerwacji() {
		try {
			rezerwacjaDAO = new RezerwacjaDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		setBounds(100, 100, 683, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Id Klienta:");
			lblNewLabel.setBounds(10, 11, 72, 21);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(64, 11, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton btnSzukaj = new JButton("Szukaj");
			btnSzukaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String numer = textField.getText();
					List<Rezerwacja> rezerwacja = null;
					try {
						if(numer != null && numer.trim().length() > 0) {
							rezerwacja = rezerwacjaDAO.searchRezerwacja(numer);
						} else {
							rezerwacja = rezerwacjaDAO.getAllRezerwacja();
						}	
						RezerwacjaTable model = new RezerwacjaTable(rezerwacja);
						table.setModel(model);

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(ListaRezerwacji.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnSzukaj.setBounds(160, 10, 89, 23);
			contentPanel.add(btnSzukaj);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 43, 647, 175);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setColumnHeaderView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUsuRezerwacje = new JButton("Usu\u0144 Rezerwacje");
				btnUsuRezerwacje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int row = table.getSelectedRow();
							if (row<0) {
								JOptionPane.showMessageDialog(ListaRezerwacji.this, "Musisz wybraæ rezerwacje", "ERROR", JOptionPane.ERROR_MESSAGE );
								return;
							}
							int response = JOptionPane.showConfirmDialog(ListaRezerwacji.this, "Czy na pewno chcesz usun¹æ t¹ rezerwacjê?", "PotwierdŸ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							
							if (response!=JOptionPane.YES_OPTION) {
								return;
							}
							
							Rezerwacja tempRezerwacja = (Rezerwacja) table.getValueAt(row, RezerwacjaTable.OBJECT_COL);
							rezerwacjaDAO.deleteRezerwacja(tempRezerwacja.getId());
							refreshRezerwacja();
							JOptionPane.showMessageDialog(ListaRezerwacji.this, "Rezerwacja usuniêta pomyœlnie", "Rezerwacja usuniêta", JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(ListaRezerwacji.this, "B³¹d w czasie usuwania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.add(btnUsuRezerwacje);
			}
			{
				JButton btnEdytujRezerwacje = new JButton("Edytuj Rezerwacje");
				btnEdytujRezerwacje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = table.getSelectedRow();
						if(row<0) {
							JOptionPane.showMessageDialog(ListaRezerwacji.this, "Musisz wybraæ Rezerwacjê", "Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						Rezerwacja tempRezerwacja = (Rezerwacja) table.getValueAt(row, RezerwacjaTable.OBJECT_COL);
						NowaRezerwacja dialog = new NowaRezerwacja(ListaRezerwacji.this, rezerwacjaDAO, tempRezerwacja, true);
						dialog.setVisible(true);
						
					}
				});
				buttonPane.add(btnEdytujRezerwacje);
			}
			{
				JButton btnDodajRezerwacje = new JButton("Dodaj Rezerwacje");
				btnDodajRezerwacje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Rezerwacja tempRezerwacja = null;
						NowaRezerwacja dialog = new NowaRezerwacja(ListaRezerwacji.this, rezerwacjaDAO, tempRezerwacja , false);
						dialog.setVisible(true);
					}
				});
				buttonPane.add(btnDodajRezerwacje);
			}
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
	public void refreshRezerwacja() {
		List<Rezerwacja> rezerwacja = null;
		try {
			rezerwacja = rezerwacjaDAO.getAllRezerwacja();
			RezerwacjaTable model = new RezerwacjaTable(rezerwacja);
			table.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
