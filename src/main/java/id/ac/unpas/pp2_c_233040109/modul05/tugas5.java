/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040109.modul05;
    import java.awt.BorderLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.SwingUtilities; 
/**
 *
 * @author Nauval Muhammad Abdu
 */
public class tugas5 {
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
                
                //4. atur Layout Manager ke borderlayout
                //sebenarnya ini tidak perlu
                //karena borderlayout adalah layout manager default
                frame.setLayout(new BorderLayout());
            
                //5. buat komponen
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                
                // Buat semua tombol
                JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)");
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");
                
                //6. Tambahkan aksi (actionlistener) ke tombol
                
                // Aksi untuk tombol SOUTH (yang sudah ada)
                buttonSouth.addActionListener(e ->  {
                    label.setText("Tombol di SOUTH diklik!");
                });
                
                // Aksi untuk tombol WEST
                buttonWest.addActionListener(e ->  {
                    label.setText("Tombol di WEST diklik!");
                });
                
                // Aksi untuk tombol EAST
                buttonEast.addActionListener(e ->  {
                    label.setText("Tombol di EAST diklik!");
                });
                
                // Aksi untuk tombol CENTER
                buttonCenter.addActionListener(e ->  {
                    label.setText("Tombol di CENTER diklik!");
                });
                
                //7. Tambahkan komponen ke frame dengan posisi
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                
                // Gunakan objek tombol yang sudah memiliki listener
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);
                
                //8. buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}   
