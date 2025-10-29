/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040109.modul05;
    import java.awt.FlowLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.SwingUtilities;
/**
 *
 * @author Nauval Muhammad Abdu
 */
public class latihan3 {
    public static void main(String[] args) {
        //menjalankan kode GUI di event Dispatch Thread (EDT)
        //ini adalah praktik terbaik untuk menghindari masalah thread
        //akan dijelaskan lebih detail nanti
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //1. buat objek JFrame
                JFrame frame = new JFrame("Label dan Tombol");
                
                //2. atur ukuran jendela (lebar 400, tinggi 300)
                frame.setSize(400, 300);
                
                //3. atur aksi saat tombol close (X) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //4. atur Layout Manager
                //FlowLayout akan menyusun komponen dari kiri ke kanan.
                frame.setLayout(new FlowLayout());
                
                //5. buat komponen GUI
                JLabel label = new JLabel("Teks Awal");
                JButton button = new JButton("Klik Saya!");
                
                /*6. Tambahkan aksi (actionlistener) ke tombol
                penambahan ini menggunakan lambda untuk mempersingkat
                penggunaan anonymous inner class
                */
                button.addActionListener(e ->  {
                    //aksi ini akan mengubah teks pada label
                    label.setText("Tombol berhasil diklik!");
                });
                /*7. tambahkan komponen ke frame karena kita
                menggunakan flowlayoout, keduanya akan tamppil
                berdampingan
                */
                frame.add(label);
                frame.add(button);
                
                
                //8. buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}
