package mirrg.boron.swing.util;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;

/**
 * <p>
 * {@link GroupLayout} に対してグリッド形式でない2次元的な配置法則を適用します。
 * </p>
 * <p>
 * このクラスは次のようにして使います。
 * </p>
 *
 * <pre>
 * setLayout(GroupBuilder.group(
 *
 * 	GroupBuilder.group(
 *
 * 		new JLabel("Label"),
 * 		new JTextField("Text")
 *
 * 	),
 *
 * 	new JScrollPane(new JButton("Button"))
 *
 * )
 * 	.apply(new GroupLayout(getContentPane())));
 * </pre>
 * <p>
 * すなわち、最も外側のグループは縦に並ぶグループを表し、
 * その内側のグループは横に並ぶグループを表し、
 * さらにその内側のグループは縦に並ぶグループを表します。
 * </p>
 */
public class GroupBuilder
{

	/**
	 * @param objects
	 *            {@link GroupBuilder} または {@link Component} のオブジェクトで構成される配列
	 */
	public static GroupBuilder group(Object... objects)
	{
		return new GroupBuilder(objects);
	}

	private Object[] objects;

	private GroupBuilder(Object[] objects)
	{
		this.objects = objects;
	}

	//

	public GroupLayout apply(GroupLayout layout)
	{
		layout.setHorizontalGroup(build(layout, false));
		layout.setVerticalGroup(build(layout));
		return layout;
	}

	private Group build(GroupLayout groupLayout)
	{
		return build(groupLayout, true);
	}

	private Group build(GroupLayout groupLayout, boolean isSequential)
	{
		Group group = isSequential
			? groupLayout.createSequentialGroup()
			: alignment != null
				? groupLayout.createParallelGroup(alignment)
				: groupLayout.createParallelGroup();

		for (Object object : objects) {
			if (object instanceof GroupBuilder) {
				group.addGroup(((GroupBuilder) object).build(groupLayout, !isSequential));
			} else if (object instanceof Component) {
				group.addComponent((Component) object);
			} else {
				throw new RuntimeException("unknown GroupBuilder entry: " + object);
			}
		}

		return group;
	}

	//

	private Alignment alignment = null;

	public GroupBuilder setAlignment(Alignment alignment)
	{
		this.alignment = alignment;
		return this;
	}

}
