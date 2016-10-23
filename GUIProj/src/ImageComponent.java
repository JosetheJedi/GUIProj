import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImageComponent extends JComponent
{
	private static final int IMAGE_WIDTH = 75;	// the final width for the image
	private static final int IMAGE_HEIGHT = 75;	// the final height for the image
	
	// these variables are used to make sure that each side of the image are
	// within the JPanel bounds
	private int xlocationLeft = 100;						// xlocationLeft is the images location from the left side
	private int xlocationRight = xlocationLeft + IMAGE_WIDTH; // this will keep track of the image from it's right side
	private int ylocationTop = 100;							// the images location from the top side
	private int ylocationDown = ylocationTop + IMAGE_HEIGHT;	// the images location from it's bottom side
	
	private BufferedImage image;		// image object 
	String fileLocation = "resources/duke_standing.gif";	// default file to use when the app is started
	
	// instantiating the component through the constructor
	public ImageComponent()
	{
		// will attempt to load the image file.
		// and if it does not exist, then the program will 
		// notify the user that the file is not found.
		try
		{
			image = ImageIO.read(new File(fileLocation));
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
	}
	
	// This method will paint the image component 
	// onto the screen.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// the image will be drawn at the certain location given, with the certain width and height given.
        g.drawImage(image, xlocationLeft, ylocationTop, IMAGE_WIDTH, IMAGE_HEIGHT, null);
	}
	
	/**
	 * This method is to move the image to the x and y 
	 * positions that are passed to it.
	 * @param dx: the location x the image should move to
	 * @param dy: the location y the image should move to
	 */
	public void moveImageTo(int dx, int dy)
	{
		// will call set x location left passing the value to 
		// be updated to.
		setXlocationLeft(dx);
		
		// will call the xlocationRight method updating the value
		// for xlocationRight
		setXlocationRight();
		
		// will call set y location left passing the value to 
		// be updated to.
		setYlocationTop(dy);
		
		// will call the ylocationDown method updating the value
		// for ylocationDown
		setYlocationDown();
		
		// will call the repaint component
		// that will rerun the paintComponent with the updated
		// location of the image
		repaint();
	}

	// XlocationRight getter
	public int getXlocationRight() 
	{
		return xlocationRight;
	}

	// xLocationLeft getter
	public int getXlocationLeft() {
		return xlocationLeft;
	}

	// yLocationTop getter
	public int getYlocationTop() {
		return ylocationTop;
	}

	// ylocationdown getter
	public int getYlocationDown() {
		return ylocationDown;
	}
	
	// xLocationRight setter, the xlocationright is updated
	// by adding the left location of the image plus the 
	// width of the image
	private void setXlocationRight(){
		xlocationRight = xlocationLeft + IMAGE_WIDTH;
	}

	// xlocationleft setter, will set the location
	// to the new location passed to it.
	private void setXlocationLeft(int xlocationLeft) {
		this.xlocationLeft = xlocationLeft;
	}

	// yLocationTeop setter, will set the location
	// to the new location passed to it.
	private void setYlocationTop(int ylocationTop) {
		this.ylocationTop = ylocationTop;
	}
	
	// YlocationDown setter, the ylocationDown is updated by adding
	// the top y location of the image with the height of the image.
	private void setYlocationDown()
	{
		this.ylocationDown = ylocationTop + IMAGE_HEIGHT;
	}

	/**
	 * This method will change the fileLocation to a different location for
	 * another image. then it will call the repaint() method to redraw the image.
	 * @param fileLocation: a string of the file location for the image
	 * selected by the user.
	 */
	public void changeImage(String fileLocation)
	{
		this.fileLocation = fileLocation;
		
		// will attempt to load the image file.
		// and if it does not exist, then the program will 
		// notify the user that the file is not found.
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

	// getter for the image width
	public static int getImageWidth() {
		return IMAGE_WIDTH;
	}

	// getter for the image height
	public static int getImageHeight() {
		return IMAGE_HEIGHT;
	}	
}