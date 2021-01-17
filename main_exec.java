import java.io.IOException;

import javax.swing.JFrame;

public class main_exec {
	public static void main(String[]args) throws IOException {
		main_interface f=new main_interface();
		f.setTitle("Vendor!!");
        f.setVisible(true);
        f.setBounds(10,10,400,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
	}
}
