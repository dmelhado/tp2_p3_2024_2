package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.VentanaJuegoControlador;
import model.GrafoPonderado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	final static String S_VENTANAMENU = "Ventana Menu";
	private final String S_VENTANAJUEGO = "Ventana Juego";
	private VentanaMenu ventanaMenu;
	private VentanaJuego ventanaJuego;
	private CardLayout cardLayout;
	private GrafoPonderado<String> grafo;
	private VentanaJuegoControlador controlador;

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
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

	public PantallaPrincipal() {
		setTitle("Temible Operario del Recontraespionaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		this.cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		this.grafo = new GrafoPonderado<>();
		this.ventanaMenu = new VentanaMenu(this);
		this.ventanaJuego = new VentanaJuego(controlador);
		this.controlador = new VentanaJuegoControlador(grafo, ventanaJuego);

		// añade ventanas al contenedor
		getContentPane().add(ventanaMenu, S_VENTANAMENU);
		getContentPane().add(ventanaJuego, getS_VENTANAJUEGO());

		revalidate();
		repaint();
	}

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

		JMenu desplegableOpciones = new JMenu("Opciones");
		menuBar.add(desplegableOpciones);

		JMenuItem volverAlMenu = new JMenuItem("Volver al Menú");
		desplegableOpciones.add(volverAlMenu);
		volverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarVentana(S_VENTANAMENU);
			}
		});

		JMenuItem salirJuego = new JMenuItem("Salir del Proyecto");
		desplegableOpciones.add(salirJuego);
		salirJuego.addActionListener(new ActionListener() {
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
