/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040109.modul05;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.SwingUtilities;
/**
 *
 * @author Nauval Muhammad Abdu
 */
public class latihan2 {
    public static void main(String[] args) {
        //menjalankan kode GUI di event Dispatch Thread (EDT)
        //ini adalah praktik terbaik untuk menghindari masalah thread
        //akan dijelaskan lebih detail nanti
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //1. buat objek JFrame
                JFrame frame = new JFrame("Jendela dengan Label");
                
                //2. atur ukuran jendela (lebar 400, tinggi 300)
                frame.setSize(400, 300);
                
                //3. atur aksi saat tombol close (X) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //4. buat komponen JLabel
                JLabel label = new JLabel("Ini adalah JLabel.");
                
                //5. tambahkan JLabel ke JFrame
                // secara default, jFrame menggunakan BorderLayout,
                //dan .add() akan menambahkannya ke bagian tengah (CENTER).
                frame.add(label);
                
                //6. buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}
