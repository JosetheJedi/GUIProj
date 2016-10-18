import javax.swing.JFrame;

public class ImagePlayer 
{

	public static void main(String[] args) 
	{
		JFrame frame = new ImageMaker();
		
		frame.setResizable(true);
		frame.setTitle("Image Component Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}