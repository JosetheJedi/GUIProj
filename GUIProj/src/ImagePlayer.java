/* Jose Hernandez Uribe
     October 20, 2016
     Purpose: This program draws an image on the screen and the
     			user is suppose to try to click on the image as the 
     			image moves around to a new position.
     			If the user clicks on the image the score of the user goes up
     			by one. When the score reaches 5 the movement of the image increases.
     			When the score reaches 10 the movement of the image increases even more!
     			There are also 6 images for the user to choose from.
     Inputs: no inputs required from the user.
     Output: the only output possible is the image being drawn.
*/
import javax.swing.JFrame;

public class ImagePlayer 
{
	public static void main(String[] args) 
	{
		// a frame object of ImageMaker type that inherits from JFrame.
		JFrame frame = new ImageMaker();
		
		// setting the resizability of the program to true
		frame.setResizable(true);
		
		// Setting the title of the game.
		frame.setTitle("Catch me if you can!");
		
		// setting the operation so that the program
		// will stop running when the application is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// setting the visibility of the frame object to true.
		frame.setVisible(true);
	}
}