
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class BlakeMethods extends JPanel
{
private final ArrayList<Point> point = new ArrayList<>();

public BlakeMethods() {

	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent event) {
			point.add(event.getPoint());
			repaint();
			}
	});

		    addMouseMotionListener(new MouseMotionAdapter() {
		        public void mouseDragged(MouseEvent event) {
		            point.add(event.getPoint());
		            repaint();
		        }
		    });
		}


		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.setColor(new Color(0, 0, 0));
		    for (Point p : point)
		        g.fillOval(p.x, p.y, 40, 40);
		}

		public static void runner() throws IOException {
			JFrame f = new JFrame();
		    f.add(new BlakeMethods());
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    f.setSize(800, 800);
		    f.setVisible(true);
		    
		}
}
