import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class Mened¿erMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mened¿erMenu frame = new Mened¿erMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mened¿erMenu() {
		setTitle("MENU MENED\u017BERA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Lista pracownikow");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaPracownikow lista = new ListaPracownikow();
				lista.setVisible(true);
			}
		});
		btnNewButton.setBounds(63, 11, 119, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("Recepcjonista");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuRecepcjonista okno = new MenuRecepcjonista();
				okno.frmMenu.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(63, 45, 119, 23);
		contentPane.add(btnNewButton_4);
	}
}
