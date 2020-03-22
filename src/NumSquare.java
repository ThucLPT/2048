import java.awt.*;

import javax.swing.JComponent;

public class NumSquare extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final int SCALE = 100, BORDER = SCALE / 20, FONT_SIZE = (int) (SCALE * 0.4);
	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE);
	private int value;

	public NumSquare() {
		setFont(FONT);
		setPreferredSize(new Dimension(SCALE, SCALE));
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.decode("#bbada0"));
		g.fillRect(0, 0, getWidth(), getHeight());

		Color color;
		switch (value) {
		case 0:
			color = Color.decode("#cdc1b4");
			break;
		case 2:
			color = Color.decode("#eee4da");
			break;
		case 4:
			color = Color.decode("#ede0c8");
			break;
		case 8:
			color = Color.decode("#f2b179");
			break;
		case 16:
			color = Color.decode("#f59563");
			break;
		case 32:
			color = Color.decode("#f67c5f");
			break;
		case 64:
			color = Color.decode("#f65e3b");
			break;
		case 128:
			color = Color.decode("#edcf72");
			break;
		case 256:
			color = Color.decode("#edcc61");
			break;
		case 512:
			color = Color.decode("#edc850");
			break;
		case 1024:
			color = Color.decode("#edc53f");
			break;
		case 2048:
			color = Color.decode("#edc22e");
			break;
		default:
			color = Color.decode("#3c3a32");
			break;
		}
		g.setColor(color);
		g.fillRect(BORDER, BORDER, getWidth() - 10, getHeight() - 10);

		if (value == Math.pow(2, 1) || value == Math.pow(2, 2))
			g.setColor(Color.decode("#776e65"));
		else
			g.setColor(Color.decode("#f9f6f2"));

		if (value > 0) {
			FontMetrics metrics = getFontMetrics(FONT);
			String txt = Integer.toString(value);
			g.drawString(txt, (getWidth() - metrics.stringWidth(txt)) / 2, getHeight() / 2 + metrics.getAscent() / 3);
		}
	}
}
