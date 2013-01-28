package game;

import java.util.Scanner;

public class TextGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		Game game = new Game( );
		game.setupGame( );
		System.out.println( game.drawBoard( ) );
		while ( sc.hasNextLine( ) ) {
			boolean correct = game.movePiece( sc.nextLine( ) );
			if ( !correct) {
				System.out.println( "You can't do that!!");
			}
			System.out.println( game.drawBoard( ) );
		}
	}
}
