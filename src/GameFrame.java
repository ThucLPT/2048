import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GamePanel panelGame;
	private GameCode game;
	private JLabel label;

	public GameFrame() {
		setLayout(new BorderLayout());

		game = new GameCode(4, 4);
		panelGame = new GamePanel(game.COLS, game.ROWS);
		add(panelGame);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.decode("#faf8ef"));
		add(panel, BorderLayout.EAST);

		label = new JLabel();
		label.setBackground(Color.decode("#bbada0"));
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		label.setForeground(Color.decode("#ffffff"));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		panel.add(label);

		JButton button = new JButton("New Game");
		button.setBackground(Color.decode("#8f7a66"));
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		button.setForeground(Color.decode("#f9f6f2"));
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		panel.add(button);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					game.slideUp();
					updateNumSquares();
					break;
				case KeyEvent.VK_DOWN:
					game.slideDown();
					updateNumSquares();
					break;
				case KeyEvent.VK_LEFT:
					game.slideLeft();
					updateNumSquares();
					break;
				case KeyEvent.VK_RIGHT:
					game.slideRight();
					updateNumSquares();
					break;
				}
			}
		});

		game.addNew2();
		game.addNew2();
		updateNumSquares();

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void updateNumSquares() {
		for (int row = 0; row < game.ROWS; row++)
			for (int col = 0; col < game.COLS; col++)
				panelGame.setValue(row, col, game.getCellValue(row, col));
		panelGame.repaint();
		label.setText("<html><center>SCORE<br>" + game.getScore());
		if (!game.canPlay())
			gameOver();
	}

	private void gameOver() {
		String[] options = { "Try again", "Exit" };
		int result = JOptionPane.showOptionDialog(this, "Your score was " + game.getScore(), "Game over!",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (result == JOptionPane.YES_OPTION)
			newGame();
		else
			System.exit(0);
	}

	private void newGame() {
		game = new GameCode(4, 4);
		game.addNew2();
		game.addNew2();
		updateNumSquares();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new GameFrame().setVisible(true);
	}
}
