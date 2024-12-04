package uaspbo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class PelangganFrame extends JFrame {
    private JTextField tfNama, tfNIK, tfNoTelp, tfAlamat;
    private JButton btnTambah, btnUbah, btnHapus, btnTampilkan;
    private JTable table;
    private DefaultTableModel tableModel;

    public PelangganFrame() {
        setTitle("CRUD Data Pelanggan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(20, 20, 100, 20);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(120, 20, 200, 20);
        add(tfNama);

        JLabel lblNIK = new JLabel("NIK:");
        lblNIK.setBounds(20, 50, 100, 20);
        add(lblNIK);

        tfNIK = new JTextField();
        tfNIK.setBounds(120, 50, 200, 20);
        add(tfNIK);

        JLabel lblNoTelp = new JLabel("No Telp:");
        lblNoTelp.setBounds(20, 80, 100, 20);
        add(lblNoTelp);

        tfNoTelp = new JTextField();
        tfNoTelp.setBounds(120, 80, 200, 20);
        add(tfNoTelp);

        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setBounds(20, 110, 100, 20);
        add(lblAlamat);

        tfAlamat = new JTextField();
        tfAlamat.setBounds(120, 110, 200, 20);
        add(tfAlamat);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(20, 150, 100, 20);
        add(btnTambah);

        btnUbah = new JButton("Ubah");
        btnUbah.setBounds(130, 150, 100, 20);
        add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(240, 150, 100, 20);
        add(btnHapus);

        btnTampilkan = new JButton("Tampilkan");
        btnTampilkan.setBounds(350, 150, 100, 20);
        add(btnTampilkan);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "NIK", "No Telp", "Alamat"}, 0);
        table = new JTable(tableModel);
        JScrollPane spTable = new JScrollPane(table);
        spTable.setBounds(20, 200, 550, 150);
        add(spTable);

        btnTambah.addActionListener(e -> tambahData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnTampilkan.addActionListener(e -> tampilkanData());

        setVisible(true);
    }

    private void tambahData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lapakmobil", "root", "")) {
            String sql = "INSERT INTO DataPelanggan (nama, nik, notelp, alamat) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfNama.getText());
            stmt.setString(2, tfNIK.getText());
            stmt.setString(3, tfNoTelp.getText());
            stmt.setString(4, tfAlamat.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void ubahData() {}

    private void hapusData() {}

    private void tampilkanData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lapakmobil", "root", "")) {
            String sql = "SELECT * FROM DataPelanggan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("idpelanggan"),
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("notelp"),
                        rs.getString("alamat")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PelangganFrame();
    }
}


