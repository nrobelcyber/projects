package selfish; //create package selfish
//import java.util.BufferedReader;
// GameException class is public 

/**
 * Public class called GameException
 *
 */
public class GameException extends Exception {

	/**
 * Public constructor called GameException
 *
 * @param e  the cause of exception
 * @param message error message
 */ 
	public GameException(String message, Throwable e) {
		super (message);
		
	}
}

	


