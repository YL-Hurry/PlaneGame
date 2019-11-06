package PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject {

	boolean left, right, up, down;
	boolean live = true;

//	press down the keyboard
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}

	}

//	release the keyboard
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}

	@Override
	public void drawMySelf(Graphics g) {
		if (live) {
			super.drawMySelf(g);
			if (left)
				this.x -= speed;
			if (right)
				this.x += speed;
			if (up)
				this.y -= speed;
			if (down)
				this.y += speed;
		}
	}

	public Plane(Image img, double x, double y, int speed) {
		super(img, x, y);
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
		this.speed = speed;
	}

}
