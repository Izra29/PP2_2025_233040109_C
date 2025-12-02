/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040109.modul7;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Nauval Muhammad Abdu
 */
public class TugasModifikasi extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public TugasModifikasi() {
        // 1. Konfigurasi Frame Utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel untuk Tab 1 
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel untuk Tab 2 
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }

    // Method untuk membuat desain tab input 
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
            "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan dan Reset (Tugas 4)
        JButton btnSimpan = new JButton("Simpan Data");
        JButton btnReset = new JButton("Reset Input");
        panel.add(btnReset);
        panel.add(btnSimpan);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        // Event Handling Tombol Reset (Tugas 4)
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
                cmbMatkul.setSelectedIndex(0);
            }
        });
        return panel;
    }

    // Method untuk membuat desain tab tabel (Tugas 2)
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tambah Tombol Hapus (Tugas 2)
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        southPanel.add(btnHapus);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Event Handling Tombol Hapus (Tugas 2)
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                    // PERBAIKAN 1: Menggunakan TugasModifikasi.this
                    JOptionPane.showMessageDialog(TugasModifikasi.this, "Data berhasil dihapus!");
                } else {
                    // PERBAIKAN 1: Menggunakan TugasModifikasi.this
                    JOptionPane.showMessageDialog(TugasModifikasi.this, "Pilih baris data yang ingin dihapus terlebih dahulu.",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return panel;
    }

    // Logika validasi dan penyimpanan data (Tugas 1 & 3)
    private void prosesSimpan() {
        // 1. Ambil data dari input
        String nama = txtNama.getText().trim();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // Validasi 1: Cek nama kosong dan minimal 3 karakter (Tugas 3)
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama harus minimal 3 karakter!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi 2: Cek nilai
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Logika Bisnis (Menentukan Grade) Menggunakan switch case (Tugas 1)
        String grade;
        int nilaiPuluhan = nilai / 10;
        switch (nilaiPuluhan) {
            case 10:
            case 9:
            case 8:
                grade = "A";
                break;
            case 7:
                grade = "AB";
                break;
            case 6:
                grade = "B";
                break;
            case 5:
                grade = "BC";
                break;
            case 4:
                grade = "C";
                break;
            case 3:
                grade = "D";
                break;
            default:
                grade = "E";
                break;
        }

        // 4. Masukkan ke Tabel 
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TugasModifikasi().setVisible(true);
            }
        });
    }
}
