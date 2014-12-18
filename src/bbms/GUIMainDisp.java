package bbms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIMainDisp extends JPanel {
	
	private int squareX = 50;
	private int squareY = 50;
	private int squareW = 20;
	private int squareH = 20;
	
	private Polygon poly;
	private Polygon[] polyMap = {};
	
	public GUIMainDisp() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// moveSquare(e.getX(), e.getY());
				// drawHex(e.getX(), e.getY(), 20);
				drawHexMap(e.getX(), e.getY(), 3, 3, 40);
				GUI_NB.GCO("Repainting.");
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				// moveSquare(e.getX(), e.getY());
			}
		});
		
		
	}
	
	public static void MouseMotionEvents(java.awt.event.MouseEvent evt)
	{
		
	}
	
	public void MouseClickedEvents(java.awt.event.MouseEvent evt)
	{
		
	}
        
        public static void KeyReleasedEvents (java.awt.event.KeyEvent evt)
        {
            
        }
	
	public void drawHex(int x, int y, int size) {
		int[] xPoly = new int[6];
		int[] yPoly = new int[6];
		
		for (int i = 0; i < 6; i++) {
			double angle = 2 * Math.PI / 6 * (i + 0.5);			
			xPoly[i] = (int) (x + size * Math.cos(angle));
			yPoly[i] = (int) (y + size * Math.sin(angle));;
		}
		
		for (int i = 0; i < 5; i++) {
			
			GUI_NB.GCO("Coordinate " + i + " is: (" + xPoly[i] + ", " + yPoly[i] + ")");
		}
		
		poly = new Polygon(xPoly, yPoly, xPoly.length);
		
				
	}
	
	public Polygon genHex(int x, int y, int size) {
		int[] xPoly = new int[6];
		int[] yPoly = new int[6];		
		
		for (int i = 0; i < 6; i++) {
			double angle = 2 * Math.PI / 6 * (i + 0.5);			
			xPoly[i] = (int) (x + size * Math.cos(angle));
			yPoly[i] = (int) (y + size * Math.sin(angle));;
		}
			
		return new Polygon(xPoly, yPoly, xPoly.length);								
	}
	
	public void drawHexMap(int xi, int yi, int sizeX, int sizeY, int hexSize) {
		
		polyMap = new Polygon[sizeX * sizeY];
		int hWidth = (int) (Math.sqrt(3) * hexSize);
		int hHeight = (int) (1.5 * hexSize);
		int index = 0;
		
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				index = x + (y * sizeX);
				
				// Even-numbered row - hexes are aligned all the way to the right
				if (y % 2 == 0) {
					polyMap[index] = genHex(xi + (hWidth * x), yi + (hHeight * y), hexSize);
				}
				// Odd-numbered row - hexes are offset to the right
				else {
					polyMap[index] = genHex(xi + (int)(hWidth * (x + 0.5)), yi + (hHeight * y), hexSize); 
				}
			}
		}				
	}
	
	public void drawHexMapComposite(int sizeX, int sizeY, int hexSize, Graphics g) {		
		int hexX = 0;
		int hexY = 0;
		int hWidth = (int) (Math.sqrt(3) * hexSize);
		int hHeight = (int) (1.5 * hexSize);
		int xi = hexSize;
		int yi = hexSize;
		
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				
				hexY = yi + (hHeight * y);
				// Even-numbered row - hexes are aligned all the way to the right
				if (y % 2 == 0) {
					hexX = xi + (hWidth * x);
					// java.net.URL imageURL = GUIMainDisp.class.getResource("hex/images/Grassland1-Z4.png");
					
					try {
						File input = new File("src/hex/graphics/Grassland1-Z4.png");
						Image image = ImageIO.read(input);
						g.drawImage(image,  hexX - (hWidth / 2) - 1,  hexY - (hHeight / 2) - 8, null);
						GUI_NB.GCO("hexY: " + hexY + "  || hHeight: " + hHeight + "  || actualen: " + (hexY - (hHeight / 2) - 8));
					} catch (IOException ie) {
						System.out.println(ie.getMessage());
					}
					
					/*
					if (imageURL != null) {
						ImageIcon icon = new ImageIcon(imageURL);												
					}*/
					
					g.drawPolygon(genHex(hexX, hexY, hexSize));					
				}
				// Odd-numbered row - hexes are offset to the right
				else {
					hexX = xi + (int)(hWidth * (x + 0.5));		
					g.drawPolygon(genHex(hexX, hexY, hexSize));					
				}
				
				g.drawString(x + ", " + y, hexX - (int)(hexSize / 3), hexY);
			}
		}				
	}
	
	public void moveSquare(int x, int y) {
		int OFFSET = 1;
		if ((squareX != x) || (squareY != y)) {
			repaint(squareX, squareY, squareW+OFFSET, squareH+OFFSET);
			squareX = x;
			squareY = y;
			repaint(squareX, squareY, squareW+OFFSET, squareH+OFFSET);
		}
	}
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		
		// Draw text		
		//g.drawString("Custom panel.",  10, 20);		
		
		/*g.setColor(Color.RED);
		g.fillRect(squareX, squareY, squareW, squareH);
		g.setColor(Color.BLACK);
		g.drawRect(squareX, squareY, squareW, squareH); */
		
		drawHexMapComposite(15, 15, 30, g);
	}

}
