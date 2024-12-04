package uaspbo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class MobilFrame extends JFrame {
    private JTextField tfMerk, tfTahun, tfHarga;
    private JButton btnTambah, btnUbah, btnHapus, btnTampilkan;
    private JTable table;
    private DefaultTableModel tableModel;

    public MobilFrame() {
        setTitle("CRUD Data Mobil");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblMerk = new JLabel("Merk:");
        lblMerk.setBounds(20, 20, 100, 20);
        add(lblMerk);

        tfMerk = new JTextField();
        tfMerk.setBounds(120, 20, 200, 20);
        add(tfMerk);

        JLabel lblTahun = new JLabel("Tahun:");
        lblTahun.setBounds(20, 50, 100, 20);
        add(lblTahun);

        tfTahun = new JTextField();
        tfTahun.setBounds(120, 50, 200, 20);
        add(tfTahun);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(20, 80, 100, 20);
        add(lblHarga);

        tfHarga = new JTextField();
        tfHarga.setBounds(120, 80, 200, 20);
        add(tfHarga);

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

        tableModel = new DefaultTableModel(new String[]{"ID", "Merk", "Tahun", "Harga"}, 0);
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
            String sql = "INSERT INTO DataMobil (merk, tahun, harga) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfMerk.getText());
            stmt.setInt(2, Integer.parseInt(tfTahun.getText()));
            stmt.setDouble(3, Double.parseDouble(tfHarga.getText()));
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
            String sql = "SELECT * FROM DataMobil";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("idmobil"),
                        rs.getString("merk"),
                        rs.getInt("tahun"),
                        rs.getDouble("harga")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MobilFrame();
    }
}
