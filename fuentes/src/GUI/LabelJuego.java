package GUI;

import javax.swing.JLabel;

import Logica.BotonDeLogica;
/**
 * modela la interfaz. Cada LabelJuego se corresponde con un BotonDeLogica.
 * @author Simon Fredes Hadad
 *
 */
public class LabelJuego extends JLabel{
	
	protected BotonDeLogica casilla;
	protected int fila;
	protected int columna;
	protected boolean esIncorrecto;
	
	public LabelJuego(int fila, int columna, BotonDeLogica label) {
		super();
		this.fila=fila;
		this.columna=columna;
		this.casilla=label;
	}
	
	/**
	 * setea el BotonDeLogica correspondiente.
	 * @param casilla
	 */
	public void setBotonLogica(BotonDeLogica casilla){
		this.casilla=casilla;
	}
	

	/**
	 * retorna la fila en la que esta el LabelJuego
	 * @return
	 */
	public int getFila() {
		return fila;
	}
	/**
	 * retorna la columna en la que se encuentra el LabelJuego.
	 * @return
	 */
	public int getColumna() {
		return columna;
	}
	/**
	 * Obtiene el BotonDeLogica asociado al LabelJuego
	 * @return
	 */
	public BotonDeLogica getBotonLogica() {
		return casilla;
	}
	
}
