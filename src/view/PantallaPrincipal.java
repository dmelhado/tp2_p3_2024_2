package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.VentanaJuegoControlador;
import controller.VentanaMenuControlador;
import model.WeightedGraph;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	final static String S_VENTANAMENU = "Ventana Menu";
	private final String S_VENTANAJUEGO = "Ventana Juego";
	private VentanaMenu ventanaMenu;
	private VentanaJuego ventanaJuego;
	private CardLayout cardLayout;
	private WeightedGraph<String> grafo;
	private VentanaJuegoControlador controlador;
	private VentanaMenuControlador controladorMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		setTitle("Temible Operario del Recontraespionaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		this.cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		this.grafo = new WeightedGraph<>();
		this.ventanaMenu = new VentanaMenu(this);
		this.controladorMenu = new VentanaMenuControlador(ventanaMenu, this);
		this.ventanaJuego = new VentanaJuego(grafo);
		this.controlador = new VentanaJuegoControlador(grafo, ventanaJuego);

		// añade ventanas al contenedor
		getContentPane().add(ventanaMenu, S_VENTANAMENU);
		getContentPane().add(ventanaJuego, getS_VENTANAJUEGO());

		revalidate();
		repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void cambiarVentana(String ventana) {
		if (ventana.equals(getS_VENTANAJUEGO())) {
			agregarMenuProyecto();

		} else {
			setJMenuBar(null);
		}
		cardLayout.show(getContentPane(), ventana);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void agregarMenuProyecto() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu opcionMenu = new JMenu("Opciones");
		menuBar.add(opcionMenu);

		JMenuItem VolverMenu = new JMenuItem("Volver al Menú");
		opcionMenu.add(VolverMenu);
		VolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentana(S_VENTANAMENU);
			}
		});

		JMenuItem SalirJuego = new JMenuItem("Salir del Proyecto");
		opcionMenu.add(SalirJuego);
		SalirJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Si", "No" };
				int opcion = JOptionPane.showOptionDialog(ventanaJuego, "¿Estas seguro que deseas salir del proyecto?",
						"Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						null);
				if (JOptionPane.YES_OPTION == opcion) {
					dispose();
				}
			}
		});

		JMenu opcionAyuda = new JMenu("Ayuda");
		menuBar.add(opcionAyuda);

		JMenuItem acercaDe = new JMenuItem("Acerca de");
		opcionAyuda.add(acercaDe);
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] option = { "Volver" };
				String mensaje = "Proyecto creado para Programación III por: \n     -Dante Melhado,  \n     -Ignacio Aranda Bao,  \n     -Emanuel Suarez.";
				JOptionPane.showOptionDialog(ventanaJuego, mensaje, "Información del Proyecto",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
			}

		});
	}

	public VentanaJuego getVentanaJuego() {
		return ventanaJuego;
	}

	public VentanaJuegoControlador getControladorJuego() {
		return controlador;
	}

	public String getS_VENTANAJUEGO() {
		return S_VENTANAJUEGO;
	}

}
