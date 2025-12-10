/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040109.modul9;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 *
 * @author Nauval Muhammad Abdu
 */
public class AplikasiFileIO extends JFrame {
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    private JButton btnSaveObj, btnLoadObj;
    private JButton btnAppendText;

    public AplikasiFileIO() {
    super("Tutorial File IO & Exception Handling");
    setSize(600, 400); // Ukuran window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Inisialisasi TextArea dan FileChooser
    textArea = new JTextArea();
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    fileChooser = new JFileChooser();

    // --- BAGIAN INI YANG DIUBAH AGAR TOMBOL TURUN KE BAWAH ---
    JPanel buttonPanel = new JPanel();
    
    // Gunakan GridLayout(Baris, Kolom, JarakHorisontal, JarakVertikal)
    // Kita buat 2 Baris, 4 Kolom, dengan jarak antar tombol 5 pixel
    buttonPanel.setLayout(new GridLayout(2, 4, 5, 5)); 
    
    // ---------------------------------------------------------

    // Inisialisasi Tombol (Sama seperti sebelumnya)
    btnOpenText = new JButton("Buka Text");
    btnSaveText = new JButton("Simpan Text");
    btnSaveBinary = new JButton("Simpan Config"); // Teks diperpendek biar rapi
    btnLoadBinary = new JButton("Muat Config");
    btnSaveObj = new JButton("Simpan Object");
    btnLoadObj = new JButton("Muat Object");
    btnAppendText = new JButton("Tambah Text");

    // Masukkan ke panel (Urutannya akan mengisi kiri-kanan, lalu turun ke bawah)
    buttonPanel.add(btnOpenText);
    buttonPanel.add(btnSaveText);
    buttonPanel.add(btnSaveBinary);
    buttonPanel.add(btnLoadBinary);
    // -- Pindah ke baris 2 otomatis karena kolomnya cuma 4 --
    buttonPanel.add(btnSaveObj);
    buttonPanel.add(btnLoadObj);
    buttonPanel.add(btnAppendText);

    // Layout Utama
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Handling (Code tetap sama, tidak perlu diubah)
    btnOpenText.addActionListener(e -> bukaFileTeks());
    btnSaveText.addActionListener(e -> simpanFileTeks());
    btnSaveBinary.addActionListener(e -> simpanConfigBinary());
    btnLoadBinary.addActionListener(e -> muatConfigBinary());
    btnSaveObj.addActionListener(e -> simpanUserConfig());
    btnLoadObj.addActionListener(e -> muatUserConfig());
    btnAppendText.addActionListener(e -> tambahTeks());

    muatCatatanTerakhir();
}

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt();
            
            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
        // Method baru untuk Latihan 2
    }
    private void muatCatatanTerakhir() {
    try (BufferedReader reader = new BufferedReader(new FileReader("last_notes.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            textArea.append(line + "\n");
        }
    } catch (IOException e) {
        // Biarkan kosong agar tidak error jika file belum ada
    }
    
    // LATIHAN 3: Menyimpan Objek Utuh (Serialized)
    }
private void simpanUserConfig() {
    // Kita buat objek UserConfig
    UserConfig config = new UserConfig();
    
    // Ambil data dari UI
    String name = JOptionPane.showInputDialog(this, "Masukkan Username:");
    if (name == null || name.isEmpty()) return; // Batal jika kosong
    
    config.setUsername(name);
    config.setFontSize(textArea.getFont().getSize()); // Ambil ukuran font saat ini

    // Simpan objek ke file "user.dat" menggunakan ObjectOutputStream
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
        oos.writeObject(config); // Menulis objek
        JOptionPane.showMessageDialog(this, "Objek UserConfig berhasil disimpan!");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
        ex.printStackTrace();
    }
}

// LATIHAN 3: Membaca Objek Utuh (Deserialized)
private void muatUserConfig() {
    // Baca file "user.dat" menggunakan ObjectInputStream
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"))) {
        
        // Baca objek dan casting kembali ke UserConfig
        UserConfig config = (UserConfig) ois.readObject();
        
        // Terapkan data ke aplikasi
        textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
        JOptionPane.showMessageDialog(this, "Config dimuat!\nUsername: " + config.getUsername() + 
                                      "\nFont Size: " + config.getFontSize());
                                      
    } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "File user.dat belum ada!");
    } catch (IOException | ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca objek: " + ex.getMessage());
    }
    
}
    // LATIHAN 4: Menambahkan Text (Append)
private void tambahTeks() {
    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        
        // Perhatikan parameter 'true' pada FileWriter constructor
        // true = append (menambahkan), false/kosong = overwrite (menimpa)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            
            writer.write(textArea.getText()); // Tulis apa yang ada di area
            writer.newLine(); // Tambahkan baris baru agar rapi
            
            JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan ke file!");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan file: " + ex.getMessage());
        }
    }
}
}




