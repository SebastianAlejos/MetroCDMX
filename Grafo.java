//Sebastian Alejos Acosta
//A00344555
//Proyecto final: Metro CDMX
//1 de junio de 2020
import java.util.Stack;
public class Grafo {
	private int[][] grafo;
	private int[] distancia, 
				  camino;
	private boolean[] encontrado;
	//Se inicializa el grafo con sus atributos
	public Grafo(){
		this.grafo=new int[][] {
			{0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{12,0,20,0,0,0,0,0,0,0,0,21,0,0,16,0,0,0,0,0,0,0,0,0,0,18,0,0},
			{0,20,0,12,0,0,0,0,0,0,13,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,12,0,12,0,0,0,14,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,12,0,13,0,0,13,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,13,0,21,0,0,0,0,0,0,0,0,0,0,0,0,14,16,0,0,0,0,0,0,0},
			{0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,20,23,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,14,13,0,0,23,0,0,0,0,0,0,14,0,0,0,14,13,0,0,0,0,0,0,0,0},
			{0,0,0,14,13,0,0,0,0,0,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15},
			{0,0,13,0,0,0,0,0,0,11,0,18,0,0,0,17,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,21,0,0,0,0,0,0,0,0,18,0,15,0,0,0,0,0,0,0,0,0,0,0,20,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,30,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,16,15,0,0,0,0,0,14,0,0,0,0,30,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,15,0,0,0,16,0,15,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,12,0,0,0,14,12,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,0,30,0},
			{0,0,0,0,0,14,20,0,13,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,16,23,0,0,0,0,0,0,0,0,16,0,0,0,0,0,15,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,15,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,12,0,0,0,0,0,0,6,27,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,20,0,0,0,0,0,0,0,0,0,0,27,0,0,0,0,0},
			{0,18,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,30,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		this.distancia=new int[this.grafo[0].length];
		this.encontrado=new boolean[this.distancia.length];
		this.camino=new int[this.grafo[0].length];
	}
	//Se encuentra el vértice más cercano, de los que no han sido encontrados aún
	private int masCercano(){ 
        int min = Integer.MAX_VALUE;
        int indice = -1; 
        for (int i=0; i<this.distancia.length; i++) {
            if (encontrado[i]==false && distancia[i]<=min) { 
                min = distancia[i]; 
                indice=i; 
            } 
        }    
        return indice; 
    } 
	//Algoritmo para encontrar el camino más corto.
	public int dijkstra(int origen, int destino) {
		//Se inicializan las distancias con valor infinito
		for (int i=0;i<this.distancia.length; i++) {
			this.distancia[i]=Integer.MAX_VALUE;
		}
		//la distancia al origen siempre va a ser 0
		this.distancia[origen]=0;
		// se encuentra el camino más cerca a todos los vertices
		for (int i = 0; i < distancia.length-1; i++) {
			//se calcula el vertice más cercano de los no encontraados
			int cerca= this.masCercano();
			//se marca como encontrado
			this.encontrado[cerca]=true;
			//Se actualiza el valor de los vertices adyacentes
			for(int j=0; j<this.distancia.length;j++) {
				//Se actualiza la distancia si:1. No esta encontrado && 2.Estan conectados && 3.Y el valor del camino de origen a j a través de cerca es menorr que el valor actual 
				if(!this.encontrado[j] && this.grafo[cerca][j]!=0 && this.distancia[cerca]!= Integer.MAX_VALUE && this.distancia[cerca]+this.grafo[cerca][j]<this.distancia[j]) {
					this.distancia[j]=this.distancia[cerca]+this.grafo[cerca][j];
					//se agrega al arreglo del camino el indice de la parada anterior a el mismo
					this.camino[j]=cerca;
				}
			}
		}
		return this.distancia[destino];
	}
	
	//se recorre el arreglo camino, y se va metiendo a un stack las paradas que se hacen de a-b
	public Stack<Integer> camino(int origen, int destino){
		int current=destino;
		Stack<Integer> caminoCorto= new Stack<Integer>();
		caminoCorto.push(destino);
		while (current!=origen) {
			caminoCorto.push(this.camino[current]);
			current=this.camino[current];
			
		}
		return caminoCorto;
    }
	public int getDistancia(int index) {
		return this.distancia[index];
	}
	
	//Se desconecta un vertice del grafo principal 
	public void desconectarEstacion(int index) {
		for(int i=0;i<this.grafo[0].length;i++) {
			this.grafo[i][index]=0;
			this.grafo[index][i]=0;
		}
	}
	/*public static void main(String[] args) {
		Grafo gr= new Grafo();	
		gr.dijkstra(4, 0);
		System.out.println("distancias");
		for (int i = 0; i < gr.distancia.length; i++) {
			System.out.println(gr.getDistancia(i));
		}
		Stack<Integer> stack=gr.camino(4, 0);
		System.out.println("camino");
		for (int i = 0; i <5; i++) {
			System.out.println(stack.pop());
		}
	}*/
}

