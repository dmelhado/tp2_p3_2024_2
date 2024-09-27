package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class VentanaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private PantallaPrincipal pantallaPrincipal;

	public VentanaJuego(PantallaPrincipal pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		iniciarJuego();
	}

	private void iniciarJuego() {
		removeAll();
		setName("Temible Operario del RecontraEspionaje");
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout());

	}
}
