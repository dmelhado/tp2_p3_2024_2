package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import controller.VentanaMenuControlador;

public class VentanaMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private DefaultTableModel modeloTablaEspias;
	private JTable table;
	private ArrayList<String> listaEspias;
	private VentanaMenuControlador controlador;

	public VentanaMenu(PantallaPrincipal pantallaPrincipal) {
		this.listaEspias = new ArrayList<>();
		MenuJuego();

	}

	public void MenuJuego() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// PANELES y SCROLLSPANE
		JPanel panelTop = new JPanel();
		add(panelTop, BorderLayout.NORTH);

		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelButtom = new JPanel();
		add(panelButtom, BorderLayout.SOUTH);

		JPanel panelWest = new JPanel();
		add(panelWest, BorderLayout.WEST);
		panelWest.setPreferredSize(new Dimension(200, 100));
		panelWest.setLayout(new BorderLayout(0, 0));

		JPanel panelEast = new JPanel();
		add(panelEast, BorderLayout.EAST);
		panelEast.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panelEast.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_8 = new JPanel();
		panelWest.add(panel_8, BorderLayout.SOUTH);

		modeloTablaEspias = new DefaultTableModel(new Object[] { "Espías" }, 0);
		table = new JTable(modeloTablaEspias);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		panelWest.add(scrollPane_1, BorderLayout.CENTER);
		scrollPane_1.setPreferredSize(new Dimension(600, 150));
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		JLabel lblNewLabel = new JLabel("Temible Operario del recontraespionaje");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		panelTop.add(lblNewLabel);

		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);

		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panelEast.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(250, 150));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(110);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		// BOTONES
		JButton btnNewButton_2 = new JButton("Agregar Espias");
		panel_8.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaAgentes();
			}

		});

		JButton btnNewButton_3 = new JButton("Limpiar Tablas");
		btnNewButton_3.setPreferredSize(new Dimension(150, 50));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.limpiarTablas();
			}
		});
		panelButtom.add(btnNewButton_3);

		JLabel lblNewLabel_1 = new JLabel("                              ");
		panelButtom.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Crear Grafo");
		btnNewButton.setPreferredSize(new Dimension(150, 50));
		panelButtom.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar Aristas");
		panel_7.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaConexiones();
			}

		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.crearGrafo();

			}
		});
	}

	public void agregarTablaEspias(String nombreEspia) {

		DefaultTableModel modeloTablaEspias = (DefaultTableModel) table.getModel();
		modeloTablaEspias.addRow(new Object[] { nombreEspia });
		listaEspias.add(nombreEspia);
		controlador.actualizarComboBoxes();

	}

	public ArrayList<String> obtenerEspias() {
		return listaEspias;
	}

	public void agregarTablaAristaProbabilidad(String espia1, String espia2, String probabilidad) {
		DefaultTableModel modeloTablaAristas = (DefaultTableModel) tabla.getModel();
		modeloTablaAristas.addRow(new Object[] { espia1 + "-" + espia2, probabilidad });

	}

	public void limpiarTablas() {

		modeloTabla.setRowCount(0);
		modeloTablaEspias.setRowCount(0);
		listaEspias.clear();

	}

	public void setControlador(VentanaMenuControlador controlador) {
		this.controlador = controlador;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}
}