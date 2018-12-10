package Firsttry;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {
	
	GoodGuy backdrop = new GoodGuy(-200,-200,800,1600,"files/jungle.png");
	GoodGuy link = new GoodGuy(-200,0,150,150,"files/diddy.png");
	GoodGuy gameover = new GoodGuy(-200,-200,800,1600,"files/green.png");
	GoodGuy gamewon = new GoodGuy(-200,-200,800,1600,"files/blue.png");
	LinkedList<Badguy> badguys = new LinkedList();
	LinkedList knives = new LinkedList();
	boolean homescreen = false;
	boolean lose = false;
	boolean win = false;
	Image home = Toolkit.getDefaultToolkit().getImage("files/black.png");
	String demost = "Press enter to play";
	Image diddy = Toolkit.getDefaultToolkit().getImage("files/diddy.png");
	Image green = Toolkit.getDefaultToolkit().getImage("files/green.png");
	String greeen = "Game Over";
	Image blue = Toolkit.getDefaultToolkit().getImage("files/blue.png");
	String mobe = "Eliminate the lions";
	
	int time = 0;
	private GoodGuy bg;
	
	public MyCanvas() {
		this.setSize(1600,800);
		this.addKeyListener(this);
		playIt("files/Super.wav");
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<30; i++) {
			Badguy bg = new Badguy(800+rand.nextInt(800),rand.nextInt(900), 100, 100,"files/Badguy.png");
			Rectangle r = new Rectangle(100,100,30,30);
			if (r.contains(link.getxCoord(), link.getyCoord())) {
				System.out.println("badguy on top of link");
				continue;
			}
			badguys.add(bg);
		}
	}	

	public void paint(Graphics g) {
		g.setFont(new Font("AlBayan", Font.PLAIN, 100));
		if (homescreen == false) {
			this.setBackground(Color.BLACK);
			g.setColor(Color.WHITE);
			g.drawImage(home, 1600, 800, this);
			g.drawString(demost,10,100);
			g.drawImage(diddy, 800, 400,200,200,this);
		}
		if (homescreen == true) {
			
		
		g.drawImage(backdrop.getImg(), backdrop.getxCoord(), backdrop.getyCoord(), backdrop.getHeight(), backdrop.getWidth(), this);
		
		
		if (lose == false) {
			g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getHeight(), link.getWidth(), this);

			if (win == false) {
				g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getHeight(), link.getWidth(), this);
				
		for(int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			g.drawImage(bg.getImg(), (int) bg.getxCoord(), (int) bg.getyCoord(), (int) bg.getWidth(), (int) bg.getHeight(), this);
			Rectangle r = new Rectangle ((int) bg.getxCoord(),(int) bg.getyCoord(),(int) bg.getWidth(),(int) bg.getHeight());
			if (bg.getxCoord() < 0) {
				g.drawImage(green, 800, 800, this);
				g.drawString(greeen, 10, 10);
			}
			else if (bg.getxCoord() > 0) {
				g.drawImage(blue, 800, 800, this);
				g.drawString(mobe, 100, 100);
				
		{
				bg.setxCoord(bg.getxCoord()- 0.75);
			}
			
		}
		
			for(int j = 0; j < knives.size(); j++) {
				projectile k = (projectile) knives.get(j);
				if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
				 {
					k.setxCoord(k.getxCoord());
				}
				g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
				
				Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getHeight(),k.getWidth());
			//	Rectangle r = new Rectangle(Math.round(bg.getxCoord()), Math.round(bg.getyCoord()), bg.getWidth(), bg.getHeight());
				if (kr.intersects(r)) {
					badguys.remove(i);
					knives.remove(j);
				}
				}if (bg.getxCoord() == 0) {
			lose = true;
			}
		}
	
		repaint();
		}
		}else {
				g.drawImage(gameover.getImg(), gameover.getxCoord(), gameover.getyCoord(), gameover.getHeight(), gameover.getWidth(), this);
			g.drawString(greeen, 400, 400);
			
			if(bg.getxCoord() > 0) {
			lose = true;
			}
		}
	
		repaint();
		}

	}

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == 32) {
			int key = e.getKeyCode();
			projectile knife = new projectile(link.getxCoord(),link.getyCoord(),40,40,"files/banana.png");
			knives.add(knife);
			
		}
		
		link.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		repaint();
		
	}
	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			homescreen = true;
		}
		
		
	} 
	
}