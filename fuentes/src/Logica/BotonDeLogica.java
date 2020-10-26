package Logica;

import javax.swing.ImageIcon;
/**
 * Clase que modela un BotonDeLogica.
 * @author simon
 *
 */

public class BotonDeLogica {
	
	protected ImageIcon imagen;
	protected int valor;
	protected boolean esInicial;
	protected boolean corrompeRegla;
				
	public BotonDeLogica(boolean esInicial, int valor) {
		this.esInicial=esInicial;
		this.valor=valor;
		actualizar();
	}
	
	public BotonDeLogica (boolean esInicial) {
		this.esInicial=esInicial;
	}
	/**
	 * establece la imagen del BotonDeLogica.
	 * @param f Imagen.
	 */
	public void setImagen(ImageIcon f) {
		imagen= f;
	}
	/**
	 * establece el valor asociado al BotonDeLogica.
	 * @param v el valor que tendra el botonDeLogica.
	 */
	public void setValor(int v) {
		valor=v;
		actualizar();
	}
	/**
	 * Establece si el BotonDeLogica corrompe alguna regla o no.
	 * @param i
	 */
	public void setcorrompeRegla(boolean i) {
		corrompeRegla=i;
	}

	
	/**
	 * retorna si el BotonDeLogica es un boton incial o no.
	 * @return un boleano true si es inicial, false caso contrario.
	 */
	public boolean esInicial() {
		return esInicial;
	}
	/**
	 *  Retorna verdadero si el boton corrompe alguna regla, falso en caso contrario.
	 * @return un boleano dependiendo del comportambiento del boton.
	 */
	public boolean corrompeRegla() {
		return corrompeRegla;
	}
	/**
	 * Retorna el grafico asociado al BotonDeLogica.
	 * @return una Imagen que se corresponde con el BotonDeLogica.
	 */
	public ImageIcon getGrafico() {
		return imagen;
	}
	
	/**
	 * retorna el valor asociado al BotonDeLogica.
	 * @return
	 */
	public int getValor() {
		return valor;
	}
	

	/**
	 * Actualiza la imagen del BotonDeLogica dependiendo el valor del mismo.
	 */
	public void actualizar() {
		switch (valor) {
			case 0: imagen= new ImageIcon("img/numero0.jpg");break;
			case 1: imagen= new ImageIcon("img/numero1.jpg");break;
			case 2: imagen= new ImageIcon("img/numero2.jpg");break;
			case 3:	imagen= new ImageIcon("img/numero3.jpg");break;
			case 4:	imagen= new ImageIcon("img/numero4.jpg");break;
			case 5:	imagen= new ImageIcon("img/numero5.jpg");break;
			case 6:	imagen= new ImageIcon("img/numero6.jpg");break;
			case 7:	imagen= new ImageIcon("img/numero7.jpg");break;
			case 8:	imagen= new ImageIcon("img/numero8.jpg");break;
			case 9:	imagen= new ImageIcon("img/numero9.jpg");break;
		}
	}
}
