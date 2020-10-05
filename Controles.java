//Sebastian Alejos Acosta
//A00344555
//Proyecto final: Metro CDMX
//1 de junio de 2020
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controles extends JPanel implements ActionListener{
	private String[] estaciones,
					 evadeEstacion;
	private JComboBox<String> cajaOrigen,
							  cajaDestino,
							  evitabox;
	private JButton buscar, nueva;
	private JLabel tiempo,lParadas,tmp2;
	private JLabel[] paradas,recorridos;
	//Se inicializan atributos y se agregan al panel los botones, cajas de seleccion
	public Controles(){
		super();
		this.setPreferredSize(new Dimension(200,800));
		this.setBackground(Color.LIGHT_GRAY);
		this.estaciones = new String[] {"Observatorio","Tacubaya","Balderas","Salto del Agua","Pino Suarez",
	    		   "Candelaria","Pantitlan","Tasquena","Chabacano","Bellas Artes","Hidalgo","Tacuba","Cuatro Caminos",
	    		   "Universidad","Centro Medico","La Raza","Deportivo 18 de Marzo","Indios Verdes","Santa Anita",
	    		   "Jamaica","Consulado","Martin Carrera","Instituto del Petroleo","Politecnico","El Rosario",
	    		   "Barranca del Muerto","Constitucion de 1917","Garibaldi"};
		this.evadeEstacion=new String[] {"Observatorio","Tacubaya","Balderas","Salto del Agua","Pino Suarez",
	    		   "Candelaria","Pantitlan","Tasquena","Chabacano","Bellas Artes","Hidalgo","Tacuba","Cuatro Caminos",
	    		   "Universidad","Centro Medico","La Raza","Deportivo 18 de Marzo","Indios Verdes","Santa Anita",
	    		   "Jamaica","Consulado","Martin Carrera","Instituto del Petroleo","Politecnico","El Rosario",
	    		   "Barranca del Muerto","Constitucion de 1917","Garibaldi","Ninguna"};
		JLabel jLOrigen=new JLabel("Selecciona el lugar de origen");
		this.add(jLOrigen);
		
		this.cajaOrigen=new JComboBox<String>(this.estaciones);
		this.add(cajaOrigen);
		this.cajaOrigen.addActionListener(this);
		
		JLabel jLDestino=new JLabel("Selecciona el destino");
		this.add(jLDestino);
		this.cajaDestino=new JComboBox<String>(this.estaciones);
		this.add(cajaDestino);
		this.cajaDestino.addActionListener(this);
		
		JLabel evita=new JLabel("Deseas evitar una estacion?");
		this.add(evita);
		this.evitabox=new JComboBox<String>(this.evadeEstacion);
		this.evitabox.setSelectedIndex(28);
		this.add(evitabox);
		evitabox.addActionListener(this);
		
		this.buscar=new JButton("Buscar");
		this.add(buscar);
		this.buscar.addActionListener(this);
		
		this.nueva=new JButton("Nueva busqueda");
		this.add(nueva);
		this.nueva.addActionListener(this);	
	}
		
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.buscar) {
			//Crea el grafo y recopila los datos de la seleccion
			Grafo metro=new Grafo();
			int origen=this.cajaOrigen.getSelectedIndex();
			int destino=this.cajaDestino.getSelectedIndex();
			int desconexion=this.evitabox.getSelectedIndex();
			//Si la seleccion es diferente a "ninguna" se desconecta esa estacion
			if(desconexion!=28) {
				metro.desconectarEstacion(desconexion);
			}
			//se calcula el tiempo total, si no se encuentra camino, se indica
			int minutos=metro.dijkstra(origen, destino);
			if(minutos==Integer.MAX_VALUE) {
				JOptionPane.showMessageDialog(this, "No se encontró un camino");
				return;
			}
			this.tiempo=new JLabel("Este camino toma :");
			this.tmp2=new JLabel("<html><font color=red>" + minutos + "</font><html>"+" minutos");
			this.add(tiempo);
			this.add(tmp2);
			this.lParadas=new JLabel("Estaciones que debes tomar: ");
			this.add(this.lParadas);
			Stack<Integer> camino=metro.camino(origen,destino);
			this.paradas=new JLabel[camino.size()];
			this.recorridos=new JLabel[camino.size()-1];
			int current=0;
			int distancia1=0;
			int paradasig=0;
			//Se muestra las paradas a tomar y el tiempo de recorrido entre estaciones
			while(!camino.isEmpty()){
				int parada=camino.pop();
				try {
					paradasig=camino.peek();
				}catch(EmptyStackException excp) {
					paradasig=parada;
				}
				this.paradas[current]=new JLabel();
				this.paradas[current].setPreferredSize(new Dimension(160,20));
				this.paradas[current].setText(this.estaciones[parada]);
				this.add(this.paradas[current]);
				try {
					int distancia=metro.getDistancia(paradasig)-distancia1;
					this.recorridos[current]=new JLabel();
					this.recorridos[current].setPreferredSize(new Dimension(160,20));
					this.recorridos[current].setText("<html><font color=green>" + distancia + "</font><html>"+ " minutos");
					this.add(this.recorridos[current]);
					distancia1+=distancia;
					current++;
				}catch(ArrayIndexOutOfBoundsException exp) {
					break;
				}
		    }
			this.revalidate();
			this.repaint();
		}
		//Se limpia el panel para poder hacer una nueva selección
		if(e.getSource()==this.nueva) {
			try {
				this.remove(this.tiempo);
				this.remove(this.lParadas);
				this.remove(tmp2);
				for(int i=0;i<this.paradas.length;i++) {
					this.remove(this.paradas[i]);
				}
				for(int i=0;i<this.recorridos.length;i++) {
					this.remove(this.recorridos[i]);
				}
				this.revalidate();
				this.repaint();
			}catch(NullPointerException exc) {
				JOptionPane.showMessageDialog(this, "No se puede hacer nueva busqueda si no ha buscado nada anteriormente");
			}
		}
		
	}

}
