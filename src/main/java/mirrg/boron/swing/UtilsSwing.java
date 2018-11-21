package mirrg.boron.swing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UtilsSwing
{

	public static void setSystemLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

}
