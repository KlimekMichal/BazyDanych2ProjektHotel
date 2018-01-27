import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MenuRecepcjonista {

	JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuRecepcjonista window = new MenuRecepcjonista();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MenuRecepcjonista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("MENU");
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Lista Rezerwacji");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaRezerwacji lista = new ListaRezerwacji();
				lista.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(159, 11, 119, 23);
		frmMenu.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Lista Klient\u00F3w");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaKlientow lista = new ListaKlientow();
				lista.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(159, 45, 119, 23);
		frmMenu.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Lista Pokoi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPokoi lista = new ListaPokoi();
				lista.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(159, 113, 119, 23);
		frmMenu.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Wyloguj");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Logowanie.myConn.close();
					frmMenu.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(159, 147, 119, 23);
		frmMenu.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Lista Adres\u00F3w");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaAdresow lista = new ListaAdresow();
				lista.setVisible(true);
				
			}
		});
		btnNewButton_6.setBounds(159, 79, 119, 23);
		frmMenu.getContentPane().add(btnNewButton_6);
	}

}
