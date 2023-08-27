public class Bishop extends Piece{
	public  Bishop(PieceColour p) {
		if (p.equals(PieceColour.Black)) {
			this.colour = PieceColour.Black;
			this.setSymbol("♝");

		} else if(p.equals(PieceColour.White)) {
			this.colour = PieceColour.White;
			this.setSymbol("♗");
		}
	}

		@Override
		public  boolean isLegitMove(int i0,int j0,int i1,int j2 ) {
		return false;
		}
	}
	
