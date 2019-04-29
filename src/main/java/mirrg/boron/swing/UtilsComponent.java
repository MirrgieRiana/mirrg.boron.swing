package mirrg.boron.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

public class UtilsComponent
{

	public static <T> T get(T t, Consumer<T> consumer)
	{
		consumer.accept(t);
		return t;
	}

	////////////////////////////// component creator

	// Button

	public static JButton createButton(String text)
	{
		return new JButton(text);
	}

	public static JButton createButton(Action action)
	{
		return new JButton(action);
	}

	public static JButton createButton(String text, ActionListener listener)
	{
		return addActionListener(new JButton(text), listener);
	}

	public static JButton createButton(String text, Action action)
	{
		JButton button = new JButton(action);
		button.setText(text);
		return button;
	}

	////////////////////////////// container creator

	// Panel

	public static JPanel createPanel(LayoutManager layout)
	{
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.setOpaque(false);
		return panel;
	}

	public static JPanel createPanel(LayoutManager layout, Component... components)
	{
		JPanel panel = createPanel(layout);
		Stream.of(components)
			.forEach(panel::add);
		return panel;
	}

	public static JPanel createPanel()
	{
		return createPanel(new CardLayout());
	}

	// PanelBorder

	private static Component createPanelBorderUp(int gap, List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		JPanel panel = createPanel(new BorderLayout(gap, gap));
		panel.add(components.get(0), BorderLayout.NORTH);
		{
			Component component = createPanelBorderUp(gap, components.subList(1, components.size()));
			if (component != null) panel.add(component, BorderLayout.CENTER);
		}

		return panel;
	}

	private static Component createPanelBorderDown(int gap, List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		JPanel panel = createPanel(new BorderLayout(gap, gap));
		{
			Component component = createPanelBorderDown(gap, components.subList(0, components.size() - 1));
			if (component != null) panel.add(component, BorderLayout.CENTER);
		}
		panel.add(components.get(components.size() - 1), BorderLayout.SOUTH);

		return panel;
	}

	private static Component createPanelBorderLeft(int gap, List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		JPanel panel = createPanel(new BorderLayout(gap, gap));
		panel.add(components.get(0), BorderLayout.WEST);
		{
			Component component = createPanelBorderLeft(gap, components.subList(1, components.size()));
			if (component != null) panel.add(component, BorderLayout.CENTER);
		}

		return panel;
	}

	private static Component createPanelBorderRight(int gap, List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		JPanel panel = createPanel(new BorderLayout(gap, gap));
		{
			Component component = createPanelBorderRight(gap, components.subList(0, components.size() - 1));
			if (component != null) panel.add(component, BorderLayout.CENTER);
		}
		panel.add(components.get(components.size() - 1), BorderLayout.EAST);

		return panel;
	}

	public static Component createPanelBorderUp(int gap, Component... components)
	{
		return createPanelBorderUp(gap, Arrays.asList(components));
	}

	public static Component createPanelBorderDown(int gap, Component... components)
	{
		return createPanelBorderDown(gap, Arrays.asList(components));
	}

	public static Component createPanelBorderLeft(int gap, Component... components)
	{
		return createPanelBorderLeft(gap, Arrays.asList(components));
	}

	public static Component createPanelBorderRight(int gap, Component... components)
	{
		return createPanelBorderRight(gap, Arrays.asList(components));
	}

	public static JPanel createPanelBorderVertical(int gap, Component top, Component middle, Component bottom)
	{
		JPanel panel = createPanel(new BorderLayout(gap, gap));

		if (top != null) panel.add(top, BorderLayout.NORTH);
		if (middle != null) panel.add(middle, BorderLayout.CENTER);
		if (bottom != null) panel.add(bottom, BorderLayout.SOUTH);

		return panel;
	}

	public static JPanel createPanelBorderHorizontal(int gap, Component left, Component center, Component right)
	{
		JPanel panel = createPanel(new BorderLayout(gap, gap));

		if (left != null) panel.add(left, BorderLayout.WEST);
		if (center != null) panel.add(center, BorderLayout.CENTER);
		if (right != null) panel.add(right, BorderLayout.EAST);

		return panel;
	}

	public static Component createPanelBorderUp(Component... components)
	{
		return createPanelBorderUp(4, components);
	}

	public static Component createPanelBorderDown(Component... components)
	{
		return createPanelBorderDown(4, components);
	}

	public static Component createPanelBorderLeft(Component... components)
	{
		return createPanelBorderLeft(4, components);
	}

	public static Component createPanelBorderRight(Component... components)
	{
		return createPanelBorderRight(4, components);
	}

	public static JPanel createPanelBorderVertical(Component top, Component middle, Component bottom)
	{
		return createPanelBorderVertical(4, top, middle, bottom);
	}

	public static JPanel createPanelBorderHorizontal(Component left, Component center, Component right)
	{
		return createPanelBorderHorizontal(4, left, center, right);
	}

	// other Panel

	public static JPanel createPanelCard(Component component)
	{
		return createPanel(new CardLayout(), component);
	}

	public static JPanel createPanelFlow(Component... components)
	{
		return createPanel(new FlowLayout(), components);
	}

	public static JPanel createPanelMargin(int top, int left, int bottom, int right, Component component)
	{
		return get(createPanelCard(component), c -> {
			c.setBorder(new EmptyBorder(top, left, bottom, right));
		});
	}

	public static JPanel createPanelMargin(int margin, Component component)
	{
		return createPanelMargin(margin, margin, margin, margin, component);
	}

	public static JPanel createPanelTitledBorder(String title, Component component)
	{
		return get(createPanelCard(component), c -> {
			c.setBorder(new TitledBorder(title));
		});
	}

	// SplitPane

	public static Component createSplitPaneHorizontal(Component... components)
	{
		return createSplitPaneHorizontal(Arrays.asList(components));
	}

	public static Component createSplitPaneHorizontal(List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
			components.get(0),
			createSplitPaneHorizontal(components.subList(1, components.size())));
	}

	public static JSplitPane createSplitPaneHorizontal(Component c1, Component c2)
	{
		return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, c1, c2);
	}

	public static JSplitPane createSplitPaneHorizontal(double resizeWeight, Component c1, Component c2)
	{
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, c1, c2);
		splitPane.setResizeWeight(resizeWeight);
		return splitPane;
	}

	public static Component createSplitPaneVertical(Component... components)
	{
		return createSplitPaneVertical(Arrays.asList(components));
	}

	public static Component createSplitPaneVertical(List<Component> components)
	{
		if (components.size() == 1) return components.get(0);
		return new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
			components.get(0),
			createSplitPaneVertical(components.subList(1, components.size())));
	}

	public static JSplitPane createSplitPaneVertical(Component c1, Component c2)
	{
		return new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, c1, c2);
	}

	public static JSplitPane createSplitPaneVertical(double resizeWeight, Component c1, Component c2)
	{
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, c1, c2);
		splitPane.setResizeWeight(resizeWeight);
		return splitPane;
	}

	// ScrollPane

	public static JScrollPane createScrollPane(Component component)
	{
		return new JScrollPane(component);
	}

	public static JScrollPane createScrollPane(Component component, int width, int height)
	{
		JScrollPane scrollPane = new JScrollPane(component);
		scrollPane.setPreferredSize(new Dimension(width, height));
		return scrollPane;
	}

	////////////////////////////// menubar

	// MenuBar

	public static JMenuBar createMenuBar(Component... components)
	{
		JMenuBar menuBar = new JMenuBar();
		for (Component component : components) {
			menuBar.add(component);
		}
		return menuBar;
	}

	/**
	 * @param components
	 *            nullの場合、セパレータを追加します。
	 */
	public static JMenu createMenu(String text, Component... components)
	{
		JMenu menu = new JMenu(text);
		for (Component component : components) {
			if (component == null) {
				menu.addSeparator();
			} else {
				menu.add(component);
			}
		}
		return menu;
	}

	public static JMenuItem createMenuItem(String text, ActionListener actionListener)
	{
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.addActionListener(actionListener);
		return menuItem;
	}

	public static JMenuItem createMenuItem(Action action)
	{
		return new JMenuItem(action);
	}

	////////////////////////////// properties

	public static <T extends AbstractButton> T setButtonMargin(T component, int top, int left, int bottom, int right)
	{
		component.setMargin(new Insets(top, left, bottom, right));
		return component;
	}

	public static <T extends Container> T setLayout(T component, LayoutManager layout)
	{
		component.setLayout(layout);
		return component;
	}

	public static <T extends Component> T setPreferredSize(T component, int width, int height)
	{
		component.setPreferredSize(new Dimension(width, height));
		return component;
	}

	public static <T extends Component> T setPreferredSizeFromRows(T component, int width, int rows)
	{
		return setPreferredSize(component, width, component.getFont().getSize() * rows + 6);
	}

	public static <T extends JComponent> T setToolTipText(T component, String string)
	{
		component.setToolTipText(string);
		return component;
	}

	public static <T extends AbstractButton> T addButtonIntoButtonGroup(T component, ButtonGroup buttonGroup)
	{
		buttonGroup.add(component);
		return component;
	}

	////////////////////////////// listener adder

	public static <T extends AbstractButton> T addActionListener(T component, ActionListener listener)
	{
		component.addActionListener(listener);
		return component;
	}

	public static <T extends JList<?>> T addListSelectionListener(T component, ListSelectionListener listener)
	{
		component.addListSelectionListener(listener);
		return component;
	}

	////////////////////////////// specialized event method hooker

	public static <T extends Component> T hookMouseRightClick(T component, Consumer<MouseEvent> listener)
	{
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3) listener.accept(e);
			}
		});
		return component;
	}

	public static <T extends Component> T hookMousePopup(T component, Consumer<MouseEvent> listener)
	{
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger()) listener.accept(e);
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger()) listener.accept(e);
			}
		});
		return component;
	}

	public static <T extends Component> T hookMouseDoubleClick(T component, Consumer<MouseEvent> listener)
	{
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2) listener.accept(e);
			}
		});
		return component;
	}

	public static <T extends JTextComponent> T hookDocumentUpdate(T textComponent, Consumer<DocumentEvent> listener)
	{
		textComponent.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				listener.accept(e);
			}

		});
		return textComponent;
	}

	////////////////////////////// other

	/**
	 * 表形式の {@link GroupLayout} を生成します。
	 * セルの結合には対応していません。
	 *
	 * @param gapSize
	 *            0以下の場合、ギャップは作成されません。
	 */
	public static LayoutManager createGroupLayout(Container host, Component[][] components, int gapSize)
	{
		GroupLayout groupLayout = new GroupLayout(host);

		int rows = components.length;
		int columns = components[0].length;

		{
			SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();

			boolean isFirst = true;
			for (int rowIndex = 0; rowIndex < rows; rowIndex++) {

				if (isFirst) {
					isFirst = false;
				} else {
					if (gapSize > 0) sequentialGroup.addGap(gapSize);
				}

				ParallelGroup parallelGroup = groupLayout.createParallelGroup();
				for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
					parallelGroup.addComponent(components[rowIndex][columnIndex]);
				}
				sequentialGroup.addGroup(parallelGroup);

			}

			groupLayout.setVerticalGroup(sequentialGroup);
		}

		{
			SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();

			boolean isFirst = true;
			for (int columnIndex = 0; columnIndex < columns; columnIndex++) {

				if (isFirst) {
					isFirst = false;
				} else {
					if (gapSize > 0) sequentialGroup.addGap(gapSize);
				}

				ParallelGroup parallelGroup = groupLayout.createParallelGroup();
				for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
					parallelGroup.addComponent(components[rowIndex][columnIndex]);
				}
				sequentialGroup.addGroup(parallelGroup);

			}

			groupLayout.setHorizontalGroup(sequentialGroup);
		}

		return groupLayout;
	}

}
