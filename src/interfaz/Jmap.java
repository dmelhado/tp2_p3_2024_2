package interfaz;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JPanel;

public class Jmap extends JPanel {
	    private JMapViewer map;

	    public Jmap() {
	        // Crear una instancia de JMapViewer
	        map = new JMapViewer();

	        // Añadir el mapa a este panel
	        this.add(map);
	    }
	    

}

