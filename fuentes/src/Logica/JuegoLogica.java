package Logica;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JuegoLogica {

	protected BotonDeLogica [][] M;
	protected final int cantFC=9;

	

	
	public JuegoLogica() {
		M=new BotonDeLogica [cantFC][cantFC];
		inicializar();

	}
	/**
	 * Muestra el estado actualizado del juego por consola.
	 */
	public void mostrarEstado() {
		for (int i=0; i<cantFC; i++)
			for (int j=0; j<cantFC; j++) {
				System.out.print(M[j][i].getValor()+ " ");
				if (j==8)
					System.out.println();
			}
	}
	/**
	 * Splitea el string linea pasado por parametro a la matriz (en la fila "i")
	 * @param i fila de la matriz en la que se va a insertar el String linea.
	 * @param linea 
	 */
	protected void convertir(int i, String linea) {
		Random r= new Random();
		int randomN;
		String [] arreglo=linea.split(" ");
		int valor=0;
		 for (int j=0; j<cantFC; j++) {
			 randomN= r.nextInt(2);
			 valor=Integer.parseInt(arreglo[j]);
			 if (randomN!=0)
				 M[j][i]=new BotonDeLogica(true,valor);
			 else
				 M[j][i]=new BotonDeLogica(false,0);
		 }
	}
	/**
	 * retorna el BotonDeLogica que se encuentra en la coordenada i,j
	 * @param i fila 
	 * @param j columna
	 * @return
	 */
	public BotonDeLogica obtenerBotonLogica(int i, int j) {
		return M[i][j];
	}
	/**
	 * Inicializa la matriz, con datos obtenidos de un txt.
	 */
	protected void inicializar() {
		String rutaAbsoluta;
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String linea;
	      int k=0;
	      rutaAbsoluta= "Archivo/Sudoku.txt" ;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (rutaAbsoluta);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         while((linea=br.readLine())!=null) {
	             convertir(k,linea);
	             k++;
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	   
	}
	/**
	 * chequea el estado de la matriz. Si algún valor está sin completar, retornara verdadero.
	 * @return true si la matriz no esta completa.
	 */
	public boolean chequearMatrizCompleta() {
		boolean encontre=false;
		for (int i=0; i<cantFC && !encontre; i++)
			for (int j=0; j<cantFC && !encontre; j++) {
				if (M[i][j].getValor()==0)
					encontre=true;
			}
		return encontre;
	}
	/**
	 * chequea que en la columna no se encuentren dos elementos iguales.
	 * @param fila fila de la matriz
	 * @param columna columna a analizar.
	 * @param numero valor para comparar que no se encuentre repetido.
	 * @return retorna verdadero si el numero esta repetido en la columna.
	 */
	protected boolean chequearColumna(int fila,int columna, int numero) {
		int contador=0;
		boolean encontre=false;
		int f=0;
		/**
		 * recorro la columna
		 */
		for (int h=0; h<cantFC && !encontre; h++) {
			if(M[h][columna].getValor()==numero) {
				contador++;
				if (contador==1)
					f=h;
				if (contador>1) {
					encontre=true;
					M[h][columna].setcorrompeRegla(true);
					M[f][columna].setcorrompeRegla(true);
				}
			}
		}
		return encontre;
	}
    /**
     * chequea que en la fila pasada por parametros no se repita el numero pasado por parametro
     * @param fila fila de la matriz que se analizara.
     * @param columna correspondiente a la coordenada del numero pasado por parametro.
     * @param numero entero el cual se comparara con los demas elementos de la fila.
     * @return verdadero si el numero se repite en la fila, false en caso contrario.
     */
	protected boolean chequearFila(int fila, int columna,int numero) {
		boolean encontre=false;
		int contador=0;
		int c=0;
			/*
			 * chequeo la fila
			 */
			for (int k=0; k<cantFC && !encontre ; k++) {
				if (M[fila][k].getValor()==numero) {
					contador++;
					if (contador==1)
						c=k;
					if (contador>1) {
						encontre=true;
						M[fila][k].setcorrompeRegla(true);
						M[fila][c].setcorrompeRegla(true);
					}
					
				}
			}
			contador=0;
					
		return encontre;	
	}
	
	/**
	 * retorna si el valor entero "x" se encuentra entre el valor entero "lower" y "upper".
	 * @param x valor entero
	 * @param lower  valor entero
	 * @param upper  valor entero.
	 * @return retorna verdadero si el valor x se encuentra entre lower y upper
	 */
    protected boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
		}
    /**
     * Dados dos enteros fila y columna, analiza en que cuadrante se encuentra esa coordenada.
     * @param f coordenada de la fila
     * @param c coordenada de la columna
     * @return retorna el numero de cuadrante en el que se encuentra la casilla de la coordenada f,c.
     *    valor entre 1 y 9.
     */
	protected int buscarCuadrante(int f,int c) {
		int res=0;
		if (isBetween(f,0,2)) {
			if (isBetween(c,0,2))
				res=1;
			else
				if(isBetween(c,3,5))
					res=2;
				else
					if(isBetween(c,6,8))
						res=3;
		}
		if (isBetween(f,3,5)) {
			if (isBetween(c,0,2))
				res=4;
			else
				if(isBetween(c,3,5))
					res=5;
				else
					if(isBetween(c,6,8))
						res=6;
			
		}
		if (isBetween(f,6,8)) {
			if (isBetween(c,0,2))
				res=7;
			else
				if(isBetween(c,3,5))
					res=8;
				else
					if(isBetween(c,6,8))
						res=9;
		}
		return res;
	}
	/**
	 * chequea que el numero pasado por parametro, no se repita en el numero de panel pasado por parametro.
	 * @param panel panel en el que se encuentra el numero.
	 * @param numero
	 * @return
	 */
	protected boolean chequearPanel(int panel, int numero) {
		boolean resultado=false;
		int contador=0;
		int auxF=0;
		int auxC=0;
		
		switch (panel) {
		
			case 1: for (int i=0; i<3 && !resultado; i++)
				 	   for (int j=0; j<3 && !resultado; j++) {
				 		   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
				 	   }break;
			
			case 2: for (int i=0; i<3 && !resultado; i++)
						for (int j=3; j<6 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
			
			case 3: for (int i=0; i<3 && !resultado; i++)
					    for (int j=6; j<9 && !resultado; j++) {
					    	   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
					    }break;
		
			case 4: for (int i=3; i<6 && !resultado; i++)
						for (int j=0; j<3 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
						
			case 5: for (int i=3; i<6 && !resultado; i++)
						for (int j=3; j<6 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
			
		
			case 6: for (int i=3; i<6 && !resultado; i++)
						for (int j=6; j<9 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
						
			case 7: for (int i=6; i<9 && !resultado; i++)
						for (int j=0; j<3 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}
						break;
				
			case 8: for (int i=6; i<9 && !resultado; i++)
						for (int j=3; j<6 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
			
			case 9: for (int i=6; i<9 && !resultado; i++)
						for (int j=6; j<9 && !resultado; j++) {
							   if (M[i][j].getValor()==numero) {
					 			   contador++;
					 		   if (contador==1) {
					 			   auxF=i;
					 			   auxC=j;
					 		   }
					 		   if (contador>1) {
					 			   
					 			   resultado=true;
					 			   M[i][j].setcorrompeRegla(true);
					 			   M[auxF][auxC].setcorrompeRegla(true);
					 		   }
				 		   }
						}break;
		}
		
		return resultado;
	}
	
	/**
	 * Dados dos valores enteros (fila-columna) chequea que el numero ubicado en esa casilla sea correcto. 
	 * @param i valor de la fila
	 * @param j valor de la columna.
	 * @return retorna verdadero si: el numero ubicado en la casilla que se encuentra en la coordenada i,j se repite fila,columna o panel.
	 *		   retorna falso en caso contrario.
	 */		  
	public boolean esCorrecto(int i, int j) {
		int panelNumero;
		boolean retorno=true;
		int n= M[i][j].getValor();
		panelNumero=buscarCuadrante(i,j);
	
		   if (n!=0) {
				if (chequearFila(i,j,n)==true)
					retorno=false;
				if (chequearColumna(i,j,n)==true)
					retorno=false;
				if (chequearPanel(panelNumero,n)==true)
					retorno=false;
		   }

		return retorno;
	}
	
}
