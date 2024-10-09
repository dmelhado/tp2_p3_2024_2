package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaConexiones {

	private JFrame frame;
	private VentanaMenu ventanaMenu;

	public VentanaConexiones(VentanaMenu ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
		ventanaConexiones();
	}

	private void ventanaConexiones() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		// PANELES //
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setPreferredSize(new Dimension(50, 100));

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setPreferredSize(new Dimension(50, 100));

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));

		ArrayList<String> listaEspias = ventanaMenu.obtenerEspias();

		JComboBox<String> comboBox = new JComboBox<String>();
		panel_4.add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		panel_4.add(comboBox_1);
		
		DefaultComboBoxModel<String> espia1 = new DefaultComboBoxModel<>(listaEspias.toArray(new String[0]));
		DefaultComboBoxModel<String> espia2 = new DefaultComboBoxModel<>(listaEspias.toArray(new String[0]));

		comboBox.setModel(espia1);
		comboBox_1.setModel(espia2);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		JSlider slider = new JSlider(0, 100, 50);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		panel_3.add(slider);

		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// LABELS //
		JLabel lblNewLabel = new JLabel("Asigna las Conexiones");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Seleccione Agente 1");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Seleccione Agente 2");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Ingrese Probabilidad");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(" " + slider.getValue() / 100.0);
		panel_7.add(lblNewLabel_5);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double decimalValue = slider.getValue() / 100.0;
				lblNewLabel_5.setText(" " + decimalValue);
			}
		});

		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(0, new JLabel("0"));
		labelTable.put(100, new JLabel("1"));
		slider.setLabelTable(labelTable);

		// BOTONES //
		JButton btnNewButton = new JButton("Volver al Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar Arista");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String espia1 = (String) comboBox.getSelectedItem();
				String espia2 = (String) comboBox_1.getSelectedItem();
				String probabilidadTexto = lblNewLabel_5.getText();
				if (espia1 != null && espia2 != null) {
					Double.parseDouble(probabilidadTexto.replace(",", "."));
					ventanaMenu.agregarTablaAristaProbabilidad(espia1, espia2, probabilidadTexto);

				}
			}
		});
		panel_6.add(btnNewButton_1);
	}

	public void abrirVentana() {
		frame.setVisible(true);
	}
}
