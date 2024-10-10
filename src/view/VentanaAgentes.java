package view;

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
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;


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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// PANELES //
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		ImageIcon imageIcon = new ImageIcon("docs/Spy.jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		JLabel labelImagen = new JLabel();
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(imageIcon);
		panel.add(labelImagen);

		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.EAST);
		panel2.setPreferredSize(new Dimension(50, 90));

		JPanel panel3 = new JPanel();
		frame.getContentPane().add(panel3, BorderLayout.WEST);
		panel3.setPreferredSize(new Dimension(50, 90));
		
				
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


					
		JLabel lblNewLabel_2 = new JLabel("Ingrese nombres:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_2);
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.SOUTH);


				}});

		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);

		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.SOUTH);

		// LABELS //
		JLabel lblNewLabel = new JLabel("Agrega un Agente");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		// BOTONES //
		JButton btnNewButton_11 = new JButton("Agregar");
		panel.add(btnNewButton_11);
		btnNewButton_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = textField.getText();
				if (!espia.isEmpty()) {
					ventanaMenu.agregarTablaEspias(espia);
					textField.setText("");
				}

			}

		});

		JLabel lblNewLabel_31 = new JLabel("");
		panel.add(lblNewLabel_31);


		// LABELS //
		JLabel lblNewLabel1 = new JLabel("Agrega un Agente");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel1, BorderLayout.NORTH);

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
