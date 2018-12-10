package Firsttry;

import java.awt.Image;
import java.awt.Toolkit;

public class Badguy {
	private double xCoord;
	private double yCoord;
	private double width;
	private double height;
	private Image img;

	public Badguy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(30);
		setHeight(30);
		setImg("../files/Badguy.png");
	}
	
	public Badguy(double x, double y, double w, double h, String imgpath) {
		setxCoord(x+50);
		setyCoord(y+50);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}
		public double getxCoord() {
		return xCoord;
	}
	public void setxCoord(double d) {
		this.xCoord = d;
	}
	public double getyCoord() {
		return yCoord;
	}
	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
		
}
