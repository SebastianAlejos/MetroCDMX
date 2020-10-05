//Sebastian Alejos Acosta
//A00344555
//Proyecto final: Metro CDMX
//1 de junio de 2020
import java.awt.BorderLayout;
import javax.swing.JFrame;


public class Ventana extends JFrame{
	//Se crea el frame y se añaden los elementos a la pantalla
	public Ventana() {
		super("Metro CDMX");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Panel p=new Panel();
		this.add(p);
		this.add(new Controles(),BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
	}	
	public static void main(String[] args) {
		Ventana ventana =new Ventana();
	}

}