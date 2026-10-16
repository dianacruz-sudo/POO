package Polizasdos;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Welcometo extends JFrame implements ActionListener {

    JMenuBar bm; //Barra de menu
    JMenu mArchivo, mBD; //Menus
    JMenuItem MBDC, MBDR, MBDE, MBDA; //Submenus

    JLabel lb1, lbNombre, lbid, lbApellido, lbNivel, lbEdad, lbSexo;
    JTextField txtNombre, txtId, txtApellido, txtNivel, txtEdad, txtSexo;
    JButton Insertar, Consultar, Modificar, Eliminar;

    Vendedor vendedor;

    public Welcometo() {
        this.setBounds(350, 50, 800, 600);//Horizontal, Vertical,Ancho,Alto
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        bm = new JMenuBar();
        this.setJMenuBar(bm);

        mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);

        mBD = new JMenu("Bases de datos");
        bm.add(mBD);

        this.MBDC = new JMenuItem("Insertar");
        mBD.add(MBDC);
         this.MBDC.addActionListener(e -> {
            Insertar();
        });

        this.MBDR = new JMenuItem("Consultar");
        mBD.add(MBDR);
         this.MBDR.addActionListener(e -> {
            Consultar();
        });

        this.MBDE = new JMenuItem("Modificar");
        mBD.add(MBDE);
         this.MBDE.addActionListener(e -> {
            Modificar();
        });

        this.MBDA = new JMenuItem("Eliminar");
        mBD.add(MBDA);
         this.MBDA.addActionListener(e -> {
            Eliminar();
        });

        this.setLayout(null);
        this.lb1 = new JLabel();
        this.lb1.setBounds(300, 20, 250, 30);
        this.lb1.setText("Welcome to Polizas");
        this.add(lb1);

        this.lbid = new JLabel("Id");
        this.lbid.setBounds(60, 80, 80, 30); //Izquierda o derecha,arriba o abajo,Ancho,Alto
        this.add(lbid);

        this.txtId = new JTextField();
        this.txtId.setBounds(100, 80, 120, 30); 
        this.add(txtId);

        this.lbNombre = new JLabel("Nombre");
        this.lbNombre.setBounds(50, 130, 80, 30);
        this.add(lbNombre);

        this.txtNombre = new JTextField();
        this.txtNombre.setBounds(100, 130, 120, 30);
        this.add(txtNombre);

        this.lbApellido = new JLabel("Apellido");
        this.lbApellido.setBounds(50, 180, 80, 30);
        this.add(lbApellido);

        this.txtApellido = new JTextField();
        this.txtApellido.setBounds(100, 180, 120, 30);
        this.add(txtApellido);

        this.lbNivel = new JLabel("Nivel");
        this.lbNivel.setBounds(350, 80, 80, 30);
        this.add(lbNivel);

        this.txtNivel = new JTextField();
        this.txtNivel.setBounds(400, 80, 120, 30);
        this.add(txtNivel);

        this.lbEdad = new JLabel("Edad");
        this.lbEdad.setBounds(350, 130, 80, 30);
        this.add(lbEdad);

        this.txtEdad = new JTextField();
        this.txtEdad.setBounds(400, 130, 120, 30);
        this.add(txtEdad);

        this.lbSexo = new JLabel("Sexo");
        this.lbSexo.setBounds(350, 180, 80, 30);
        this.add(lbSexo);

        this.txtSexo = new JTextField();
        this.txtSexo.setBounds(400, 180, 120, 30);
        this.add(txtSexo);

        this.Insertar = new JButton("Insertar");
        this.Insertar.setBounds(70, 250, 150, 30);
        this.add(Insertar);

        this.Consultar = new JButton("Consultar");
        this.Consultar.setBounds(230, 250, 150, 30);
        this.add(this.Consultar);

        this.Modificar = new JButton("Modificar");
        this.Modificar.setBounds(390, 250, 120, 30);
        this.add(this.Modificar);

        this.Eliminar = new JButton("Eliminar");
        this.Eliminar.setBounds(520, 250, 120, 30);
        this.add(this.Eliminar);

        this.Insertar.addActionListener(this);
        this.Consultar.addActionListener(this);
        this.Modificar.addActionListener(this);
        this.Eliminar.addActionListener(this);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Welcometo polizas = new Welcometo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Consultar) {
            System.out.println("Consultando datos ");

            vendedor = new Vendedor();
            vendedor.setNombre(this.txtNombre.getText());
            Consultar();
        }

        if (e.getSource() == this.Insertar) {
            System.out.println("Agregando información..");
            this.Insertar();
        }

        if (e.getSource() == this.Modificar) {
            System.out.println("Actualizando..");
            this.Modificar();
        }
        if (e.getSource() == this.Eliminar) {
            System.out.println("Eliminando Vendedor...");
            this.Eliminar();
        }
    }

    private void Consultar() {

        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("select * from vendedor");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + "   Nombre: " + rs.getString("nombre")
                        + "   Apellido: " + rs.getString("apellido")
                        + "   Nivel: " + rs.getString("nivel")
                        + "   Edad: " + rs.getInt("edad")
                        + "   Sexo: " + rs.getString("sexo"));
            }

            System.out.println("Conexion exitosa");
            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error al consultar: " + ex.getMessage());
        }

    }

    private void Insertar() {
        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("insert into vendedor(id, nombre, apellido, nivel, edad, sexo) values(?,?, ?, ?, ?, ?)");

            ps.setInt(1, Integer.parseInt(this.txtId.getText()));
            ps.setString(2, this.txtNombre.getText());
            ps.setString(3, this.txtApellido.getText());
            ps.setString(4, this.txtNivel.getText());
            ps.setInt(5, Integer.parseInt(this.txtEdad.getText()));
            ps.setString(6, this.txtSexo.getText());

            ps.execute();
            cnx.close(); // Para cerrar la conexión correctamente
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }

    }

    private void Modificar() {
        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("update vendedor set nombre = ?, apellido = ?, nivel = ?, edad = ?, sexo = ? where id = ?;");

            ps.setString(1, this.txtNombre.getText());
            ps.setString(2, this.txtApellido.getText());
            ps.setString(3, this.txtNivel.getText());
            ps.setInt(4, Integer.parseInt(this.txtEdad.getText()));
            ps.setString(5, this.txtSexo.getText());
            ps.setInt(6, Integer.parseInt(this.txtId.getText()));

            ps.executeUpdate();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al modificar: " + ex.getMessage());
        }

    }

    private void Eliminar() {
        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("DELETE FROM vendedor WHERE id = ?;");

            ps.setInt(1, Integer.parseInt(this.txtId.getText()));

            ps.executeUpdate();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }

    }

}
