import java.util.Random;

public class GameCode {
	public final int ROWS, COLS;
	private int[][] grid;
	private int score;
	private boolean moved;

	public GameCode(final int rows, final int cols) {
		this.ROWS = rows;
		this.COLS = cols;
		grid = new int[rows][cols];
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				grid[row][col] = 0;
	}

	public int getCellValue(int row, int col) {
		return grid[row][col];
	}

	public void slideLeft() {
		int destCol;
		for (int row = 0; row < ROWS; row++) {
			destCol = 0;
			for (int col = 1; col < COLS; col++) {
				if (destCol == col || grid[row][col] == 0) {
					continue;
				} else if (grid[row][col] == grid[row][destCol]) {
					grid[row][destCol] = grid[row][destCol] * 2;
					grid[row][col] = 0;
					score += grid[row][destCol];
					destCol++;
					moved = true;
				} else {
					if (grid[row][destCol] != 0)
						destCol++;
					if (destCol != col) {
						grid[row][destCol] = grid[row][col];
						grid[row][col] = 0;
						moved = true;
					}
				}
			}
		}
		update();
	}

	public void slideRight() {
		int destCol;
		for (int row = 0; row < ROWS; row++) {
			destCol = COLS - 1;
			for (int col = COLS - 2; col >= 0; col--) {
				if (destCol == col || grid[row][col] == 0) {
					continue;
				} else if (grid[row][col] == grid[row][destCol]) {
					grid[row][destCol] = grid[row][destCol] * 2;
					grid[row][col] = 0;
					score += grid[row][destCol];
					destCol--;
					moved = true;
				} else {
					if (grid[row][destCol] != 0)
						destCol--;
					if (destCol != col) {
						grid[row][destCol] = grid[row][col];
						grid[row][col] = 0;
						moved = true;
					}
				}
			}
		}
		update();
	}

	public void slideUp() {
		int destRow;
		for (int col = 0; col < COLS; col++) {
			destRow = 0;
			for (int row = 1; row < ROWS; row++) {
				if (destRow == row || grid[row][col] == 0) {
					continue;
				} else if (grid[row][col] == grid[destRow][col]) {
					grid[destRow][col] = grid[destRow][col] * 2;
					grid[row][col] = 0;
					score += grid[destRow][col];
					destRow++;
					moved = true;
				} else {
					if (grid[destRow][col] != 0)
						destRow++;
					if (destRow != row) {
						grid[destRow][col] = grid[row][col];
						grid[row][col] = 0;
						moved = true;
					}
				}
			}
		}
		update();
	}

	public void slideDown() {
		int destRow;
		for (int col = 0; col < COLS; col++) {
			destRow = ROWS - 1;
			for (int row = ROWS - 2; row >= 0; row--) {
				if (destRow == row || grid[row][col] == 0) {
					continue;
				} else if (grid[row][col] == grid[destRow][col]) {
					grid[destRow][col] = grid[destRow][col] * 2;
					grid[row][col] = 0;
					score += grid[destRow][col];
					destRow--;
					moved = true;
				} else {
					if (grid[destRow][col] != 0)
						destRow--;
					if (destRow != row) {
						grid[destRow][col] = grid[row][col];
						grid[row][col] = 0;
						moved = true;
					}
				}
			}
		}
		update();
	}

	public void addNew2() {
		Random random = new Random();
		while (true) {
//			int location = random.nextInt(ROWS * COLS);
//			int row = location / ROWS;
//			int col = location % COLS;
			int row = random.nextInt(ROWS);
			int col = random.nextInt(COLS);
			if (grid[row][col] == 0) {
				grid[row][col] = Math.random() < 0.9 ? 2 : 4;
				break;
			}
		}
	}

	public boolean canPlay() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (grid[row][col] == 0)
					return true;
				if (row > 0)
					if (grid[row][col] == grid[row - 1][col])
						return true;
				if (row < ROWS - 1)
					if (grid[row][col] == grid[row + 1][col])
						return true;
				if (col > 0)
					if (grid[row][col] == grid[row][col - 1])
						return true;
				if (col < COLS - 1)
					if (grid[row][col] == grid[row][col + 1])
						return true;
			}
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	private void update() {
		if (moved)
			addNew2();
		moved = false;
	}
}
