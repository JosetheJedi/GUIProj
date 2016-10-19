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
	private static final int BOX_WIDTH = 75;
	private static final int BOX_HEIGHT = 75;
	
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
	public void moveImageTo(int dx, int dy)
	{
		setXlocationLeft(dx);
		setXlocationRight();
		setYlocationTop(dy);
		setYlocationDown();
		repaint();
	}

	public int getXlocationRight() 
	{
		return xlocationRight;
	}

	public int getXlocationLeft() {
		return xlocationLeft;
	}
	
	private void setXlocationRight(){
		xlocationRight = xlocationLeft + BOX_WIDTH;
	}

	private void setXlocationLeft(int xlocationLeft) {
		this.xlocationLeft = xlocationLeft;
	}

	public int getYlocationTop() {
		return ylocationTop;
	}

	private void setYlocationTop(int ylocationTop) {
		this.ylocationTop = ylocationTop;
	}
	
	private void setYlocationDown()
	{
		this.ylocationDown = ylocationTop + BOX_HEIGHT;
	}

	public int getYlocationDown() {
		return ylocationDown;
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

	public static int getBoxWidth() {
		return BOX_WIDTH;
	}

	public static int getBoxHeight() {
		return BOX_HEIGHT;
	}
	
	
	
	
	
}
