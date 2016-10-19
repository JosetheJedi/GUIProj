import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ImageMaker extends JFrame
{
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 1000;
	
	private ImageComponent compDisplay;	// the component that will be displayed and moved
	private JPanel divider;				// the panel that divides the score label from the paint area
	private JPanel topSection;			// Panel that will hold everything for the north border
	private JLabel score;				// the label of the current score
	private JMenuBar menuBar;			// menu bar to hold image menu
	private JMenu imagesMenu;			// image menu that will hold possible images to use
	private JMenuItem image1;			// first image item the player can choose from
	private JMenuItem image2;			// second image item the player can choose from
	private JMenuItem image3;			// third image item the player can choose from
	private int clicks = 0;				// the number of clicks the user makes
	private int speed = 1500;			// the speed of the component
	private Random randx;				// to generate a random x location
	private Random randy;				// to generate a random y location

	String file1 = "resources/duke_standing.gif";	// string location of the first image
	String file2 = "resources/duke_waving.gif";		// string location of the second image
	String file3 = "resources/smiley.gif";			// string location of the third image
	
	ImageIcon icon1;	// will hold the 1st icon of the radio button
	ImageIcon icon2;	// will hold the 2nd icon of the radio button
	ImageIcon icon3; 	// will hold the 3rd icon of the radio button
	
	ActionListener listener;	// Listener for the time change
	MouseListener clicking;		// Listener for mouse events
	ActionListener rListener;	// Listener for radio button events
	Timer t;					// object for the timer to keep the pace of the moving image
	
	class TimerListener implements ActionListener
	{
		// instantiating xbound and ybound
		int xBound = 0;
		int yBound = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// the xBound is the highest position x can be
			// this bound will be used for the random x generator
			// so that it won't select a random value bigger than
			// the panel width
			xBound = compDisplay.getWidth() - ImageComponent.getBoxWidth();
			// the yBound is the highest position y can be
			// this bound will be used for the random y generator
			// so that it won't select a random value bigger than
			// the panel height
			yBound = compDisplay.getHeight() - ImageComponent.getBoxHeight();
			
			
			//debugging
//			System.out.println("coortop and left " + compDisplay.getXlocationLeft() + " " + compDisplay.getYlocationTop());
//			System.out.println("coorright and bottom" + compDisplay.getXlocationRight() + " " + compDisplay.getYlocationDown());
//			System.out.println(speed);
			
			// this will call the move image method to set the x and y position 
			// to the one generated randomly
			compDisplay.moveImageTo(randx.nextInt(xBound),randy.nextInt(yBound));
			
		}
	}
	
	// 
	class Clicker implements MouseListener
	{
		@Override
		public void mousePressed(MouseEvent e) 
		{
			int x = e.getX();
			int y = e.getY();
			
			if(x <= compDisplay.getXlocationRight() && x >= compDisplay.getXlocationLeft()
					&& y >= compDisplay.getYlocationTop() && y <= compDisplay.getYlocationDown())
			{
				click();
				score.setText("Score: " + clicks);
				
				if(clicks == 5)
				{
					setSpeed(1000);
					createTimer();
					t.restart();
					
				}
				else if(clicks == 10)
				{
					setSpeed(800);
					createTimer();
					t.restart();
				}
			}
			
		}
		/**
		 * 	Do nothing methods
		 */
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	
	class MenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equalsIgnoreCase("standing"))
			{
				compDisplay.changeImage(file1);
			}
			else if(e.getActionCommand().equalsIgnoreCase("waving"))
			{
				compDisplay.changeImage(file2);
			}
			else if(e.getActionCommand().equalsIgnoreCase("smiley"))
			{
				compDisplay.changeImage(file3);
			}			
		}
	}
	
	
	// increment clicks
	public void click()
	{
		this.clicks += 1;
	}
	
	// set the new speed of the repaint
	public void setSpeed(int s)
	{
		this.speed = s;
	}
	
	// constructor
	public ImageMaker()
	{
		// instantiate a new component to display
		compDisplay = new ImageComponent();
		
		// Instantiate the random x and y generators
		randx = new Random();
		randy = new Random();
		
		// creating a border for the area the image will be moving around
		compDisplay.setBorder(new TitledBorder(new EtchedBorder(), "Play Area"));
		
		// instantiate the divider for the Frame
		// it will divide the moving window from other parts of 
		// the program 
		divider = new JPanel(new BorderLayout());
		
		// instantiating the listener for the menu items
		rListener = new MenuListener();
		
		// create a label for the score
		createLabel();

		// what will go on the top part of the divider.
		topSection = new JPanel();
		
		// calls the method to create the image icons for 
		// the radio buttons
		createImages();
		
		createMenu();
		
		// Timer listener instantiation.
		listener = new TimerListener();
		
		// mouse listener instantiation
		clicking = new Clicker();
		
		// adding a mouse listener to the printing area
		compDisplay.addMouseListener(clicking);
		
		// create a timer 
		createTimer();
		
		// adding the area for the image to be drawn into the divider
		divider.add(compDisplay, BorderLayout.CENTER);
		
		// adding the score label into the divider
		topSection.add(score);
		
		divider.add(topSection, BorderLayout.NORTH);
		
		// setting the size of the frame
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// adding the divider into the JFrame
		add(divider);
		
		
		// start the timer
		t.start();
		
	}
	
	// creates any labels
	public void createLabel()
	{
		// instantiates the label for the 
		// score.
		score = new JLabel("Score " + 0);
	}
	
	// creates the timer
	public void createTimer()
	{
		// instantiating the timer
		t = new Timer(speed, listener);
	}
	
	/**
	 * will instantiate the icon images
	 * using the string file location variables
	 */
	public void createImages()
	{
		icon1 = new ImageIcon(file1);
		icon2 = new ImageIcon(file2);
		icon3 = new ImageIcon(file3);
		
	}
	
	
	public void createMenu()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		imagesMenu = new JMenu("Images");
		menuBar.add(imagesMenu);
		
		image1 = new JMenuItem("Standing", icon1);
		image2 = new JMenuItem("Waving", icon2);
		image3 = new JMenuItem("Smiley", icon3);
		
		imagesMenu.add(image1);
		imagesMenu.add(image2);
		imagesMenu.add(image3);
		
		// adding an action listener to all the radio buttons
		// so that when a button is selected, the program renders the
		// image that the user selected.
		image1.addActionListener(rListener);
		image2.addActionListener(rListener);
		image3.addActionListener(rListener);
	}
	
	
}
