package co.edu.unbosque.controller;

import java.util.Scanner;

public class Computadores {

	public static Scanner leer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leer = new Scanner(System.in);
		// Cantidad de casos de prueba
		String output="";
		int T = Integer.parseInt(leer.nextLine().substring(1));
		if( T>0 && T<1000000) {
			//Ejecutar cada caso
			for (int i = 0; i < T; i++) {
				String auxCasos = leer.nextLine();
				// # de computadores
				int N = Integer.parseInt(auxCasos.split(" ")[0]);
				// # de conexiones entre computadores
				int M = Integer.parseInt(auxCasos.split(" ")[1]);
				// Computador origen
				int A = Integer.parseInt(auxCasos.split(" ")[2]);
				// Computador destino
				int B = Integer.parseInt(auxCasos.split(" ")[3]);
				if(A>B) {
					int aux=A;
					A=B;
					B=aux;
				}					
				
				if(N>0 && N<20000) {
					//inicializar Matriz de adyacencia del grafo
					int grafo[][] = new int[N][N];
					for (int j = 0; j < N; j++) {
						for(int k = 0; k < N; k++) {
							grafo[j][k] = 0;
						}
					}
					
					if(M>0 && M<50000) {
						//Asignar conexiones
						for (int l = 0; l < M; l++) {
							String auxConexiones = leer.nextLine();
							// Computador U
							int U = Integer.parseInt(auxConexiones.split(" ")[0]);
							// Computador V
							int V = Integer.parseInt(auxConexiones.split(" ")[1]);
							//Tiempo de transferencia entre U y V
							int W = Integer.parseInt(auxConexiones.split(" ")[2]);
							//Añadir la información a la matriz de adyacencia
							grafo[U][V]=W;							
						}
					}
					//Usar algoritmo Dijkstra
					int respuesta = dijkstra(grafo, A, N, B);
					if(respuesta==2147483647) {
						output+="Caso#"+(i+1)+":\nInalcanzable\n";
					}else {
						output+="Caso#"+(i+1)+":\n"+respuesta+"\n";
					}
					
				}
			}
			System.out.println(output);
		}		
	}

	public static int dijkstra(int[][] grafo, int src, int V, int B) {
		//Inicializa
	     int[] dist = new int[V];     
	     boolean[] verticeYaProcesado = new boolean[V]; 
	     for (int i = 0; i < V; i++) {
	        dist[i] = Integer.MAX_VALUE;
	        verticeYaProcesado[i] = false;
	     }
	     dist[src] = 0;
	 
	     //Encuentra el camino mas corto para todos los vertices
	     for (int count = 0; count < V-1; count++){
	       int u = minDistance(dist, verticeYaProcesado, V);
	       verticeYaProcesado[u] = true;
	       for (int v = 0; v < V; v++)
	         if (!verticeYaProcesado[v] && grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u]+grafo[u][v] < dist[v]) {
	        	 dist[v] = dist[u] + grafo[u][v]; 
	         }
	     }
	               
	     return dist[B];
	}
	
	public static int minDistance(int[] dist, boolean[] verticeYaProcesado, int V) {
	   // Initialize min value
	   int min = Integer.MAX_VALUE; int min_index=0;
	   for (int v = 0; v < V; v++)
	     if (verticeYaProcesado[v] == false && dist[v] <= min) {
	         min = dist[v];
	         min_index = v;
	      }
	   return min_index;
	}
}
