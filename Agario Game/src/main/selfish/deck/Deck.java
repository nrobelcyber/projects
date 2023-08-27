package selfish.deck;
import selfish.*;
//import selfish.GameException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Random;




/**
 * Public class called Deck
 *
 * @param cards cards
 */ 
public abstract class Deck implements Serializable {

	//private cards variable with List<Card> object return type
	private static final long serialVersionUID = 1L;
	private Collection<Card> cards;

	/**
	 *  protected constructor called Deck
	 * */
	protected Deck() {
		
		cards = new ArrayList<Card>();
		
	}
	/**
 * protected class called loadCards
 *
 * @param path path
 * @return list of cards loaded from the file
 * @throws selfish.GameException if file is not found or error occurs during the file processing
 */ 
	// LoadCards method is protected  with a List<Card> return type
	protected static List<Card> loadCards(String path) throws selfish.GameException
	{
		List<Card> cardList = new ArrayList<Card>();

		try(Scanner scanner = new Scanner(new File(path)))
		{
			// skip line
			scanner.nextLine();

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();

				Card[] cards = stringToCards(line);

				for (int i = 0; i < cards.length; i++)
				{
					cardList.add(cards[i]);
				}
			}

			return cardList;

		}

		catch(FileNotFoundException e)
		{
			throw new selfish.GameException("Cannot find file in path: " + path, e);
		}
	}
	/**
 	* Protected class called stringToCards
 	*
 	* @param str string 
 	* @return card array
 
 	*/

	protected static Card[] stringToCards(String str)
	{
		String[] cardArray = str.split(";");

		String name = cardArray[0].strip();
		String description = cardArray[1].strip();
		int quantity = Integer.parseInt(cardArray[2].strip());

		Card[] output = new Card[quantity];

		// add cards for quantity
		for (int i = 0; i < quantity; i++)
		{
			output[i] = new Card(name, description);
		}

		return output;
	}
	/**
 	* Public class called remove
 	*
 	* @param card removes card
 
 	*/
	public void remove(Card card)
	{
		cards.remove(card);
	}
	/**
 	* Public class called size
 	*
 	* @return the size of cards array
 
 	*/
	public int size()
	{
		return cards.size();
	}
	/**
 	* Public class called add
 	*
 	* @param card card
 	* @return add card to cards array 
 
 	*/
	public int add(Card card)
	{
		cards.add(card);

		return cards.size();
	}
	/**
 	* Public class called draw
 	*
 	* @return the drawn card
 
 	*/
	public Card draw()
	{
		Iterator<Card> iterator = cards.iterator();

		if (iterator.hasNext())
		{
			Card card = iterator.next();
			remove(card);
			return card;
		}

		throw new IllegalStateException("Deck is empty!");

	}
	/**
 	* Public class called shuffle
 	*
 	* @param random random shuffle
 
 	*/

	public void shuffle(Random random)
	{
		Collections.shuffle((List<Card>) cards, random);
	}
	/**
 	* Protected class called add
 	*
 	* @param cards cards
 	* @return size of cards array
 
 	*/
	protected int add(List<Card> cards)
	{
		for (int i = 0; i < cards.size(); i++)
		{

			add(cards.get(i));
		}

		return this.cards.size();
	}
	
	/**
 	* Public class called toString
 	*
 	* @return name 
 
 	*/
	@Override public String toString()
	{
		String output = "";
		Iterator<Card> iterator = cards.iterator();

		while (iterator.hasNext())
		{
			output += iterator.next().toString() + "\n";
		}

		return output;
	}
}

	

