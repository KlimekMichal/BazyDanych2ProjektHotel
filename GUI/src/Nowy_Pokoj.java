import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Nowy_Pokoj extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField liczbalozekTextField;
	private JTextField typlozekTextField;
	private JTextField cenaTextField;
	private JTextField statusTextField;
	private JTextField tidrezerwacjiTextField;
	
	private PokojDAO pokojDAO;
	private ListaPokoi listaPokoi;
	
	private Pokoj previousPokoj = null;
	private boolean updateMode = false;
	public Nowy_Pokoj(ListaPokoi tlistaPokojow, PokojDAO tPokojDAO, Pokoj tpreviousPokoj, boolean tupdateMode) {
		this();
		pokojDAO = tPokojDAO;
		listaPokoi = tlistaPokojow;
		previousPokoj = tpreviousPokoj;
		updateMode = tupdateMode;
		if (updateMode) {
			setTitle("Update Pokoj");
			populateGui(previousPokoj);
		}
	}
	private void populateGui(Pokoj pokoj) {
		liczbalozekTextField.setText(String.valueOf(pokoj.getLiczba_lozek()));
		typlozekTextField.setText(pokoj.getTyp_lozek());
		cenaTextField.setText(String.valueOf(pokoj.getCena()));
		statusTextField.setText(pokoj.getStatus());
		tidrezerwacjiTextField.setText(String.valueOf(pokoj.getRezerwacjaid()));
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Nowy_Pokoj dialog = new Nowy_Pokoj();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Nowy_Pokoj() {
		setTitle("Pok\u00F3j- Edycja");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Liczba \u0142\u00F3\u017Cek");
			lblNewLabel.setBounds(10, 11, 74, 14);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Typ \u0142\u00F3\u017Cek");
		lblNewLabel_1.setBounds(10, 36, 74, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cena");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 86, 46, 14);
		contentPanel.add(lblStatus);
		
		JLabel lblIdRezerwacji = new JLabel("Id rezerwacji");
		lblIdRezerwacji.setBounds(10, 111, 74, 14);
		contentPanel.add(lblIdRezerwacji);
		
		liczbalozekTextField = new JTextField();
		liczbalozekTextField.setBounds(94, 8, 65, 20);
		contentPanel.add(liczbalozekTextField);
		liczbalozekTextField.setColumns(10);
		
		typlozekTextField = new JTextField();
		typlozekTextField.setBounds(94, 33, 86, 20);
		contentPanel.add(typlozekTextField);
		typlozekTextField.setColumns(10);
		
		cenaTextField = new JTextField();
		cenaTextField.setBounds(94, 58, 86, 20);
		contentPanel.add(cenaTextField);
		cenaTextField.setColumns(10);
		
		statusTextField = new JTextField();
		statusTextField.setBounds(94, 83, 86, 20);
		contentPanel.add(statusTextField);
		statusTextField.setColumns(10);
		
		tidrezerwacjiTextField = new JTextField();
		tidrezerwacjiTextField.setBounds(94, 108, 86, 20);
		contentPanel.add(tidrezerwacjiTextField);
		tidrezerwacjiTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						savePokoj();
					}
				});
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
	protected void savePokoj() {
		int liczba_lozek = Integer.parseInt(liczbalozekTextField.getText());
		String typ_lozek = typlozekTextField.getText();
		float cena = Float.parseFloat(cenaTextField.getText());
		String status = statusTextField.getText();
		int idrezerwacji = Integer.parseInt(tidrezerwacjiTextField.getText());
		Pokoj tempPokoj = null;
		if (updateMode) {
			tempPokoj = previousPokoj;
			tempPokoj.setLiczba_lozek(liczba_lozek);
			tempPokoj.setTyp_lozek(typ_lozek);
			tempPokoj.setCena(cena);
			tempPokoj.setStatus(status);
			tempPokoj.setRezerwacjaid(idrezerwacji);
		} else {
			tempPokoj = new Pokoj(5,liczba_lozek,typ_lozek,cena,status,idrezerwacji);
		}
		try {
			if (updateMode) {
				pokojDAO.updatePokoj(tempPokoj);
			} else {
				pokojDAO.addPokoj(tempPokoj);			
			}
			setVisible(false);
			dispose();
			listaPokoi.refreshPokoj();
			if(updateMode) {
			JOptionPane.showMessageDialog(
					listaPokoi, 
					"Pokoj edytowany poprawnie.",
					"DodanoPokój", 
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						listaPokoi, 
						"Pokoj dodany poprawnie.",
						"DodanoPokój", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					listaPokoi,
					"B³¹d w czasie interakcji z Pokojem: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
