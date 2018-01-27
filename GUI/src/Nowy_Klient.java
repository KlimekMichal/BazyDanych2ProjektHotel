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

public class Nowy_Klient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField imieTextField;
	private JTextField nazwiskoTextField;
	private JTextField nrTelefonuTextField;
	private JTextField emailTextField;
	private JTextField adresIdTextField;

	private KlientDAO klientDAO;
	private ListaKlientow listaKlientow;
	
	private Klient previousKlient = null;
	private boolean updateMode = false;
	public Nowy_Klient(ListaKlientow tlistaKlientow, KlientDAO tklientDAO, Klient tpreviousKlient, boolean tupdateMode) {
		this();
		klientDAO = tklientDAO;
		listaKlientow = tlistaKlientow;
		previousKlient = tpreviousKlient;
		updateMode = tupdateMode;
		if (updateMode) {
			setTitle("Update Klient");
			populateGui(previousKlient);
		}
	}
	private void populateGui(Klient klient) {
		imieTextField.setText(klient.getImie());
		nazwiskoTextField.setText(klient.getNazwisko());
		nrTelefonuTextField.setText(klient.getNr_tel());
		emailTextField.setText(klient.getEmail());
		adresIdTextField.setText(String.valueOf(klient.getAdresid()));
	}
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Nowy_Klient dialog = new Nowy_Klient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Nowy_Klient() {
		setTitle("Dodawanie klienta");
		setBounds(100, 100, 391, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(10, 11, 82, 14);
		contentPanel.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 36, 82, 14);
		contentPanel.add(lblNazwisko);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 86, 82, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblNrTelefonu = new JLabel("Nr telefonu");
		lblNrTelefonu.setBounds(10, 61, 82, 14);
		contentPanel.add(lblNrTelefonu);
		
		imieTextField = new JTextField();
		imieTextField.setBounds(102, 8, 179, 20);
		contentPanel.add(imieTextField);
		imieTextField.setColumns(10);
		
		nazwiskoTextField = new JTextField();
		nazwiskoTextField.setBounds(102, 33, 179, 20);
		contentPanel.add(nazwiskoTextField);
		nazwiskoTextField.setColumns(10);
		
		nrTelefonuTextField = new JTextField();
		nrTelefonuTextField.setBounds(102, 58, 179, 20);
		contentPanel.add(nrTelefonuTextField);
		nrTelefonuTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(102, 83, 179, 20);
		contentPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblIdAdresu = new JLabel("Id Adresu");
		lblIdAdresu.setBounds(10, 111, 82, 14);
		contentPanel.add(lblIdAdresu);
		
		adresIdTextField = new JTextField();
		adresIdTextField.setBounds(102, 108, 25, 20);
		contentPanel.add(adresIdTextField);
		adresIdTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveKlient();
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
	protected void saveKlient() {
		String imie = imieTextField.getText();
		String nazwisko = nazwiskoTextField.getText();
		String nr_telefonu = nrTelefonuTextField.getText();
		String email = emailTextField.getText();
		String adid = adresIdTextField.getText();
		int adresid = Integer.parseInt(adid);
		Klient tempKlient = null;
		if (updateMode) {
			tempKlient = previousKlient;
			tempKlient.setImie(imie);
			tempKlient.setNazwisko(nazwisko);
			tempKlient.setNr_tel(nr_telefonu);
			tempKlient.setEmail(email);
			tempKlient.setAdresid(adresid);
		} else {
			tempKlient = new Klient(5,imie,nazwisko,nr_telefonu,email,adresid);
		}
		try {
			if (updateMode) {
				klientDAO.updateKlient(tempKlient);
			} else {
				klientDAO.addKlient(tempKlient);			
			}
			setVisible(false);
			dispose();
			listaKlientow.refreshKlient();
			if(updateMode) {
			JOptionPane.showMessageDialog(
					listaKlientow, 
					"Klient edytowany poprawnie.",
					"DodanoKlienta", 
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						listaKlientow, 
						"Klient dodany poprawnie.",
						"DodanoKlienta", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					listaKlientow,
					"B³¹d w czasie interakcji z klientem: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
