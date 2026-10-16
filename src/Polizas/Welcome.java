package Polizas;

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

public class Welcome extends JFrame implements ActionListener {

    JMenuBar bm; //Barra de menu
    JMenu mArchivo, mBD; //Menus
    JMenuItem MBDC, MBDR, MBDE, MBDA; //Submenus

    JLabel lb1, lbNombre, lbid, lbVelocidad;
    JTextField txtNombre, txtId, txtVelocidad;
    JButton enviar, agregar, actualizar, eliminar;
    Persona persona;

    public Welcome() {
        this.setBounds(350, 50, 800, 600);//Horizontal, Vertical,ancho,Alto
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        bm = new JMenuBar();
        this.setJMenuBar(bm);

        mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);

        mBD = new JMenu("Bases de datos");
        bm.add(mBD);

        this.MBDC = new JMenuItem("Crear");
        mBD.add(MBDC);

        this.MBDR = new JMenuItem("Consultar");
        mBD.add(MBDR);
        this.MBDR.addActionListener(e -> {
            JOptionPane.showMessageDialog(rootPane, "Evento capturado");
            consultar();
        });

        this.MBDE = new JMenuItem("Eliminar");
        mBD.add(MBDE);

        this.MBDA = new JMenuItem("Actualizar");
        mBD.add(MBDA);
        this.setLayout(null);
        this.lb1 = new JLabel();
        this.lb1.setBounds(300, 20, 250, 30);
        this.lb1.setText("Welcome to Polizas");
        this.add(lb1);

        this.lbid = new JLabel("Id");
        this.lbid.setBounds(150, 80, 80, 30);
        this.add(lbid);

        this.txtId = new JTextField();
        this.txtId.setBounds(240, 80, 250, 30);
        this.add(txtId);

        this.lbNombre = new JLabel("Nombre");
        this.lbNombre.setBounds(150, 130, 80, 30);
        this.add(lbNombre);

        this.txtNombre = new JTextField("Diana");
        this.txtNombre.setBounds(240, 130, 250, 30);
        this.add(txtNombre);

        this.lbVelocidad = new JLabel("Velocidad");
        this.lbVelocidad.setBounds(150, 180, 80, 30);
        this.add(lbVelocidad);

        this.txtVelocidad = new JTextField();
        this.txtVelocidad.setBounds(240, 180, 250, 30);
        this.add(txtVelocidad);

        this.enviar = new JButton("Consultar");
        this.enviar.setBounds(200, 250, 150, 30);
        this.add(enviar);

        this.agregar = new JButton("Agregar");
        this.agregar.setBounds(350, 250, 150, 30);
        this.add(this.agregar);

        this.actualizar = new JButton("Actualizar");
        this.actualizar.setBounds(75, 250, 120, 30);
        this.add(this.actualizar);

        this.eliminar = new JButton("Eliminar");
        this.eliminar.setBounds(500, 250, 120, 30);
        this.add(this.eliminar);

        this.eliminar.addActionListener(this);
        this.actualizar.addActionListener(this);
        this.agregar.addActionListener(this);
        this.enviar.addActionListener(this);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Welcome polizas = new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.enviar) {
            System.out.println("El nombre es: " + this.txtNombre.getText());

            persona = new Persona();
            persona.setNombre(this.txtNombre.getText());
            //JOptionPane.showMessageDialog(rootPane, persona.getNombre());
            consultar();
        }

        if (e.getSource() == this.agregar) {
            System.out.println("Agregar");
            this.agregar();
        }

        if (e.getSource() == this.actualizar) {
            System.out.println("Actualizando..");
            this.actualizar();
        }
        if (e.getSource() == this.eliminar) {
            System.out.println("Eliminar");
            this.eliminar();
        }
    }

    public void eliminar() {

        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("DELETE FROM marcas WHERE id = ?;");
            ps.setInt(1, Integer.parseInt(this.txtId.getText()));
            ps.executeUpdate();
            cnx.close();
        } catch (SQLException ex) {
            System.getLogger(Welcome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void actualizar() {

        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("update marcas set nombre = ?,velocidad = ? where id = ?;");
            ps.setInt(2, Integer.parseInt(this.txtId.getText()));
            ps.setString(1, this.txtNombre.getText());
            ps.setInt(2, Integer.parseInt(this.txtVelocidad.getText()));
            ps.setInt(3, Integer.parseInt(this.txtId.getText()));

            ps.executeUpdate();
            cnx.close();
        } catch (SQLException ex) {
            System.getLogger(Welcome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void agregar() {

        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("insert into marcas(nombre, velocidad) values(?, ?)");
            ps.setString(1, this.txtNombre.getText());
            ps.setInt(2, Integer.parseInt(this.txtVelocidad.getText()));
            ps.execute();
        } catch (SQLException ex) {
            System.getLogger(Welcome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void consultar() {

        try {
            //Importacion paquete SQL
            //import java.sql.*;
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            ps = cnx.prepareStatement("select * from marcas");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre") + "     velocidad:" + rs.getInt("Velocidad"));
            }

            System.out.println("Conexion exitosa");
            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
            //System.getLogger(Welcome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
}
