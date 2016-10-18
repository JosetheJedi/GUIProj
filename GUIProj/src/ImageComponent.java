import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImageComponent extends JComponent
{
	private static final int BOX_X = 100;
	private static final int BOX_Y = 100;
	private static final int BOX_WIDTH = 50;
	private static final int BOX_HEIGHT = 50;
	
	private int xlocationRight = BOX_X + BOX_WIDTH;
	private int xlocationLeft = BOX_X;
	private int ylocationTop = BOX_Y;
	private int ylocationDown = BOX_Y + BOX_HEIGHT;
	
	private BufferedImage image;
	String fileLocation = "resources/duke_standing.gif";
	
	
	// instantiating the component through the constructor
	public ImageComponent()
	{
		try
		{
			image = ImageIO.read(new File(fileLocation));
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	}
	
	// must paint the component
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
        g.drawImage(image, xlocationLeft, ylocationTop, BOX_WIDTH, BOX_HEIGHT, null);
	}
	
	/**
	 * 
	 * @param dx the amount to move in direction x
	 * @param dy the amount to move in direction y
	 */
	public void moveImageBy(int dx, int dy)
	{
		setXlocationRight(dx);
		setXlocationLeft(dx);
		setYlocationTop(dy);
		setYlocationDown(dy);
		repaint();
	}

	public int getXlocationRight() 
	{
		return xlocationRight;
	}

	private void setXlocationRight(int xlocationRight) 
	{
		this.xlocationRight += xlocationRight;
	}

	public int getXlocationLeft() {
		return xlocationLeft;
	}

	private void setXlocationLeft(int xlocationLeft) {
		this.xlocationLeft += xlocationLeft;
	}

	public int getYlocationTop() {
		return ylocationTop;
	}

	private void setYlocationTop(int ylocationTop) {
		this.ylocationTop += ylocationTop;
	}

	public int getYlocationDown() {
		return ylocationDown;
	}

	private void setYlocationDown(int ylocationDown) {
		this.ylocationDown += ylocationDown;
	}
	
	
	public void changeImage(String fileLocation)
	{
		this.fileLocation = fileLocation;
		
		try
		{
			image = ImageIO.read(new File(fileLocation));
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
		repaint();
	}
	
	
	
}
