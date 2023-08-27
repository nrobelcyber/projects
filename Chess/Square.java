


public class Square{

	private boolean hasPiece;
	private Piece p;


	public Piece getPiece(){
		return p;
		
	}

	public void setPiece(Piece p ){
		this.p = p;
		hasPiece = true;
	
	}
	public void removePiece(){
		hasPiece = false;
		p = null;



	}
	public boolean hasPiece() {
		return hasPiece;
		
	}

	

}