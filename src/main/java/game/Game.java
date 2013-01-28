package game;

import java.util.ArrayList;

import parsers.SimpleParser;
import pieces.Color;
import pieces.Piece;
import pieces.Type;
import board.Board;
import board.Location;
import board.Tile;

public class Game {

	private Board			board;
	private Color			currentPlayer;

	private ArrayList<Move>	moves	= new ArrayList<Move>( );

	public void changeCurrentPlayer() {
		this.currentPlayer = currentPlayer.equals( Color.WHITE ) ? Color.BLACK
				: Color.WHITE;
	}

	public String drawBoard() {
		return board.textDraw( );
	}

	public Location getLocation(String algebraic) {
		return Board.convertAlgebraic( algebraic );
	}

	public Type getPieceType(char abbr) {
		switch ( abbr ) {
		case 'P':
		case 'p':
			return Type.PAWN;
		case 'R':
		case 'r':
			return Type.ROOK;
		case 'N':
		case 'n':
			return Type.KNIGHT;
		case 'B':
		case 'b':
			return Type.BISHOP;
		case 'Q':
		case 'q':
			return Type.QUEEN;
		case 'K':
		case 'k':
			return Type.KING;
		default:
			return null;
		}
	}

	public Tile getTile(String algebraic) {
		Location loc = Board.convertAlgebraic( algebraic );
		return board.getSquare( loc );
	}

	public boolean movePiece(String move) {
		Location[] toFrom = SimpleParser.parse(move);
		Tile fromSquare = board.getSquare(toFrom[0]);
		Tile toSquare = board.getSquare(toFrom[1]);
		if( !fromSquare.isOccupied() || fromSquare.getOccupiedPiece().getColor() != currentPlayer || fromSquare.equals(toSquare)) {
			return false;
		} else {
			ArrayList<Tile> possibleMoves = fromSquare.getOccupiedPiece().getAllMoves();
			if ( !possibleMoves.contains( toSquare)) {
				return false;
			} else {
				board.movePiece( fromSquare.getOccupiedPiece(), toSquare);
				changeCurrentPlayer();
				return true;
			}
		}
	}

	public void setupGame() {
		board = new Board( );
		board.setupGame( );
		currentPlayer = Color.WHITE;

	}

	private boolean doMovement(Piece piece, Tile toSquare) {
		Piece capturedPiece = toSquare.getOccupiedPiece( );
		Tile oldSquare = board.getSquare( piece.getLocation( ) );
		boolean captured = board.movePiece( piece, toSquare );
		Move move;
		if ( captured ) {
			move = new Move( piece, capturedPiece, oldSquare, toSquare );
		} else {
			move = new Move( piece, oldSquare, toSquare );
		}
		this.moves.add( move );
		return true;

	}

}
