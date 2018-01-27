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

public class ListaPokoi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private PokojDAO pokojDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaPokoi dialog = new ListaPokoi();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaPokoi() {
		try {
			pokojDAO = new PokojDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		setBounds(100, 100, 646, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Typ \u0142\u00F3\u017Cek");
			lblNewLabel.setBounds(10, 11, 63, 14);
			contentPanel.add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setBounds(83, 8, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Szukaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typ_lozek = textField.getText();
				List<Pokoj> pokoj = null;
				try {
					if(typ_lozek != null && typ_lozek.trim().length() > 0) {
						pokoj = pokojDAO.searchPokoj(typ_lozek);
					} else {
						pokoj = pokojDAO.getAllPokoj();
					}	
					PokojTable model = new PokojTable(pokoj);
					table.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ListaPokoi.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(179, 7, 89, 23);
		contentPanel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 610, 179);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnUsuPokj = new JButton("Usu\u0144 Pok\u00F3j");
			btnUsuPokj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int row = table.getSelectedRow();
						if (row<0) {
							JOptionPane.showMessageDialog(ListaPokoi.this, "Musisz wybraæ Pokoja", "ERROR", JOptionPane.ERROR_MESSAGE );
							return;
						}
						int response = JOptionPane.showConfirmDialog(ListaPokoi.this, "Czy na pewno chcesz usun¹æ ten Pokój?", "PotwierdŸ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						
						if (response!=JOptionPane.YES_OPTION) {
							return;
						}
						
						Pokoj tempPokoj = (Pokoj) table.getValueAt(row, PokojTable.OBJECT_COL);
						pokojDAO.deletePokoj(tempPokoj.getId());
						refreshPokoj();
						JOptionPane.showMessageDialog(ListaPokoi.this, "Pokój usuniêty pomyœlnie", "Pokój usuniêty", JOptionPane.INFORMATION_MESSAGE);
						
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(ListaPokoi.this, "B³¹d w czasie usuwania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			buttonPane.add(btnUsuPokj);
			
			JButton btnEdytujPokj = new JButton("Edytuj Pok\u00F3j");
			btnEdytujPokj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row<0) {
						JOptionPane.showMessageDialog(ListaPokoi.this, "Musisz wybraæ Pokoja", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					Pokoj tempPokoj = (Pokoj) table.getValueAt(row, PokojTable.OBJECT_COL);
					Nowy_Pokoj dialog = new Nowy_Pokoj(ListaPokoi.this, pokojDAO, tempPokoj, true);
					dialog.setVisible(true);
				}
			});
			buttonPane.add(btnEdytujPokj);
			
			JButton btnDodajPokj = new JButton("Dodaj Pok\u00F3j");
			btnDodajPokj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pokoj tempPokoj = null;
					Nowy_Pokoj dialog = new Nowy_Pokoj(ListaPokoi.this, pokojDAO, tempPokoj , false);
					dialog.setVisible(true);
				}
			});
			buttonPane.add(btnDodajPokj);
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
	public void refreshPokoj() {
		List<Pokoj> pokoj = null;
		try {
			pokoj = pokojDAO.getAllPokoj();
			PokojTable model = new PokojTable(pokoj);
			table.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
