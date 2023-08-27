//Creating package called selfish.deck
package selfish.deck;

import java.io.Serializable;
import java.lang.Comparable;

/**
 * Public class called Oxygen
 *
 * @param value  value of card
 */ 
public class Oxygen extends Card implements Serializable, Comparable<Card>
{
	private static final long serialVersionUID = 1L;

	// private variable with integer data type with name called value
	private int value;
	//private value variable 


	/**
 * Public constructor called Oxygen
 *
 * @param value value of card
 */ 
	public Oxygen(int value) {
		//Oxygen card 
		super("Oxygen(" + value + ")","The description goes here");

		// the value initializiing
		this.value = value;
		//the value initializing 
	

	}
	/**
 * Public class called getValue
 *
 * @return value value of card
 */
	public int getValue() {
		//return the value 
		return value;
		//return value
	}

	/**
 	* Public class called toString
 	*
 	* @return name with value
 
 	*/

	public String toString () {//ToString method which returns oxygen card
	//ToString method which returns oxygen card
		//concatenating name with value using ToString 
		return super.toString();
		//Concatenating toString 
	}

	@Override
	public int compareTo(Card other)
	{
		if (other instanceof Oxygen)
		{
			char otherValueChar = other.toString().charAt(other.toString().length() - 2);
			int otherValue = otherValueChar - '0';
			return Integer.compare(this.getValue(), otherValue);
		}

		return this.toString().compareTo(other.toString());
	}
}
