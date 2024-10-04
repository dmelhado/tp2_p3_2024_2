package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaAgentes {

	private JFrame frame;
	private JTextField textField;
	private VentanaMenu ventanaMenu;

	public VentanaAgentes(VentanaMenu ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
		ventanaAgente();
	}

	private void ventanaAgente() {
		frame = new JFrame("Agregar Agente");
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		// PANELES //
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.EAST);
		panel2.setPreferredSize(new Dimension(60, 100));

		JPanel panel3 = new JPanel();
		frame.getContentPane().add(panel3, BorderLayout.WEST);
		panel3.setPreferredSize(new Dimension(60, 100));

		JLabel lblNewLabel_2 = new JLabel("Ingrese nombres:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_2);
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.SOUTH);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = textField.getText();
				if (!espia.isEmpty()) {
					ventanaMenu.agregarTablaEspias(espia);
					textField.setText("");
				}

			}

		});

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		// BOTONES //
		JButton btnNewButton_1 = new JButton("Agregar");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = textField.getText();
				if (!espia.isEmpty()) {
					ventanaMenu.agregarTablaEspias(espia);
					textField.setText("");
				}

			}

		});

		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);


		// LABELS //
		JLabel lblNewLabel = new JLabel("Agrega un Agente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Volver al Menu");
		panel1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}

		});
		
		

	}

	public void abrirVentana() {
		frame.setVisible(true);
	}
}
