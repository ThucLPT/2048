import java.awt.GridLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int ROWS, COLS;
	private NumSquare[][] numbers;

	public GamePanel(int xSize, int ySize) {
		removeAll();
		COLS = xSize;
		ROWS = ySize;
		setLayout(new GridLayout(ROWS, COLS));
		numbers = new NumSquare[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				numbers[row][col] = new NumSquare();
				add(numbers[row][col]);
			}
		}
	}

	public void setValue(int row, int col, int value) {
		numbers[row][col].setValue(value);
	}
}
