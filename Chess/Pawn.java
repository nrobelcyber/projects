
public class Pawn extends Piece {
	//private String symbol;
	//protected PieceColour colour;
		public  Pawn(PieceColour p) {
			if (p.equals(PieceColour.Black)) {
				this.colour = PieceColour.Black;
				this.setSymbol("♟");

			} else if(p.equals(PieceColour.White)){
				this.colour = PieceColour.White;
				this.setSymbol("♙");

			}

		}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		//return i1 = i0 +1;
		return false;
	}
}