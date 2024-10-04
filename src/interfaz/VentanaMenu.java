package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private PantallaPrincipal pantallaPrincipal;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JTable table;
	private ArrayList<String> listaEspias;

	public VentanaMenu(PantallaPrincipal pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		this.listaEspias = new ArrayList<>();
		MenuJuego();

	}

	public void MenuJuego() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		// PANELES y SCROLLSPANE
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_9 = new JPanel();
		panel.add(panel_9);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.WEST);
		panel_3.setPreferredSize(new Dimension(200, 100));
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8, BorderLayout.SOUTH);

		DefaultTableModel modeloTablaEspias = new DefaultTableModel(new Object[] { "Espías" }, 0);
		table = new JTable(modeloTablaEspias);

		JScrollPane scrollPane_1 = new JScrollPane(table);
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		scrollPane_1.setPreferredSize(new Dimension(600, 150));

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("Temible Operario del recontraespionaje");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		panel_1.add(lblNewLabel);

		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_9.add(panel_6, BorderLayout.WEST);
		panel_6.setPreferredSize(new Dimension(120, 120));

		JPanel panel_5 = new JPanel();
		panel_9.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panel_5.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(600, 150));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// BOTONES
		JButton btnNewButton_2 = new JButton("Agregar Espias");
		panel_8.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAgentes ventanaAgentes = new VentanaAgentes(VentanaMenu.this);
				ventanaAgentes.abrirVentana();
			}

		});

		JButton btnNewButton_1 = new JButton("Agregar Aristas");
		panel_7.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaConexiones ventanaConexiones = new VentanaConexiones(VentanaMenu.this);
				ventanaConexiones.abrirVentana();
			}

		});

		JButton btnNewButton = new JButton("Crear Grafo");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int numeroFila = modeloTabla.getRowCount();
				HashMap<String, Boolean> mapaEspias = new HashMap<>();
				for (int i = 0; i < numeroFila; i++) {
					String espia1 = modeloTabla.getValueAt(i, 0).toString().split("-")[0];
					String espia2 = modeloTabla.getValueAt(i, 0).toString().split("-")[1];
					mapaEspias.put(espia1, true);
					mapaEspias.put(espia2, true);
				}

				String[] espias = mapaEspias.keySet().toArray(new String[0]);
				String[][] conexiones = new String[numeroFila][3];
				for (int i = 0; i < numeroFila; i++) {
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

	public void agregarTablaEspias(String nombreEspia) {

		DefaultTableModel modeloTablaEspias = (DefaultTableModel) table.getModel();
		modeloTablaEspias.addRow(new Object[] { nombreEspia });
		listaEspias.add(nombreEspia);
	}

	public ArrayList<String> obtenerEspias() {
		return listaEspias;
	}

	public void agregarTablaAristaProbabilidad(String espia1, String espia2, String probabilidad) {
		DefaultTableModel modeloTablaAristas = (DefaultTableModel) tabla.getModel();
		modeloTablaAristas.addRow(new Object[] { espia1 + "-" + espia2, probabilidad });

	}
}