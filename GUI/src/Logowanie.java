import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.sql.*;
public class Logowanie {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public static Connection myConn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//KlientDAO dao = new KlientDAO();
				try {
					//System.out.println(dao.getAllKlient());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Logowanie window = new Logowanie();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logowanie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 330, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 54, 314, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblLogowanie = new JLabel("LOGOWANIE");
		lblLogowanie.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLogowanie.setBounds(115, 11, 100, 38);
		frame.getContentPane().add(lblLogowanie);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(20, 80, 46, 14);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setBounds(20, 110, 46, 14);
		frame.getContentPane().add(lblHaso);
		
		textField = new JTextField();
		textField.setBounds(60, 77, 244, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(60, 107, 244, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Zaloguj");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String pass = String.valueOf(passwordField.getPassword());
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza?useSSL=false&noAccessToProcedureBodies=true", textField.getText(), pass);
					String log = textField.getText();
					if(log.contains("Recepcjonista")) {
					MenuRecepcjonista okno = new MenuRecepcjonista();
					okno.frmMenu.setVisible(true);
					} else {
					Mened¿erMenu okno1 = new Mened¿erMenu();
					okno1.setVisible(true);
					}
					frame.dispose();
				} catch (SQLException exc) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "B³¹d logowania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		btnNewButton.setBounds(115, 137, 115, 30);
		frame.getContentPane().add(btnNewButton);
	}
}
