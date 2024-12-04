package uaspbo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class PenjualanFrame extends JFrame {
    private JTextField tfIdPelanggan, tfIdMobil, tfTotalBiaya;
    private JButton btnTambah, btnUbah, btnHapus, btnTampilkan;
    private JTable table;
    private DefaultTableModel tableModel;

    public PenjualanFrame() {
        setTitle("CRUD Data Penjualan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblIdPelanggan = new JLabel("ID Pelanggan:");
        lblIdPelanggan.setBounds(20, 20, 100, 20);
        add(lblIdPelanggan);

        tfIdPelanggan = new JTextField();
        tfIdPelanggan.setBounds(120, 20, 200, 20);
        add(tfIdPelanggan);

        JLabel lblIdMobil = new JLabel("ID Mobil:");
        lblIdMobil.setBounds(20, 50, 100, 20);
        add(lblIdMobil);

        tfIdMobil = new JTextField();
        tfIdMobil.setBounds(120, 50, 200, 20);
        add(tfIdMobil);

        JLabel lblTotalBiaya = new JLabel("Total Biaya:");
        lblTotalBiaya.setBounds(20, 80, 100, 20);
        add(lblTotalBiaya);

        tfTotalBiaya = new JTextField();
        tfTotalBiaya.setBounds(120, 80, 200, 20);
        add(tfTotalBiaya);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(20, 120, 100, 20);
        add(btnTambah);

        btnUbah = new JButton("Ubah");
        btnUbah.setBounds(130, 120, 100, 20);
        add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(240, 120, 100, 20);
        add(btnHapus);

        btnTampilkan = new JButton("Tampilkan");
        btnTampilkan.setBounds(350, 120, 100, 20);
        add(btnTampilkan);

        tableModel = new DefaultTableModel(new String[]{"ID Penjualan", "ID Pelanggan", "ID Mobil", "Total Biaya"}, 0);
        table = new JTable(tableModel);
        JScrollPane spTable = new JScrollPane(table);
        spTable.setBounds(20, 160, 550, 180);
        add(spTable);

        btnTambah.addActionListener(e -> tambahData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnTampilkan.addActionListener(e -> tampilkanData());

        setVisible(true);
    }

    private void tambahData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lapakmobil", "root", "")) {
            String sql = "INSERT INTO DataPenjualan (idpelanggan, idmobil, totalbiaya) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(tfIdPelanggan.getText()));
            stmt.setInt(2, Integer.parseInt(tfIdMobil.getText()));
            stmt.setDouble(3, Double.parseDouble(tfTotalBiaya.getText()));
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
            String sql = "SELECT * FROM DataPenjualan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("idpenjualan"),
                        rs.getInt("idpelanggan"),
                        rs.getInt("idmobil"),
                        rs.getDouble("totalbiaya")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PenjualanFrame();
    }
}
