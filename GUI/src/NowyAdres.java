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

public class NowyAdres extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField ulicaTextField;
	private JTextField nrmieszkaniaTextField;
	private JTextField nrdomuTextField;
	private JTextField kodpocztowyTextField;
	private JTextField miastoTextField;
	private JTextField panstwoTextField;

	private AdresDAO adresDAO;
	private ListaAdresow listaAdresow;
	
	private Adres previousAdres = null;
	private boolean updateMode = false;
	public NowyAdres(ListaAdresow tlistaAdresow, AdresDAO tAdresDAO, Adres tpreviousAdres, boolean tupdateMode) {
		this();
		adresDAO = tAdresDAO;
		listaAdresow = tlistaAdresow;
		previousAdres = tpreviousAdres;
		updateMode = tupdateMode;
		if (updateMode) {
			setTitle("Update Adres");
			populateGui(previousAdres);
		}
	}
	private void populateGui(Adres adres) {
		ulicaTextField.setText(adres.getUlica());
		nrmieszkaniaTextField.setText(adres.getNr_mieszkania());
		nrdomuTextField.setText(adres.getNr_domu());
		kodpocztowyTextField.setText(adres.getKod_pocztowy());
		miastoTextField.setText(adres.getMiasto());
		panstwoTextField.setText(adres.getPanstwo());
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NowyAdres dialog = new NowyAdres();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NowyAdres() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ulica:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblNrMieszkania = new JLabel("Nr mieszkania");
			lblNrMieszkania.setBounds(10, 36, 80, 14);
			contentPanel.add(lblNrMieszkania);
		}
		{
			JLabel lblNrDomu = new JLabel("Nr domu");
			lblNrDomu.setBounds(10, 61, 46, 14);
			contentPanel.add(lblNrDomu);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Kod pocztowy");
			lblNewLabel_1.setBounds(10, 86, 80, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Miasto");
			lblNewLabel_2.setBounds(10, 111, 80, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Pa\u0144stwo");
			lblNewLabel_3.setBounds(10, 136, 46, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			ulicaTextField = new JTextField();
			ulicaTextField.setBounds(86, 8, 136, 20);
			contentPanel.add(ulicaTextField);
			ulicaTextField.setColumns(10);
		}
		{
			nrmieszkaniaTextField = new JTextField();
			nrmieszkaniaTextField.setBounds(86, 33, 86, 20);
			contentPanel.add(nrmieszkaniaTextField);
			nrmieszkaniaTextField.setColumns(10);
		}
		{
			nrdomuTextField = new JTextField();
			nrdomuTextField.setBounds(86, 58, 86, 20);
			contentPanel.add(nrdomuTextField);
			nrdomuTextField.setColumns(10);
		}
		{
			kodpocztowyTextField = new JTextField();
			kodpocztowyTextField.setBounds(86, 83, 86, 20);
			contentPanel.add(kodpocztowyTextField);
			kodpocztowyTextField.setColumns(10);
		}
		{
			miastoTextField = new JTextField();
			miastoTextField.setBounds(86, 108, 136, 20);
			contentPanel.add(miastoTextField);
			miastoTextField.setColumns(10);
		}
		{
			panstwoTextField = new JTextField();
			panstwoTextField.setBounds(86, 133, 136, 20);
			contentPanel.add(panstwoTextField);
			panstwoTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveAdres();
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
	protected void saveAdres() {
		String ulica = ulicaTextField.getText();
		String nrdomu = nrdomuTextField.getText();
		String nrmieszkania = nrmieszkaniaTextField.getText();
		String kodpocztowy = kodpocztowyTextField.getText();
		String miasto = miastoTextField.getText();
		String panstwo = panstwoTextField.getText();
		Adres tempAdres = null;
		if (updateMode) {
			tempAdres = previousAdres;
			tempAdres.setUlica(ulica);
			tempAdres.setNr_domu(nrdomu);
			tempAdres.setNr_mieszkania(nrmieszkania);
			tempAdres.setKod_pocztowy(kodpocztowy);
			tempAdres.setMiasto(miasto);
			tempAdres.setPanstwo(panstwo);
		} else {
			tempAdres = new Adres(5,ulica,nrdomu,nrmieszkania,kodpocztowy,miasto,panstwo);
		}
		try {
			if (updateMode) {
				adresDAO.updateAdres(tempAdres);
			} else {
				adresDAO.addAdres(tempAdres);			
			}
			setVisible(false);
			dispose();
			listaAdresow.refreshAdres();
			if(updateMode) {
			JOptionPane.showMessageDialog(
					listaAdresow, 
					"Adres dodany poprawnie.",
					"DodanoAdres", 
					JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						listaAdresow, 
						"Adres edytowany poprawnie.",
						"DodanoAdres", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					listaAdresow,
					"B³¹d podczas dodawania Adresu: " + exc.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
