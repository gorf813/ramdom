import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase Ventana
 * Muestra la estructuta que deberia tener una Ventana en Java con la libreria
 * Swing, contiene una etiqueta, un caja de texto y un boton, que tiene la
 * accion de mostrar el texto en la caja por una ventana de mensaje.
 * @author Daniel Alvarez (a3dany)
 */
public class Ventana extends JFrame {

    private JLabel texto;           // etiqueta o texto no editable
    private JTextField caja;        // caja de texto, para insertar datos
    private JButton boton;          // boton con una determinada accion
    private JButton random;

    public Ventana() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }

    private void configurarVentana() {
        this.setTitle("Esta Es Una Ventana");                   // colocamos titulo a la ventana
        this.setSize(310, 250);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        texto = new JLabel();
        caja = new JTextField();
        boton = new JButton();
        random = new JButton();
        // configuramos los componentes
        texto.setText("Inserte Nombre");    // colocamos un texto a la etiqueta
        texto.setBounds(50, 50, 100, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        caja.setBounds(150, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        boton.setText("Ingresar Alumno");   // colocamos un texto al boton
        boton.setBounds(50, 100, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = caja.getText();                                 // obtenemos el contenido de la caja de texto
                JOptionPane.showMessageDialog(null, "Alumno  " + nombre + " ingresado correctamente.");    // mostramos un mensaje (frame, mensaje)
                String ruta = "BDAlumno.txt";
                File archivo = new File(ruta);
                BufferedWriter bw;
                if(archivo.exists()) {

                    try {
                        bw = new BufferedWriter(new FileWriter(archivo,true));
                        bw.write(nombre+"\n");

                        bw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else {

                    try {
                        bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write(nombre+"\n");

                        bw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

                caja.setText("");


            }
        });      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        // adicionamos los componentes a la ventana.

        random.setText("Random");   // colocamos un texto al boton
        random.setBounds(50, 150, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)

        random.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                String ruta = "BDAlumno.txt";
                File archivo = new File(ruta);

                String sCadena;
                String sCadena2;
                int lNumeroLineas = 1;
                int lLineas = 1;
                int ran = 0;

                try {
                    BufferedReader bw = new BufferedReader(new FileReader(archivo));
                    while ((sCadena = bw.readLine())!=null) {
                        lNumeroLineas++;
                    }
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                ran = (int)(Math.random() * ((lNumeroLineas) - 1)+1);

                try {
                    BufferedReader bw2 = new BufferedReader(new FileReader(archivo));

                    while ((sCadena2 = bw2.readLine())!=null) {

                        if( lLineas == ran ){
                            JOptionPane.showMessageDialog(null, sCadena2  );    // mostramos un mensaje (frame, mensaje)

                        }
                        lLineas++;
                    }
                    bw2.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        this.add(texto);
        this.add(caja);
        this.add(boton);
        this.add(random );
    }


    public static void main(String[] args) {
        Ventana V = new Ventana();      // creamos una ventana
        V.setVisible(true);             // hacemos visible la ventana creada
    }
}