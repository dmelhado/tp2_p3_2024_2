package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private PantallaPrincipal pantallaPrincipal;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JTextField textField;
	private JTextField textField_1;
	private VentanaJuego ventanaJuego;
	public VentanaMenu(PantallaPrincipal pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		MenuJuego();

	}

	public void MenuJuego() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		// PANELES
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.WEST);
		panel_3.setPreferredSize(new Dimension(100, 100));

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.EAST);
		panel_4.setPreferredSize(new Dimension(100, 100));

		// Labels
		JLabel lblNewLabel_1 = new JLabel("Ingrese Nombre de Espía");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_1);

		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Seleccione Espía 1:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Seleccione Espía 2:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Ingrese Probabilidad de Intercepción");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_4);

		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Temible Operario del recontraespionaje");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel);

		// ComboBox

		JComboBox<String> comboBox = new JComboBox<String>();
		panel_7.add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		panel_7.add(comboBox_1);

		// Modelo Tabla
		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);

		// Crea Tabla y agrega JScrollPane
		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(600, 150));
		panel_9.add(scrollPane, BorderLayout.CENTER);

		//ACCIONES ENTER
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espía = textField.getText();
				if (!espía.isEmpty())
				{
					comboBox.addItem(espía);
					comboBox_1.addItem(espía);
					
					textField.setText("");
				}
				
			}
		});
		
		textField_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia1 = (String) comboBox.getSelectedItem();
				String espia2 = (String) comboBox_1.getSelectedItem();
				String probabilidadTexto = textField_1.getText();

				if (espia1 != null && espia2 != null && !probabilidadTexto.isEmpty()) {
					try {
						double probabilidad = Double.parseDouble(probabilidadTexto.replace(',', '.'));
						if (probabilidad < 0 || probabilidad > 1) {
							JOptionPane.showMessageDialog(VentanaMenu.this, "La probabilidad debe estar entre 0 y 1");
						} else {
							modeloTabla.addRow(new Object[] { espia1 + "-" + espia2, probabilidad });
							textField_1.setText("");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(VentanaMenu.this, "Por favor ingrese un valor numérico válido.");
					}
				}
			}

		});
				
		// Botones
		JButton btnNewButton_1 = new JButton("Cargar Espía");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String espia = textField.getText();
				if (!espia.isEmpty()) {
					comboBox.addItem(espia);
					comboBox_1.addItem(espia);

					textField.setText("");
				}
			}
		});
		panel_5.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cargar Arista");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String espia1 = (String) comboBox.getSelectedItem();
				String espia2 = (String) comboBox_1.getSelectedItem();
				String probabilidadTexto = textField_1.getText();

				if (espia1 != null && espia2 != null && !probabilidadTexto.isEmpty()) {
					try {
						double probabilidad = Double.parseDouble(probabilidadTexto.replace(',', '.'));
						if (probabilidad < 0 || probabilidad > 1) {
							JOptionPane.showMessageDialog(VentanaMenu.this, "La probabilidad debe estar entre 0 y 1");
						} else {
							modeloTabla.addRow(new Object[] { espia1 + "-" + espia2, probabilidad });
							textField_1.setText("");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(VentanaMenu.this, "Por favor ingrese un valor numérico válido.");
					}
				}
			}

		});
		panel_6.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Crear Grafo");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int numeroFila = modeloTabla.getRowCount();
				HashMap <String, Boolean> mapaEspias = new HashMap<>();
				for (int i = 0; i < numeroFila; i++)
				{
					String espia1 = modeloTabla.getValueAt(i, 0).toString().split("-")[0];	
					String espia2 = modeloTabla.getValueAt(i, 0).toString().split("-")[1];	
					mapaEspias.put(espia1, true);
					mapaEspias.put(espia2, true);
				}
				
				String[] espias = mapaEspias.keySet().toArray(new String[0]);
				String[][] conexiones = new String[numeroFila][3];
				for (int i = 0; i < numeroFila; i++)
				{
					String espia1 = modeloTabla.getValueAt(i, 0).toString().split("-")[0];
					String espia2 = modeloTabla.getValueAt(i, 0).toString().split("-")[1];
					String probabilidad = modeloTabla.getValueAt(i, 1).toString();
					conexiones[i][0] = espia1;
					conexiones[i][1] = espia2;
					conexiones[i][2] = probabilidad;
				}
		
		
				
				
				pantallaPrincipal.cambiarVentana(pantallaPrincipal.S_VENTANAJUEGO);
				pantallaPrincipal.getVentanaJuego().crearGrafo(espias, conexiones);
				
			}
		});
	}

}
