package Firsttry;

import java.awt.Color;

import javax.swing.JFrame;
//inheritance v
public class MyScreen extends JFrame {
	public MyScreen() {
		this.setBackground(Color.black);
		this.setSize(1600,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
	}
}

