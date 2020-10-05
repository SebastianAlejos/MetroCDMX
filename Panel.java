//Sebastian Alejos Acosta
//A00344555
//Proyecto final: Metro CDMX
//1 de junio de 2020
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private BufferedImage fondo;
	//Se crea el panel y se inicializa la imagen
	public Panel() {
		super();
		this.setPreferredSize(new Dimension(600,800));
		try {
			this.fondo=ImageIO.read(getClass().getResource("metro.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
	}	
	//Se pinta la imagen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0,600,800,this);
		
	}
}
