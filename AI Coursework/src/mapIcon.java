import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class mapIcon 
{
	boolean isPassable;
	BufferedImage mapImage;
	Component c;
	int sizeMultiplier, x, y, imageWidth, imageHeight;
	Color color;
	
	public mapIcon(boolean isPassable, int x, int y, int sizeMultiplier)
	{
		this.isPassable = isPassable;
		this.sizeMultiplier = sizeMultiplier;
		this.x = x;
		this.y = y;
		imageWidth = (10 * sizeMultiplier);
		imageHeight = (10 * sizeMultiplier);
	}
	
	public Component loadImage()
	{
		if (isPassable == true)
		{
			color = new Color(64, 255, 61);
		}
		else
		{
			color = new Color(255, 38, 56);
		}
		mapImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < mapImage.getWidth(); x++)
		{
			for (int y = 0; y < mapImage.getHeight(); y++)
			{
				mapImage.setRGB(x, y, color.getRGB());
			}
		}
	
		c = new JLabel(new ImageIcon(mapImage));
		c.setBounds((((int) sizeMultiplier) * (x * 11)), (((int) sizeMultiplier) *(y * 11)), imageWidth, imageHeight);
		return c;
	}
	
	public boolean isPassable()
	{
		return isPassable;
	}
}
