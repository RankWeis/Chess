package view;

import game.Game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pieces.Piece;
import board.Board;
import board.Location;

public class ChessApp {

	private JFrame frame;
	private Color color = Color.WHITE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessApp window = new ChessApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChessApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Game game = new Game();
		game.setupGame();
		Board board = game.getBoard();
		
		frame = new JFrame();
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int i = 8;
		int j = 8;
		GridLayout grid = new GridLayout(i,j,1,1);
		JPanel[][] panelHolder = new JPanel[i][j];
		frame.getContentPane().setLayout( grid);
		frame.setBackground(Color.black);

		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
			   Location loc = new Location(7 - m, n);
			   JPanel curr = new JPanel();
			   panelHolder[m][n] = curr;
			   JTextArea button = new JTextArea( board.getSquare(loc).toString());
			   curr.add(button);
//		      newText.setBounds(0, 0, 10, 10);
//		      curr.add(newText);
		      curr.setBackground(color);
		      switchColor();
//		      curr.setBounds(10*m, 10*n, 10, 10);
//		      curr.setVisible(true§
		      frame.getContentPane().add(curr);
		   }
		   switchColor();
		}

	}
	
	private void switchColor( ) {
		this.color = this.color == Color.WHITE ? Color.DARK_GRAY : Color.WHITE;
		
	}

}
