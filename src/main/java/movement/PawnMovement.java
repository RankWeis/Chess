package movement;

import java.util.ArrayList;

import pieces.Color;
import board.Board;
import board.Location;

public class PawnMovement {

	public static ArrayList<Location> getAdjacentMoves(Board board,
			Color color, int row, int col) {
		return getAdjacentMoves( board, color, row, col, false );
	}

	public static ArrayList<Location> getAdjacentMoves(Board board,
			Color color, int row, int col, boolean attacksOnly) {
		ArrayList<Location> ret = new ArrayList<Location>( );
		int direction = color == Color.WHITE ? 1 : -1;
		int backRow = color == Color.WHITE ? 1 : 6;

		int[][] legalAttacks = {
				{
						row + direction, col + 1
				}, {
						row + direction, col - 1
				}

		};
		for ( int[] curr : legalAttacks ) {
			if ( checkLegal( curr[0], curr[1] ) ) {
				ret.add( new Location( curr[0], curr[1] ) );
			}
		}

		if ( !attacksOnly ) {
			@SuppressWarnings("unchecked")
			ArrayList<Location> clone = (ArrayList<Location>) ret.clone( );
			for ( Location loc : clone ) {
				if ( !board.getSquare( loc ).isOccupied( ) ) {
					ret.remove( loc );
				}
			}
			if ( checkLegal( row + direction, col ) ) {
				ret.add( new Location( row + direction, col ) );
			}
			if ( row == backRow ) {
				if ( checkLegal( row + 2 * direction, col ) ) {
					ret.add( new Location( row + 2 * direction, col ) );
				}
			}
		}
		return ret;
	}

	private static boolean checkLegal(int row, int col) {
		if ( row < 8 && row >= 0 && col < 8 && col >= 0 ) {
			return true;
		} else {
			return false;
		}
	}

}
