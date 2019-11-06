package PlaneGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;


public class MyGameFrame extends Frame {
	Image bjImg = GameUtil.getImage("image/bj.png");
	Image planeImg = GameUtil.getImage("image/plane.png");
	Plane plane = new Plane(planeImg, 300, 300, 3);
	Explode boom;
	Date startTime = new Date();
	Date endTime;
	int period;
	
	ArrayList<Shell> shellList = new ArrayList<Shell>();

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();

		g.drawImage(bjImg, 0, 0, null);
		plane.drawMySelf(g);
		
		for (int i = 0; i < shellList.size(); i++) {
			Shell shell = shellList.get(i);
			shell.draw(g);
			if (plane.live) {
				boolean peng = shell.getRect().intersects(plane.getRect());
				if (peng) {
					plane.live = false;

					if (boom == null) {
						boom = new Explode(plane.x, plane.y);
					}
					boom.draw(g);
				}
			}
		}
		if(!plane.live){
            if(endTime==null){
                endTime = new Date();
            }
            int period = (int)((endTime.getTime()-startTime.getTime())/1000);
            printInfo(g, "Time："+period+"s", 50, 120, 260, Color.white);
        }
		g.setColor(c);
		g.setFont(f);
	}
	 public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
	        Color c = g.getColor();
	        g.setColor(color);
	        Font f = new Font("TimesNewRoman",Font.BOLD,size);
	        g.setFont(f);
	        g.drawString(str,x,y);
	        g.setColor(c);
	    }  
//    create a thread, it is an inner class
	class PaintThread extends Thread {
		public void run() {
			while (true) {
				repaint();

				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

//	add keyMoniter, it is an inner class
	class KeyMoniter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}

	}

//	create a frame
	public void launchFrame() {
		this.setTitle("PlaneGame");
		this.setVisible(true);
		this.setSize(450, 450);
		this.setLocation(300, 300);

		new PaintThread().start();
		addKeyListener(new KeyMoniter());

		for (int i = 0; i < 50; i++) {
			Shell shell = new Shell();
			shellList.add(shell);
		}
		
//		set windowListener when close the window, the progam also close
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GameWidth,Constant.GameHeight);
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
