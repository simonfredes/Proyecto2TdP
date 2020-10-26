package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import Logica.BotonDeLogica;
import Logica.JuegoLogica;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Juego extends JFrame {

	private JuegoLogica game;
	private JPanel contentPane;
	private LabelJuego[][] M;
	private BotonDigitos[][] digitos;
	private BotonDigitos ultimoBotonDigito;
	private LabelJuego ultimoLabelModificado;
	private boolean corrompeRegla;
	private JLabel[] arregloReloj = new JLabel[8];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void deshabilitarDigitos(boolean estado) {

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				digitos[i][j].setEnabled(estado);
			}
	}

	public void deshabilitarCuadrantes(boolean estado) {
		for (int i = 0; i < M.length; i++)
			for (int j = 0; j < M.length; j++) {

				M[i][j].setEnabled(estado);
				if (estado == false) {
					if (M[i][j].getBotonLogica().corrompeRegla())
						M[i][j].setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
				} else {
					M[i][j].setBorder(null);
					M[i][j].getBotonLogica().setcorrompeRegla(false);
				}

			}
	}

	/**
	 * Create the frame.
	 */
	public Juego() {
		int p = 0;
		Timer t = new Timer();
		ultimoBotonDigito = null;
		game = new JuegoLogica();
		digitos = new BotonDigitos[9][9];
		M = new LabelJuego[9][9];
		
		setResizable(false);
		game.mostrarEstado();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 581);
		contentPane = new JPanel();
		this.setTitle("Sudoku");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_reloj = new JPanel();
		panel_reloj.setBackground(Color.WHITE);
		panel_reloj.setBounds(572, 24, 199, 81);
		contentPane.add(panel_reloj);
		panel_reloj.setLayout(new GridLayout(0, 8, 0, 0));

		JPanel panel_numeros = new JPanel();
		panel_numeros.setBackground(new java.awt.Color(0, 0, 0, 30));
		panel_numeros.setBounds(572, 116, 199, 180);
		contentPane.add(panel_numeros);
		panel_numeros.setLayout(new GridLayout(3, 0, 3, 0));

		JPanel panel_matriz = new JPanel();
		panel_matriz.setBounds(0, 0, 562, 552);
		panel_matriz.setBackground(new java.awt.Color(0, 0, 0, 30));
		contentPane.add(panel_matriz);
		panel_matriz.setLayout(null);

		BotonDigitos botonBorrar = new BotonDigitos();
		botonBorrar.setIcon(new ImageIcon(this.getClass().getResource("/img/numero0.jpg")));
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ultimoBotonDigito = new BotonDigitos(new BotonDeLogica(false, 0));

			}
		});
		botonBorrar.setBounds(572, 300, 60, 60);
		contentPane.add(botonBorrar);

		JButton TerminarIntento = new JButton("Terminar juego");
		TerminarIntento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane res = new JOptionPane();
				deshabilitarCuadrantes(false);
				TerminarIntento.setEnabled(false);
				botonBorrar.setEnabled(false);
				deshabilitarDigitos(false);
				t.cancel();
				if (!game.chequearMatrizCompleta())
					res.showMessageDialog(null, "¡Ganaste!");

				else
					res.showMessageDialog(null, "¡Perdiste!");

			}
		});
		TerminarIntento.setBounds(638, 306, 133, 41);
		contentPane.add(TerminarIntento);

		JLabel labelPanel1 = new JLabel("");
		labelPanel1.setIcon(new ImageIcon(this.getClass().getResource("/img/fondo.jpg")));
		labelPanel1.setBounds(0, 0, 186, 180);
		contentPane.add(labelPanel1);

		JLabel labelPanel7 = new JLabel("");
		labelPanel7.setIcon(new ImageIcon(this.getClass().getResource("/img/fondo.jpg")));
		labelPanel7.setBounds(0, 359, 186, 195);
		contentPane.add(labelPanel7);

		JLabel labelPanel5 = new JLabel("");
		labelPanel5.setIcon(new ImageIcon(this.getClass().getResource("/img/fondo.jpg")));
		labelPanel5.setBounds(182, 178, 190, 182);
		contentPane.add(labelPanel5);

		JLabel labelPanel3 = new JLabel("");
		labelPanel3.setIcon(new ImageIcon(this.getClass().getResource("/img/fondo.jpg")));
		labelPanel3.setBounds(368, 0, 189, 180);
		contentPane.add(labelPanel3);

		JLabel labelPanel9 = new JLabel("");
		labelPanel9.setIcon(new ImageIcon(this.getClass().getResource("/img/fondo.jpg")));
		labelPanel9.setBounds(368, 359, 189, 180);
		contentPane.add(labelPanel9);

		JLabel labelGif = new JLabel("");
		labelGif.setIcon(new ImageIcon(Juego.class.getResource("/img/gokuKame.gif")));
		labelGif.setBounds(558, 370, 220, 132);
		contentPane.add(labelGif);

		JLabel lblCasaGoku = new JLabel("");
		lblCasaGoku.setIcon(new ImageIcon(this.getClass().getResource("/img/CasaGoku1.png")));
		lblCasaGoku.setBounds(572, 384, 220, 157);
		contentPane.add(lblCasaGoku);

		/**
		 * panel de digitos 1 al 9
		 */
		for (int k = 0; k < 3; k++)
			for (int h = 0; h < 3; h++) {
				p++;
				digitos[k][h] = new BotonDigitos(new BotonDeLogica(true, p));
				digitos[k][h].setIcon(digitos[k][h].getCasilla().getGrafico());
				digitos[k][h].setBackground(new java.awt.Color(0, 0, 0, 10));
				panel_numeros.add(digitos[k][h]);
				digitos[k][h].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent evento) {
						BotonDigitos prueba = (BotonDigitos) evento.getSource();
						ultimoBotonDigito = prueba;

					}
				});
			}

		/**
		 * Panel matriz de juego
		 */
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				M[j][i] = new LabelJuego(j, i, game.obtenerBotonLogica(j, i));
				M[j][i].setIcon(game.obtenerBotonLogica(j, i).getGrafico());
				M[j][i].getBotonLogica().setcorrompeRegla(false);

				M[j][i].setBounds(62 * j, 60 * i, 58, 58);

				panel_matriz.add(M[j][i]);

				M[j][i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evento) {
						int fila, columna;
						LabelJuego prueba = (LabelJuego) evento.getSource();
						fila = prueba.getFila();
						columna = prueba.getColumna();
						if (ultimoBotonDigito != null) {
							if (corrompeRegla && ultimoBotonDigito != null) {
								if (ultimoBotonDigito.getCasilla().getValor() == 0 && prueba == ultimoLabelModificado) {
									prueba.setBorder(null);
									prueba.getBotonLogica().setcorrompeRegla(false);
									prueba.getBotonLogica().setValor(0);
									prueba.setIcon(ultimoBotonDigito.getCasilla().getGrafico());
									corrompeRegla = false;
									ultimoBotonDigito = null;
									ultimoLabelModificado = prueba;
									deshabilitarCuadrantes(true);
									deshabilitarDigitos(true);
								}
							} else {
								if (ultimoBotonDigito != null && !prueba.getBotonLogica().esInicial()) {
									prueba.getBotonLogica().setValor(ultimoBotonDigito.getCasilla().getValor());
									prueba.setIcon(ultimoBotonDigito.getCasilla().getGrafico());
									if (!game.esCorrecto(fila, columna)) {
										prueba.getBotonLogica().setcorrompeRegla(true);
										deshabilitarCuadrantes(false);
										prueba.setEnabled(true);
										corrompeRegla = true;
										ultimoLabelModificado = prueba;
										prueba.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
										ultimoBotonDigito = null;
										deshabilitarDigitos(false);

									}
								}

							}
							ultimoBotonDigito = null;

						}
					}
				});
			}
		
		for (int i = 0; i < arregloReloj.length; i++) {
			arregloReloj[i] = new JLabel();
			if (i == 2 || i == 5)
				arregloReloj[i].setIcon(new ImageIcon(this.getClass().getResource("/imgReloj/dospuntos.png")));
			panel_reloj.add(arregloReloj[i]);
		}
		LocalTime start = LocalTime.now();

		TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                LocalTime stop = LocalTime.now( );
                Duration d = Duration.between(start, stop);
                String hms = String.format("%02d:%02d:%02d", 
                                d.toHours(), 
                                d.toMinutesPart(), 
                                d.toSecondsPart());
                
                for(int i = 0; i < hms.length(); i++) {
                    if(hms.charAt(i) != ':')
                        arregloReloj[i].setIcon(new ImageIcon(this.getClass().getResource("/imgReloj/numero"+hms.charAt(i)+".jpg")));
                    	
                }
            
            };
        };
        t.schedule(tt, 0, 1000);

	}
}
