import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ImageMaker extends JFrame
{
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 450;
	
	private ImageComponent compDisplay;	// the component that will be displayed and moved
	private JPanel divider;				// the panel that divides the score label from the paint area
	private JPanel topSection;
	private JPanel buttonPanel;
	private JLabel score;				// the label of the current score
	private ButtonGroup buttons;
	private JRadioButton first;
	private JRadioButton second;
	private JRadioButton third;
	private int clicks = 0;				// the number of clicks the user makes
	private int speed = 35;			// the speed of the component

	String file1 = "resources/duke_standing.gif";
	String file2 = "resources/duke_waving.gif";
	String file3 = "resources/smiley.gif";
	
	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3; 
	
	
	
	private Rectangle panelsize;
	
	private int moveByX = 1;
	private int moveByY = 1;
	
	ActionListener listener;
	MouseListener clicking;
	ActionListener rListener;
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
			panelsize = new Rectangle(compDisplay.getBounds());
			
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
					
//					System.out.println("the speed is now " + speed);
				}
				else if(clicks == 10)
				{
					setSpeed(15);
					createTimer();
					t.restart();
//					System.out.println("the speed is now " + speed);
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
	
	
	class ChoiceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(first.isSelected())
			{
				compDisplay.changeImage(file1);
			}
			else if(second.isSelected())
			{
				compDisplay.changeImage(file2);
			}
			else if(third.isSelected())
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
		compDisplay.setBorder(new TitledBorder(new EtchedBorder(), "Play Area"));
		
		rListener = new ChoiceListener();
		
		// create a label for the score
		createLabel();
		
		// instantiate the divider for the Frame
		// it will divide the moving window from other parts of 
		// the program 
		divider = new JPanel(new BorderLayout());

		// what will go on the top part of the divider.
		topSection = new JPanel();
		
		createImages();
		
		createButtons();
		
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
		score = new JLabel("Score: ");
	}
	
	// creates the timer
	public void createTimer()
	{
		// instantiating the timer
		t = new Timer(speed, listener);
	}
	
	public void createButtons()
	{
		buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Images"));
		buttons = new ButtonGroup();
		
		first = new JRadioButton("Standing", icon1, true);
		second = new JRadioButton("Waving",icon2, false);
		third = new JRadioButton("Smiley",icon3, false);
		
		first.addActionListener(rListener);
		second.addActionListener(rListener);
		third.addActionListener(rListener);
		
		buttons.add(first);
		buttons.add(second);
		buttons.add(third);
		
		buttonPanel.add(first);
		buttonPanel.add(second);
		buttonPanel.add(third);
		
		topSection.add(buttonPanel);
		
		
	}
	
	public void createImages()
	{
		icon1 = new ImageIcon(file1);
		icon2 = new ImageIcon(file2);
		icon3 = new ImageIcon(file3);
		
	}
	
	
}
