package algorithm;

import javax.swing.*;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIFor2048 extends JPanel {
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final String FONT_NAME = "Arial";
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;
	public static Algorithm_2048 algorithm;
	public static long[][] myBoard = new long[4][4];

	boolean myWin = false;
	boolean myLose = false;
	
	public UIFor2048() {
		setPreferredSize(new Dimension(340, 400));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				long[][] copy = new long[myBoard.length][myBoard.length];
				for (int i = 0; i < myBoard.length; i++)
					for (int j = 0; j < myBoard.length; j++)
						copy[i][j] = myBoard[i][j];
				
				if (!Algorithm_2048.end(myBoard) && !Algorithm_2048.win(myBoard)) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						myBoard = Algorithm_2048.left(myBoard);
						break;
					case KeyEvent.VK_RIGHT:
						myBoard = Algorithm_2048.right(myBoard);
						break;
					case KeyEvent.VK_DOWN:
						myBoard = Algorithm_2048.down(myBoard);
						break;
					case KeyEvent.VK_UP:
						myBoard = Algorithm_2048.up(myBoard);
						break;
					}
					
					if (!Algorithm_2048.same(copy, myBoard))
						myBoard = Algorithm_2048.afterMove(myBoard);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					myBoard = new long[4][4];
					myBoard = Algorithm_2048.newBoard(myBoard);
				}

				repaint();
			}
		});
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				drawTile(g, myBoard[y][x], x, y); 
			}
		}
	}

	private void drawTile(Graphics g2, long value, int x, int y) {
		Graphics2D g = ((Graphics2D) g2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2));
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
		g.setColor(valueColor(value));
		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

		if (Algorithm_2048.end(myBoard) || Algorithm_2048.win(myBoard)) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
			if (Algorithm_2048.win(myBoard)) {
				g.drawString("You won!", 68, 150);
			}
			else if (Algorithm_2048.end(myBoard)) {
				g.drawString("Game over!", 50, 130);
				g.drawString("You lose!", 64, 200);
			}
			
			g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
			g.setColor(new Color(128, 128, 128, 128));
			g.drawString("Press ESC to play again", 80, getHeight() - 40);
		
		}
		g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		long score = Algorithm_2048.totalScore(myBoard);
		g.drawString("Score: " + score, 200, 365);
	}

	private static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}
	
	public Color valueColor(long value) {
		switch ((int)value) {
		case 2:
			return new Color(0xeee4da);
		case 4:
			return new Color(0xede0c8);
		case 8:
			return new Color(0xf2b179);
		case 16:
			return new Color(0xf59563);
		case 32:
			return new Color(0xf67c5f);
		case 64:
			return new Color(0xf65e3b);
		case 128:
			return new Color(0xedcf72);
		case 256:
			return new Color(0xedcc61);
		case 512:
			return new Color(0xedc850);
		case 1024:
			return new Color(0xedc53f);
		case 2048:
			return new Color(0xedc22e);
		}
		return new Color(0xcdc1b4);
	}
	
	public static void main(String[] args) {
		myBoard = Algorithm_2048.newBoard(myBoard);
		Algorithm_2048.showBoard(myBoard);
	    JFrame game = new JFrame();
	    game.setTitle("2048 Game ---V1.0");
	    game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    game.setSize(340, 400);
	    game.setResizable(false);

	    game.add(new UIFor2048());

	    game.setLocationRelativeTo(null);
	    game.setVisible(true);
	}

}
