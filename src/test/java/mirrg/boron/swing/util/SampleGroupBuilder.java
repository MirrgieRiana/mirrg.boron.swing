package mirrg.boron.swing.util;

import static mirrg.boron.swing.util.GroupBuilder.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SampleGroupBuilder
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		{
			GroupLayout layout = new GroupLayout(frame.getContentPane());

			layout.setAutoCreateGaps(true);

			group(

				group(

					new JLabel("<html>Label<br>Label"),
					new JTextField("Text")

				),
				group(

					new JLabel("<html>Label<br>Label"),
					new JTextField("Text")

				).setAlignment(Alignment.BASELINE),
				group(

					new JLabel("<html>Label<br>Label"),
					new JTextField("Text")

				).setAlignment(Alignment.CENTER),
				group(

					new JLabel("<html>Label<br>Label"),
					new JTextField("Text")

				).setAlignment(Alignment.LEADING),
				group(

					new JLabel("<html>Label<br>Label"),
					new JTextField("Text")

				).setAlignment(Alignment.TRAILING),

				new JScrollPane(new JButton("Button"))

			)
				.apply(layout);

			frame.setLayout(layout);
		}

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

}
