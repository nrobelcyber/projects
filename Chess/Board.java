
public class Board extends Square{

	private static Square [][] board = new Square[8][8];

	public static void initialiseBoard(){
		for (int i=0; i<board[0].length; i++){
			for (int j=0; j<board[1].length; j++)
					board[i][j]=new Square();
		}

	}
	public  static void initialisePieces(){
		//white 
		setPiece(7,0, new Rook(PieceColour.White));
		setPiece(7,1, new  Knight(PieceColour.White));
		setPiece(7,2, new Bishop(PieceColour.White));
		setPiece(7,3, new Queen(PieceColour.White));
		setPiece(7,4, new King(PieceColour.White));
		setPiece(7,5, new Bishop(PieceColour.White));
		setPiece(7,6, new Knight(PieceColour.White));
		setPiece(7,7, new Rook(PieceColour.White));
		setPiece(6,0, new Pawn(PieceColour.White));
		setPiece(6,1, new Pawn(PieceColour.White));
		setPiece(6,2, new Pawn(PieceColour.White));
		setPiece(6,3, new Pawn(PieceColour.White));
		setPiece(6,4, new Pawn(PieceColour.White));
		setPiece(6,5, new Pawn(PieceColour.White));
		setPiece(6,6, new Pawn(PieceColour.White));
		setPiece(6,7, new Pawn(PieceColour.White));




		//black
		setPiece(0,0, new Rook(PieceColour.Black));
		setPiece(0,1, new  Knight(PieceColour.Black));
		setPiece(0,2, new Bishop(PieceColour.Black));
		setPiece(0,3, new Queen(PieceColour.Black));
		setPiece(0,4, new King(PieceColour.Black));
		setPiece(0,5, new Bishop(PieceColour.Black));
		setPiece(0,6, new Knight(PieceColour.Black));
		setPiece(0,7, new Rook(PieceColour.Black));
		setPiece(1,0, new Pawn(PieceColour.Black));
		setPiece(1,1, new Pawn(PieceColour.Black));
		setPiece(1,2, new Pawn(PieceColour.Black));
		setPiece(1,3, new Pawn(PieceColour.Black));
		setPiece(1,4, new Pawn(PieceColour.Black));
		setPiece(1,5, new Pawn(PieceColour.Black));
		setPiece(1,6, new Pawn(PieceColour.Black));
		setPiece(1,7, new Pawn(PieceColour.Black));





	};
	public static void printBoard() {
		System.out.print("\n  a b c d e f g h \n");
		System.out.print("  -----------------\n");

		for (int i = 0; i < board[0].length; i++) {
			int row = i + 1;
			for (int j = 0; j < board[1].length; j++) {
				if ((j == 0) && Board.hasPiece( i, j))
					System.out.print(row + " " + Board.getPiece(i, j).getSymbol());
				else if ((j == 0) && !Board.hasPiece(i, j))
					System.out.print(row + "  ");
				else if (Board.hasPiece(i, j))
					System.out.print("|" + Board.getPiece(i, j).getSymbol());
				else
					System.out.print("| ");
			}
			System.out.print("  " + row + "\n");
		}
		System.out.print("  -----------------");
		System.out.print("\n  a b c d e f g h \n");

	}
	public static boolean movePiece(int i0 ,int j0 ,int i1 ,int j1 ,Piece p) {
		boolean kingCaptured = false;
		board[i0][j0].removePiece();
		if (board[i1][j1].hasPiece()) {
			//other way Board.getPiece(i1,j1).getClass().simpleName();
			boolean isKing = Board.getPiece(i1,j1) instanceof King;
			if (isKing) {
		   		kingCaptured= true ;
		   		System.out.println("King Captured!!"); 

			}
		}
		Board.setPiece(i1,j1,p);
		printBoard();
		return kingCaptured;
	}



	public static void setPiece(int i0 ,int j0 ,Piece p ){
		board[i0][j0].setPiece(p);


	}
	public static Piece getPiece(int i,int j){
		return board[i][j].getPiece();

	}
	public static boolean hasPiece(int i,int j ){
		return board[i][j].hasPiece();


	}

	

}