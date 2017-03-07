import javax.swing.JFrame;

public class testActivity extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
		mainActivity Thread1 = new mainActivity("Thread1", 100, 100, 2, 2);
		Thread1.start();
		
	}
}
