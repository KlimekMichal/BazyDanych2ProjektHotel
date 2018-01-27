import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Nowy_Pracownik extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField imieTextField;
	private JTextField nazwiskoTextField;
	private JTextField wyksztalcenieTextField;
	private JTextField poczatekPracyTextField;
	private JTextField koniecPracyTextField;
	private JTextField pensjaTextField;
	private JTextField adresIDTextField;
	private JTextField textField_7;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private PracownikDAO pracownikDAO;
	private ListaPracownikow listaPracownikow;
	
	private Pracownik previousPracownik = null;
	private boolean updateMode = false;
	private JTextField stanowiskoTextField;
	private JTextField nrTelefonuTextField;
	private JTextField emailTextField;
	/**
	 * Launch the application.
	 */
	public Nowy_Pracownik(ListaPracownikow tlistaPracownikow, PracownikDAO tpracownikDAO, Pracownik tpreviousPracownik, boolean tupdateMode) {
		this();
		pracownikDAO = tpracownikDAO;
		listaPracownikow = tlistaPracownikow;
		previousPracownik = tpreviousPracownik;
		updateMode = tupdateMode;
		if (updateMode) {
			setTitle("Update pracownik");
			populateGui(previousPracownik);
		}
	}
	private void populateGui(Pracownik pracownik) {
		imieTextField.setText(pracownik.getImie());
		nazwiskoTextField.setText(pracownik.getNazwisko());
		stanowiskoTextField.setText(pracownik.getStanowisko());
		nrTelefonuTextField.setText(pracownik.getNr_telefonu());
		emailTextField.setText(pracownik.getEmail());
		wyksztalcenieTextField.setText(pracownik.getWyksztalcenie());
		poczatekPracyTextField.setText(String.valueOf(pracownik.getPoczatek_pracy()));
		koniecPracyTextField.setText(String.valueOf(pracownik.getKoniec_pracy()));
		pensjaTextField.setText(String.valueOf(pracownik.getPensja()));
		adresIDTextField.setText(String.valueOf(pracownik.getAdres_id()));
	}
	public static void main(String[] args) {
		try {
			Nowy_Pracownik dialog = new Nowy_Pracownik();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Nowy_Pracownik() {
		setTitle("Dodawanie pracownik\u00F3w");
		setBounds(100, 100, 450, 538);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Imie:");
			lblNewLabel.setBounds(10, 11, 83, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
			lblNewLabel_1.setBounds(10, 36, 83, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblStanowisko = new JLabel("Wyksztalcenie:");
			lblStanowisko.setBounds(10, 136, 83, 14);
			contentPanel.add(lblStanowisko);
		}
		{
			JLabel lblPocztekPracy = new JLabel("Pocz\u0105tek pracy:");
			lblPocztekPracy.setBounds(10, 161, 83, 14);
			contentPanel.add(lblPocztekPracy);
		}
		{
			JLabel lblKoniecPracy = new JLabel("Koniec pracy:");
			lblKoniecPracy.setBounds(10, 186, 83, 14);
			contentPanel.add(lblKoniecPracy);
		}
		{
			JLabel lblPensja = new JLabel("Pensja:");
			lblPensja.setBounds(10, 211, 83, 14);
			contentPanel.add(lblPensja);
		}
		{
			imieTextField = new JTextField();
			imieTextField.setBounds(103, 8, 167, 20);
			contentPanel.add(imieTextField);
			imieTextField.setColumns(10);
		}
		{
			JLabel lblAdresId = new JLabel("Adres ID:");
			lblAdresId.setBounds(10, 236, 83, 14);
			contentPanel.add(lblAdresId);
		}
		{
			nazwiskoTextField = new JTextField();
			nazwiskoTextField.setBounds(103, 33, 167, 20);
			contentPanel.add(nazwiskoTextField);
			nazwiskoTextField.setColumns(10);
		}
		{
			wyksztalcenieTextField = new JTextField();
			wyksztalcenieTextField.setBounds(103, 133, 167, 20);
			contentPanel.add(wyksztalcenieTextField);
			wyksztalcenieTextField.setColumns(10);
		}
		{
			poczatekPracyTextField = new JTextField();
			poczatekPracyTextField.setBounds(103, 158, 167, 20);
			contentPanel.add(poczatekPracyTextField);
			poczatekPracyTextField.setColumns(10);
		}
		{
			koniecPracyTextField = new JTextField();
			koniecPracyTextField.setBounds(103, 183, 167, 20);
			contentPanel.add(koniecPracyTextField);
			koniecPracyTextField.setColumns(10);
		}
		{
			pensjaTextField = new JTextField();
			pensjaTextField.setBounds(103, 208, 167, 20);
			contentPanel.add(pensjaTextField);
			pensjaTextField.setColumns(10);
		}
		{
			adresIDTextField = new JTextField();
			adresIDTextField.setBounds(103, 233, 49, 20);
			contentPanel.add(adresIDTextField);
			adresIDTextField.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			
			panel.setBounds(10, 295, 414, 161);
			contentPanel.add(panel);
			panel.setVisible(false);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_2 = new JLabel("Dane do Bazy Danych");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setBounds(115, 0, 188, 44);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblLogin = new JLabel("Login:");
				lblLogin.setBounds(10, 58, 46, 14);
				panel.add(lblLogin);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Has\u0142o:");
				lblNewLabel_3.setBounds(10, 83, 36, 14);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Poziom dost\u0119pu:");
				lblNewLabel_4.setBounds(10, 136, 88, 14);
				panel.add(lblNewLabel_4);
			}
			{
				textField_7 = new JTextField();
				textField_7.setBounds(96, 55, 120, 20);
				panel.add(textField_7);
				textField_7.setColumns(10);
			}
			
			passwordField = new JPasswordField();
			passwordField.setBounds(96, 80, 120, 20);
			panel.add(passwordField);
			
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(96, 105, 120, 20);
			panel.add(passwordField_1);
			
			JLabel lblPowtrzHaso = new JLabel("Powt\u00F3rz Has\u0142o:");
			lblPowtrzHaso.setBounds(10, 108, 78, 14);
			panel.add(lblPowtrzHaso);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(250, 55, 154, 95);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("Poziomy dost\u0119pu");
			lblNewLabel_5.setBounds(37, 11, 88, 14);
			panel_1.add(lblNewLabel_5);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Menad\u017Cer");
			rdbtnNewRadioButton.setBounds(6, 32, 109, 23);
			panel_1.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Recepcjonista");
			rdbtnNewRadioButton_1.setBounds(6, 58, 109, 23);
			panel_1.add(rdbtnNewRadioButton_1);
			
			JLabel lblNewLabel_8 = new JLabel("");
			lblNewLabel_8.setBounds(96, 136, 120, 14);
			panel.add(lblNewLabel_8);
		}
		{
			JLabel lblStanowisko_1 = new JLabel("Stanowisko");
			lblStanowisko_1.setBounds(10, 61, 67, 14);
			contentPanel.add(lblStanowisko_1);
		}
		{
			JLabel lblNrTelefonu = new JLabel("Nr telefonu");
			lblNrTelefonu.setBounds(10, 86, 67, 14);
			contentPanel.add(lblNrTelefonu);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(10, 111, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			stanowiskoTextField = new JTextField();
			stanowiskoTextField.setBounds(103, 58, 167, 20);
			contentPanel.add(stanowiskoTextField);
			stanowiskoTextField.setColumns(10);
		}
		{
			nrTelefonuTextField = new JTextField();
			nrTelefonuTextField.setBounds(103, 83, 167, 20);
			contentPanel.add(nrTelefonuTextField);
			nrTelefonuTextField.setColumns(10);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(103, 108, 167, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						savePracownik();
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
	protected void savePracownik() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String imie = imieTextField.getText();
		String nazwisko = nazwiskoTextField.getText();
		String stanowisko = stanowiskoTextField.getText();
		String nr_telefonu = nrTelefonuTextField.getText();
		String email = emailTextField.getText();
		String wyksztalcenie = wyksztalcenieTextField.getText();
		java.util.Date poczatek = null;
		java.util.Date koniec = null;
		try {
			poczatek = format.parse(poczatekPracyTextField.getText());
			koniec = format.parse(koniecPracyTextField.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date pocz = new Date(poczatek.getTime());
		java.sql.Date kon = new Date(koniec.getTime());
		float pensja = Float.parseFloat(pensjaTextField.getText());
		String adid = adresIDTextField.getText();
		int adresid = Integer.parseInt(adid);
		Pracownik tempPracownik = null;
		if (updateMode) {
			tempPracownik = previousPracownik;
			tempPracownik.setImie(imie);
			tempPracownik.setNazwisko(nazwisko);
			tempPracownik.setStanowisko(stanowisko);
			tempPracownik.setNr_telefonu(nr_telefonu);
			tempPracownik.setEmail(email);
			tempPracownik.setWyksztalcenie(wyksztalcenie);
			tempPracownik.setPoczatek_pracy((Date) pocz);
			tempPracownik.setKoniec_pracy((Date) kon);
			tempPracownik.setPensja(pensja);
			tempPracownik.setAdres_id(adresid);
		} else {
			tempPracownik = new Pracownik(5,imie,nazwisko, stanowisko,nr_telefonu,email,wyksztalcenie,pocz,kon,pensja,adresid);
		}
		try {
			if (updateMode) {
				pracownikDAO.updatePracownik(tempPracownik);
			} else {
				pracownikDAO.addPracownik(tempPracownik);			
			}
			setVisible(false);
			dispose();
			listaPracownikow.refreshPracownik();
			if(updateMode) {
			JOptionPane.showMessageDialog(
					listaPracownikow, 
					"Pracownik dodany poprawnie.",
					"DodanoPracownika", 
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						listaPracownikow, 
						"Pracownik edytowany poprawnie.",
						"DodanoPracownika", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					listaPracownikow,
					"B³¹d podczas dodawania Pracownika: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
