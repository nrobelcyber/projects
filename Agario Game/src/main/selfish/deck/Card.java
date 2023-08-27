//Create selfish.deck package
package selfish.deck;

import java.io.Serializable;
import java.lang.Comparable;


/**
 * Public class called Oxygen
 *
 * @param name  name of card
 * @param description description of card
 */  		
public class Card implements Serializable, Comparable<Card>
{
	private static final long serialVersionUID = 1L;

	//Variable is name which is private and a string
	private String name;
	//Variable is description which is private and a String
	private String description;

	/** 
	 * public class called Card
	 * @param name name of card
	 * @param description description of card
	 * */
	public Card(String name, String description)
	{
		// name variable initialising
		this.name = name;
		//description variable initialising 
		this.description = description;

	}

	/** 
	 * public class called getDescription
	 * @return return description
	 * */
	public String getDescription()
	{
		//return description
		return description;
	}

	/**
 	* Public class called toString
 	*
 	* @return name  with description
 
 	*/
	public String toString()
	{
		//return name of card
		return name/* + "\nDescription: " + description*/;
	}

	@Override
	public int compareTo(Card other)
	{
		return this.toString().compareTo(other.toString());
	}
}
