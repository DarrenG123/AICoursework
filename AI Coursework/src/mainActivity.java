import java.awt.Component;
import java.awt.FlowLayout;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class mainActivity implements Runnable
{
	private Thread t;
	private String threadName, chosenWidth, chosenHeight, chosenMapScale;
	private static mapIcon mapArray[][];
	private long columns, rows;
	private static long sizeMultiplier;
	private JFrame jFrame;
	private JPanel jPanel;
	private static int reply;
	private int passFactor;
	
	mainActivity (String name, long columns, long rows, int sizeMultiplier, int passFactor)
	{
		threadName = name;
		this.passFactor = passFactor;
		setupGUI();
		reply = JOptionPane.showConfirmDialog(null, "Use default settings?", "", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION)
		{
			this.columns = columns;
			this.rows = rows;
			mainActivity.sizeMultiplier = sizeMultiplier;
		}
		else
		{
			while (checkInputs(chosenWidth, chosenHeight, chosenMapScale) != "ALL FINE")
			{
				chosenWidth = JOptionPane.showInputDialog("Please enter a width...");
				chosenHeight = JOptionPane.showInputDialog("Please enter a height...");
				chosenMapScale = JOptionPane.showInputDialog("Please enter a scale size as a whole number between 1 and 5");
			}
			this.columns = Integer.valueOf(chosenWidth);
			this.rows = Integer.valueOf(chosenHeight);
			mainActivity.sizeMultiplier = Integer.valueOf(chosenMapScale);
		}
	}
		
	private String checkInputs(String chosenWidth, String chosenHeight, String chosenMapScale) 
	{
		try
		{
			if (chosenWidth.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter a width");
			}
			if (chosenHeight.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter a height");
			}
			if (chosenMapScale.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter a scale factor");
			}
		}
		catch (NullPointerException e)
		{
			e.getMessage();
		}
		return null;
	}

	private void setupGUI() 
	{
		jFrame = new JFrame("Artificial Intelligence Project");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(1280, 1024);
		jFrame.setVisible(true);
		jPanel = new JPanel(new FlowLayout());
		jPanel.setSize(1280, 1024);
		jPanel.setVisible(true);
		jFrame.add(jPanel);
	}

	@Override
	public void run() 
	{
		mapArray = new mapIcon[(int) columns][(int) rows];
		shuffleArrays(mapArray, passFactor);
		intitialiseMapArray(mapArray);	
		printMap(mapArray);
	}

	private void intitialiseMapArray(mapIcon[][] mapArray) 
	{
		for (int x = 0; x < mapArray.length; x++)
		{
			for (int y = 0; y < mapArray[x].length; y++)
			{
				mapArray[x][y] = new mapIcon((mapArray[x][y].isPassable()), x, y, ((int) sizeMultiplier));
				try
				{
					Component comp = mapArray[x][y].loadImage();
					jPanel.add(comp);
				}
				catch (NullPointerException e)
				{
					e.printStackTrace();
				}
			}
		}
		jFrame.add(jPanel);
		jFrame.repaint();
	}

	public static void shuffleArrays(mapIcon mapArray[][], int passFactor)
	{
		boolean holder;
		for (int x = 0; x < mapArray.length; x++)
		{
			for (int y = 0; y < mapArray[x].length; y++)
			{
				int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
				if (randomNum <= passFactor)
				{
					holder = false;
				}
				else
				{
					holder = true;
				}
				mapArray[x][y] = new mapIcon(holder, x, y, ((int) sizeMultiplier));
			}
		}
	}
	
	public static void printMap(mapIcon[][] mapArray)
	{
		for (int x = 0; x < mapArray.length; x++)
		{
			for (int y = 0; y < mapArray[x].length; y++)
			{
				if (mapArray[x][y].isPassable() == true)
				{
					System.out.print("O");
				}
				else
				{
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}

	public void start() 
	{
		if (t == null) 
		{
			t = new Thread (this, threadName);
	        t.start ();
		}
	}
}
