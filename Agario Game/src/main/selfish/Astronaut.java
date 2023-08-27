//create selfish package
package selfish; 
import selfish.deck.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

 /**
 * Public class called Astronaut
 *
 * @param game game
 * @param oxygens oxygens
 * @param actions actions
 * @param track track
 */
public class Astronaut implements Serializable{
	//private variable with object called GameEngine and variable name is game

	private static final long serialVersionUID = 1L;
	private GameEngine game;
	private List<Oxygen> oxygens;
	private List<Card> actions;
	private Collection<Card> track;


	//private variable object type is String ,variable name is called name
	private String name; 

	/**
 * Public class called Astronaut
 *
 * @param name name of player with string data type.
 * @param game game with GameEngine type.
 **/
	public Astronaut(String name, GameEngine game)
	{
		//initiating variables
		this.game = game;
		//initiating varaibales
		this.name = name;

		oxygens = new ArrayList<Oxygen>();
		actions = new ArrayList<Card>();
		track = new ArrayList<Card>();
	}
	/**
 	* Public class called addToHand
 	*
 	* @param card card
 
 	*/
	public void addToHand(Card card)
	{
		// if oxygen card
		if (card instanceof Oxygen)
		{
			oxygens.add((Oxygen)card);
		}

		// otherwise assume it's an action card
		else
		{
			actions.add(card);
		}
	}
	
	/**
 	* Public class called peekAtTrack
 	*
 	* @return second to last  card in track
 
 	*/
	public Card peekAtTrack()
	{
		if (track.size() == 0)
		{
			return null;
		}

		// use an iterator to traverse the track and store in an arraylist
		ArrayList<Card> currentTrack = new ArrayList<Card>();
		Iterator<Card> iterator = track.iterator();
		while (iterator.hasNext())
		{
			currentTrack.add(iterator.next());
        }

		return currentTrack.get(currentTrack.size() - 2);
	}
	/**
	 * public class called laserblast
	 * @return last card from the track
	 */
	public Card laserBlast()
	{
		if (distanceFromShip() == 6)
		{
			throw new IllegalArgumentException("Can't laser blast an astronaut on the starting square!");
		}


		Iterator<Card> iterator = track.iterator();
		Card blasted = iterator.next();
		while (iterator.hasNext())
		{
			blasted = iterator.next();
        }

		track.remove(blasted);
		return blasted;
	}
	/**
 	* Public class called hasMeltedEyeballs
 	*@return {@code true} if track begins with Solar Flare
 	* 		 {@code false} if it does not
 
 	*/
	public boolean hasMeltedEyeballs()
	{
		return peekAtTrack().getDescription().startsWith("Solar flare");
	}
	/** 
	 * public class called getTrack
	 * @return track
	 * **/
	public Collection<Card> getTrack()
	{
		return track;
	}
	/**
 	* Public class called swapTrack
 	*
 	* @param card card.
 
 	*/
	public void addToTrack(Card card)
	{
		track.add(card);
	}
	/**
 	* Public class called oxygenRemaining
 	*@return total amount of oxygen remaining
 
 	*/
	public int oxygenRemaining()
	{
		int oxygen = 0;

		for (int i = 0; i < oxygens.size(); i++)
		{
			oxygen += oxygens.get(i).getValue();
		}

		return oxygen;
	}
	/**
 	* Public class called breathe
 	*@return oxygen remaining
 	*/	

	public int breathe() 
	{
		// get first oxygen card in hand
		Oxygen oxygen = oxygens.get(0);		

		if (oxygen.getValue() == 2)
		{
			Oxygen[] splitOxygen = game.splitOxygen(oxygen);

			oxygens.remove(oxygen);
			oxygens.add(splitOxygen[0]);
			oxygen = splitOxygen[1];
		}
		else
		{
			// remove the card from players hand
			oxygens.remove(oxygen);
		}


		// add the card to the game discard pile
		game.getGameDiscard().add(oxygen);
		
		return oxygenRemaining();
	}
	/**
 	* Public class called distanceFromShip
 	*@return distance from ship
 	*/	
	public int distanceFromShip()
	{
		return 6 - track.size();
	}
	/**
 	* Public class called getActions
 	*@return actions deck
 	*/	
	public List<Card> getActions()
	{
		return actions;
	}
	/**
 	* Public class called getHand
 	*@return hand
 	*/	
	public List<Card> getHand()
	{
		List<Card> output = new ArrayList<Card>();

		output.addAll(oxygens);
		output.addAll(actions);

		return output;
	}
	/**
 	* Public class called getHandStr
 	*@return hand
 	*/	
	public String getHandStr()
	{
		List<Card> hand = getHand();
		String output = "";

		for (int i = 0; i < hand.size(); i++)
		{
			output += hand.get(i).toString();

			if (i + 1 < hand.size())
			{
				output += ", ";
			}
		}

		return output;
	}
	/**
 	* Public class called hack
 	*
 	* @param card card.
 
 	*/
	public void hack(Card card)
	{
		if (oxygens.contains(card))
		{
			oxygens.remove(card);
		}

		else
		{
			actions.remove(card);
		}
	}
	/**
 	* Public class called hack
 	* @returns Card
 	*
 	* @param card card.
 	* @return card
 
 	*/
	public Card hack(String card)
	{
		List<Card> hand = getHand();

		for (int i = 0; i < hand.size(); i++)
		{
			Card output = hand.get(i);

			if (output.getDescription().startsWith(card))
			{
				hack(output);
				return output;
			}
		}

		// should only happen if card name is incorrect
		throw new IllegalArgumentException("Card string is invalid!");
	}
	/**
 	* Public class called hasCard
 	*
 	* @param card card.
 	* @return card
 
 	*/
	public int hasCard(String card)
	{
		int output = 0;
		List<Card> hand = getHand();

		for (int i = 0; i < hand.size(); i++)
		{
			if (hand.get(i).getDescription().startsWith(card))
			{
				output++;
			}
		}

		return output;
	}
	/**
 	* Public class called isAlive
 	*@return {@code true} Astronaut is alive
 	* 		 {@code false} if astranout is alive
 
 	*/	
	public boolean isAlive()
	{
		return oxygenRemaining() > 0;
	}
	/**
 	* Public class called getActionsStr
 	*
 	* @param enumerated     enumerated
 	* @param excludeShields exclude shields
 	* @return actions in string
 
 	*/
	public String getActionsStr(boolean enumerated, boolean excludeShields)
	{

		// TODO: ADD THE ENUMERATED BOOL

		String output = "";

		for (int i = 0; i < actions.size(); i++)
		{
			if (excludeShields &&
				actions.get(i).getDescription().startsWith("Shield"))
			{
				continue;
			}

			output += actions.get(i).toString();
		}

		return output;
	}
	/**
 	* Public class called siphon
 	*@return oxygen deck split
 	*/
	public Oxygen siphon()
	{
		Oxygen oxygen;

		// attempt to siphon a single oxygen card
		for (int i = 0; i < oxygens.size(); i++)
		{
			if (oxygens.get(i).getValue() == 1)
			{
				oxygen = oxygens.get(i);
				oxygens.remove(oxygen);
				return oxygen;
			}
		}

		// if no single cards, split a double
		oxygen = oxygens.get(0);
		oxygens.remove(oxygen);

		Oxygen[] split = game.splitOxygen(oxygen);

		oxygens.add(split[0]);

		return split[1];
	}
	/**
 	* Public class called steal
 	*
 	* @return card.
 
 	*/
	public Card steal() 
	{
		List<Card> hand = getHand();
		Random random = new Random();
		int index = random.nextInt(hand.size());

		Card card = hand.get(index);

		if (card instanceof Oxygen)
		{
			oxygens.remove(card);
		}

		else
		{
			actions.remove(card);
		}

		return card;
	}
	/**
 	* Public class called hasWon
 	*
 	* @return hasWon won or not 
 
 	*/
	public boolean hasWon()
	{
		return distanceFromShip() == 0 && isAlive();
	}

	/**
 	* Public class called swapTrack
 	*
 	* @param swapee swapee.
 
 	*/
	public void swapTrack(Astronaut swapee)
	{
		Collection<Card> playerTrack = track;
		Collection<Card> swapeeTrack = swapee.getTrack();

		// set the players track to the swapees track
		track = swapeeTrack;

		// laser blast the swapees track out of existence
		while (swapeeTrack.size() > 0)
		{
			swapee.laserBlast();
		}

		// build up the swapees track
		Iterator<Card> iterator = playerTrack.iterator();
		while (iterator.hasNext())
		{
			swapee.addToTrack(iterator.next());
        }
		
	}

	/**
 	* Public class called toString
 	*
 	* @return the name of the object as a string 
 
 	*/

	@Override
	public String toString() {
		//returns the name
		return name;
	}

}
