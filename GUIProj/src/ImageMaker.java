import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageMaker extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	
	private ImageComponent compDisplay;	// the component that will be displayed and moved
	private JPanel divider;				// the panel that divides the score label from the paint area
	private JLabel score;				// the label of the current score
	private int clicks = 0;				// the number of clicks the user makes
	private int speed = 35;			// the speed of the component
	
	private Rectangle panelsize;
	
	private int moveByX = 1;
	private int moveByY = 1;
	
	ActionListener listener;
	MouseListener clicking;
	Timer t;
	
	class TimerListener implements ActionListener
	{
		
		int locationXR = 0;
		int locationXL = 0;
		int locationYT = 0;
		int locationYD = 0;
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			locationXR = compDisplay.getXlocationRight();
			locationXL = compDisplay.getXlocationLeft();
			locationYT = compDisplay.getYlocationTop();
			locationYD = compDisplay.getYlocationDown();
			
			// get panel size
			panelsize = new Rectangle(divider.getBounds());
			
			// debugging purpose only
			//System.out.println(panelsize.toString());
			
			if(locationXR >= panelsize.getWidth())
			{
				moveByX = -1;
			}
			else if(locationXL <= 0)
			{
				moveByX = 1;
			}
			
			if(locationYT <= 0)
			{
				moveByY = 1;
			}
			else if(locationYD == panelsize.getHeight())
			{
				moveByY = -1;
			}
			
			compDisplay.moveImageBy(moveByX, moveByY);
		}
	}
	
	// a click listener should be here
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
					setSpeed(25);
					createTimer();
					t.restart();
					System.out.println("the speed is now" + speed);
				}
				else if(clicks == 10)
				{
					setSpeed(15);
					createTimer();
					t.restart();
					System.out.println("the speed is now" + speed);
				}
			}
			
			
			
			
		}
		/**
		 * 	Do nothing methods
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseClicked(MouseEvent e) 
		{
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
		
		// create a label for the score
		createLabel();
		
		// instantiate the divider for the Frame
		divider = new JPanel(new BorderLayout());
		
		// Timer listener instantiation.
		listener = new TimerListener();
		
		// mouselistener instantiation
		clicking = new Clicker();
		
		// adding a mouse listener to the printing area
		compDisplay.addMouseListener(clicking);
		
		// create a timer 
		createTimer();
		
		// adding the area for the image to be drawn into the divider
		divider.add(compDisplay, BorderLayout.CENTER);
		
		// adding the score label into the divider
		divider.add(score, BorderLayout.NORTH);
		
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
		score = new JLabel("Score: ");
	}
	
	// creates the timer
	public void createTimer()
	{
		// instantiating the timer
		t = new Timer(speed, listener);
	}
	
	
}
