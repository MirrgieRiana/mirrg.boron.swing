package mirrg.boron.swing.util;

import static mirrg.boron.swing.UtilsComponent.*;

import java.awt.CardLayout;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SampleNamedSlot
{

	private static JTextField textField;
	private static JList<NamedSlot<SampleObject>> list;

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		frame.setLayout(new CardLayout());
		{
			SampleObject[] objects = IntStream.range(0, 20)
				.mapToObj(i -> new SampleObject(i, "[" + i + "]"))
				.toArray(SampleObject[]::new);

			frame.add(createPanelBorderDown(

				// 左
				createScrollPane(addListSelectionListener(list = new JList<NamedSlot<SampleObject>>(Stream.of(objects)
					.map(o -> new NamedSlot<>(o, o2 -> o2.name))
					.collect(Collectors.toCollection(Vector::new))), e -> {
						textField.setText("" + list.getSelectedValue().get().value);
					}), 100, 200),

				// テキストボックス
				textField = new JTextField()

			));
		}

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

	public static class SampleObject
	{

		public int value;
		public String name;

		public SampleObject(int value, String name)
		{
			this.value = value;
			this.name = name;
		}

	}

}
