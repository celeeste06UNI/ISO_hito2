package presentacion;
import dominio.Cancion;
import dominio.GestorCancion;
import persistencia.ManejadorBD;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class VentanaApp {

	private JFrame frame;
	GestorCancion gesCan = new GestorCancion();
	ManejadorBD manejador = new ManejadorBD();
	
	private JPanel Streaming;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaApp window = new VentanaApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public VentanaApp() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaApp.class.getResource("/Presentacion/music.png")));
		frame.setBounds(100, 100, 500, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSpotif = new JLabel("SPOTIF");
		lblSpotif.setForeground(new Color(128, 0, 0));
		lblSpotif.setIcon(new ImageIcon(VentanaApp.class.getResource("/Presentacion/music.png")));
		lblSpotif.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 25));
		lblSpotif.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblSpotif, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		Streaming = new JPanel();
		tabbedPane.addTab("Streaming", null, Streaming, null);
		GridBagLayout gbl_Streaming = new GridBagLayout();
		gbl_Streaming.columnWidths = new int[] { 164, 104, 38, 70, 89, 0 };
		gbl_Streaming.rowHeights = new int[] { 30, 46, 111, 0, 41, 0 };
		gbl_Streaming.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Streaming.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Streaming.setLayout(gbl_Streaming);

		JLabel lblCanciones = new JLabel("Canciones");
		GridBagConstraints gbc_lblCanciones = new GridBagConstraints();
		gbc_lblCanciones.insets = new Insets(0, 0, 5, 5);
		gbc_lblCanciones.gridx = 0;
		gbc_lblCanciones.gridy = 0;
		Streaming.add(lblCanciones, gbc_lblCanciones);

		JLabel lblDatos = new JLabel("Datos");
		GridBagConstraints gbc_lblDatos = new GridBagConstraints();
		gbc_lblDatos.gridwidth = 2;
		gbc_lblDatos.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatos.gridx = 1;
		gbc_lblDatos.gridy = 0;
		Streaming.add(lblDatos, gbc_lblDatos);
		
		JLabel lblReproductor = new JLabel("Reproductor");
		GridBagConstraints gbc_lblReproductor = new GridBagConstraints();
		gbc_lblReproductor.gridwidth = 2;
		gbc_lblReproductor.insets = new Insets(0, 0, 5, 0);
		gbc_lblReproductor.gridx = 3;
		gbc_lblReproductor.gridy = 0;
		Streaming.add(lblReproductor, gbc_lblReproductor);
		
		JPanel panel = new JPanel();
		panel.setEnabled(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		Streaming.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(20, 40, 139, 57);
		panel.add(lblNewLabel);
		
				
		JButton btnReproducir = new JButton("Play");
		btnReproducir.setIcon(new ImageIcon(VentanaApp.class.getResource("/Presentacion/play.png")));
		btnReproducir.setEnabled(false);

		GridBagConstraints gbc_btnReproducir = new GridBagConstraints();
		gbc_btnReproducir.insets = new Insets(0, 0, 5, 5);
		gbc_btnReproducir.gridx = 0;
		gbc_btnReproducir.gridy = 3;
		Streaming.add(btnReproducir, gbc_btnReproducir);

		JPanel Local = new JPanel();
		tabbedPane.addTab("Local", null, Local, null);

		conexion(Streaming);
		mostrarCancionesS(Streaming , btnReproducir,lblNewLabel);
		

	}

	public void conexion(JPanel streaming) throws SQLException {
		boolean controlConexion;
		
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		streaming.add(label, gbc_label);

		controlConexion = gesCan.conexion();
		System.out.println("Hola:" + controlConexion);
		if (controlConexion) {
			label.setText("Conectado");
		} else {
			label.setText("Conectado");
		}

	}

	public void mostrarCancionesS(JPanel streaming, final JButton btnReproducir, final JLabel lblNewLabel) throws SQLException {
		int i;
		ArrayList<Cancion> canciones = new ArrayList();
		canciones = gesCan.cancionesBBDD();
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		streaming.add(scrollPane, gbc_scrollPane);

		final JList list = new JList();

		scrollPane.setViewportView(list);

		final DefaultListModel modeloLista = new DefaultListModel();
		list.setModel(modeloLista);

		for (i = 0; i < canciones.size(); i++) {
			modeloLista.addElement(canciones.get(i).getTitulo());
		}
		
		
		mostrarDatosCanciones(streaming, modeloLista, list, btnReproducir, lblNewLabel);
		

	}

	public void mostrarDatosCanciones(JPanel streaming,  final DefaultListModel modeloLista, final JList list, final JButton btnReproducir, final JLabel lblNewLabel) throws SQLException {
		
		final JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		streaming.add(textArea, gbc_textArea);
		
		final JButton btnReplay = new JButton("Replay");
		
		btnReplay.setIcon(new ImageIcon(VentanaApp.class.getResource("/Presentacion/play.png")));
		btnReplay.setEnabled(false);
		GridBagConstraints gbc_btnReplay = new GridBagConstraints();
		gbc_btnReplay.insets = new Insets(0, 0, 5, 5);
		gbc_btnReplay.gridx = 3;
		gbc_btnReplay.gridy = 3;
		Streaming.add(btnReplay, gbc_btnReplay);
		
		final JButton btnPause = new JButton("Pause");
		btnPause.setIcon(new ImageIcon(VentanaApp.class.getResource("/Presentacion/pause.png")));
		btnPause.setEnabled(false);
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 5, 0);
		gbc_btnPause.gridx = 4;
		gbc_btnPause.gridy = 3;
		Streaming.add(btnPause, gbc_btnPause);
		
		final JButton btnSalir = new JButton("Cerrar");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridwidth = 2;
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.gridx = 3;
		gbc_btnSalir.gridy = 4;
		Streaming.add(btnSalir, gbc_btnSalir);
		
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ArrayList<Cancion> canciones = new ArrayList();

				int posicion = 0;
				if (e.getClickCount() == 2) {
					posicion = list.locationToIndex(e.getPoint());

					try {
						canciones = gesCan.cancionesBBDD();
						for (int i = 0; i < canciones.size(); i++) {
							String Titulo = (String) modeloLista.getElementAt(posicion);

							if (canciones.get(i).getTitulo().equals(Titulo)) {
							
								textArea.setText("Nombre: " + canciones.get(i).getTitulo() + "\n" + "Precio:" + canciones.get(i).getPrecio() + " €");
								btnReproducir.setEnabled(true);
								btnReproducir.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										lblNewLabel.setText("REPRODUCIENDO");
										btnPause.setEnabled(true);
										

									}
								});
								
								clickPause(btnPause, lblNewLabel, btnReplay);
								clickReplay(btnReplay, lblNewLabel, btnPause);
								clickCerrar(btnSalir, lblNewLabel,btnReplay,btnPause);
							}

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		};
		list.addMouseListener(mouseListener);
		
	}
	
	public void clickPause(final JButton btnPause, final JLabel lblNewLabel, final JButton btnReplay) {
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPause.setEnabled(false);
				lblNewLabel.setText("CANCION EN PAUSE");
				btnReplay.setEnabled(true);
				
			}
		});
		
	}
	
	public void clickReplay(final JButton btnReplay, final JLabel lblNewLabel, final JButton btnPause) {
		btnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText("REPRODUCIENDO");
				btnReplay.setEnabled(false);
				btnPause.setEnabled(true);
			}
			
		});
		
	}
	
	public void clickCerrar(JButton btnSalir, final JLabel lblNewLabel, final JButton btnReplay, final JButton btnPause) {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText("");
				btnReplay.setEnabled(false);
				btnPause.setEnabled(false);
			}
		});
	}
	
	
	

}
