package GUI;

import javax.swing.JButton;

import Logica.BotonDeLogica;

public class BotonDigitos extends JButton{
	
	protected BotonDeLogica casilla;
	
	public BotonDigitos(BotonDeLogica btn) {
		super();
		casilla=btn;
	}

	public BotonDigitos() {
		casilla=null;
	}
	/**
	 * Retorna la casilla.
	 * @return La casilla
	 */
	public BotonDeLogica getCasilla() {
		return casilla;
	}
	/**
	 * setea el valor de la casilla.
	 * @param casilla
	 */
	public void setCasilla(BotonDeLogica casilla) {
		this.casilla = casilla;
	}
	
}
