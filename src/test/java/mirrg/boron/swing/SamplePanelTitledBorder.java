package mirrg.boron.swing;

import static mirrg.boron.swing.UtilsComponent.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.WindowConstants;

public class SamplePanelTitledBorder
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		frame.getContentPane().setBackground(Color.red);

		frame.setLayout(new CardLayout());
		frame.add(createPanelTitledBorder("Title", createScrollPane(new JList<String>(IntStream.range(0, 20)
			.mapToObj(i -> "" + i)
			.collect(Collectors.toCollection(Vector::new))), 200, 200)));

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

}
