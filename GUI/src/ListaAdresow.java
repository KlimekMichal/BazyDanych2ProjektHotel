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

public class ListaAdresow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private AdresDAO adresDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaAdresow dialog = new ListaAdresow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaAdresow() {
		try {
			adresDAO = new AdresDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		setBounds(100, 100, 671, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPodajUlic = new JLabel("Podaj ulic\u0119:");
			lblPodajUlic.setBounds(10, 11, 74, 14);
			contentPanel.add(lblPodajUlic);
		}
		{
			textField = new JTextField();
			textField.setBounds(94, 8, 144, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton btnSzukaj = new JButton("Szukaj");
			btnSzukaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ulica = textField.getText();
					List<Adres> adres = null;
					try {
						if(ulica != null && ulica.trim().length() > 0) {
							adres = adresDAO.searchAdres(ulica);
						} else {
							adres = adresDAO.getAllAdres();
						}	
						AdresTable model = new AdresTable(adres);
						table.setModel(model);

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(ListaAdresow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnSzukaj.setBounds(248, 7, 89, 23);
			contentPanel.add(btnSzukaj);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 635, 182);
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
				JButton btnUsuAdres = new JButton("Usu\u0144 Adres");
				btnUsuAdres.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int row = table.getSelectedRow();
							if (row<0) {
								JOptionPane.showMessageDialog(ListaAdresow.this, "Musisz wybraæ adres", "ERROR", JOptionPane.ERROR_MESSAGE );
								return;
							}
							int response = JOptionPane.showConfirmDialog(ListaAdresow.this, "Czy na pewno chcesz usun¹æ ten adres?", "PotwierdŸ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							
							if (response!=JOptionPane.YES_OPTION) {
								return;
							}
							
							Adres tempAdres = (Adres) table.getValueAt(row, AdresTable.OBJECT_COL);
							adresDAO.deleteAdres(tempAdres.getId());
							refreshAdres();
							JOptionPane.showMessageDialog(ListaAdresow.this, "Adres usuniêty pomyœlnie", "Adres usuniêty", JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(ListaAdresow.this, "B³¹d w czasie usuwania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.add(btnUsuAdres);
			}
			{
				JButton btnEdytujAdres = new JButton("Edytuj Adres");
				btnEdytujAdres.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = table.getSelectedRow();
						if(row<0) {
							JOptionPane.showMessageDialog(ListaAdresow.this, "Musisz wybraæ Adres", "Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						Adres tempAdres = (Adres) table.getValueAt(row, AdresTable.OBJECT_COL);
						NowyAdres dialog = new NowyAdres(ListaAdresow.this, adresDAO, tempAdres, true);
						dialog.setVisible(true);
					}
				});
				buttonPane.add(btnEdytujAdres);
			}
			{
				JButton btnDodajAdres = new JButton("Dodaj Adres");
				btnDodajAdres.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Adres tempAdres = null;
						NowyAdres dialog = new NowyAdres(ListaAdresow.this, adresDAO, tempAdres , false);
						dialog.setVisible(true);
					}
				});
				buttonPane.add(btnDodajAdres);
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
	public void refreshAdres() {
		List<Adres> adres = null;
		try {
			adres = adresDAO.getAllAdres();
			AdresTable model = new AdresTable(adres);
			table.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
