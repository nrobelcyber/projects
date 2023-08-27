//creating package selfish.deck
package selfish.deck;
import selfish.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Public class called GameDeck
 *
 * @param Hack_SUIT game
 * @param HOLE_IN_SUIT oxygens
 * @param LASER_BLAST actions
 * @param OXYGEN track
 * @param Oxygen_1 oxygen 1
 * @param Oxygen_2 oxygen 2
 * @param OXYGEN_SIPHON  oxygen_siphon
 * @param ROCKET_BOOSTER rocket booster
 * @param SHIELD shield
 * @param TETHER tether
 * @param TRACTOR_BEAM tractor beam
 */
public class GameDeck extends Deck {
	private static final long serialVersionUID = 1L;
	//HackSuit is static and  attributes  are readonly
	public static final String  HACK_SUIT = "Hack suit";
	//HoleInSuit is static and attributes are readonly
	public static final String  HOLE_IN_SUIT = "Hole in suit";
	//LaserBlast is static and  attributes are readonly
	public static final String  LASER_BLAST = "Laser blast";
	//Oxygen is static and  attributes  are readonly
	public static final String  OXYGEN = "Oxygen";
	//Oxygen1 is static and  attributes are readonly
	public static final String  OXYGEN_1 = "Oxygen(1)";
	//Oxygen2 is static and  attributes  are readonly
	public static final String  OXYGEN_2 = "Oxygen(2)";
	//OxygenSiphon is static and attributes  are readonly
	public static final String  OXYGEN_SIPHON = "Oxygen siphon";
	//RocketBooster is static and attributes  are readonly
	public static final String  ROCKET_BOOSTER = "Rocket booster";
	//Shield is static attributes and are readonly
	public static final String  SHIELD = "Shield";
	//Tether is static attributes and are readonly
	public static final String  TETHER = "Tether";
	//TractorBeam is static attributes and are readonly
	public static final String  TRACTOR_BEAM = "Tractor beam";
	//TractorBeam is static attributes and are readonly



	public GameDeck()
	{
	}

	/**
	 * public gameDeck constructor
	 * @param path file path */
	public GameDeck(String path) {
		try
		{
			List<Card> cards = loadCards(path);
			add(cards);	
		}

		catch (selfish.GameException e)
		{
			e.printStackTrace();
		}
	};
	/**
	 * public class called split oxygen
	 * @param dbl double oxygen card
	 * @return split oxygen array*/
	public Oxygen[] splitOxygen(Oxygen dbl)
	{
//		if (size() <= 1)
//		{
//			throw new IllegalStateException("Deck is empty!");
//		}

		if (dbl.getValue() == 1)
		{
			throw new IllegalArgumentException("Can't split a single oxygen!");
		}

		int count = 0;

		String[] lines = this.toString().split("\n");
		for (String line : lines)
		{
			if (line.startsWith("Oxygen (1)"))
			{
				count++;
			}
		}

		if (count > 2)
		{
			throw new IllegalStateException("Not enough single oxygens in deck to split!");
		}

	    Oxygen[] oxygens = new Oxygen[2];

	    // Get the value of the double-value oxygen card
	    int value = dbl.getValue();

	    // Create two new oxygen cards with half the value
	    Oxygen single1 = new Oxygen(value / 2);
	    Oxygen single2 = new Oxygen(value / 2);

	    // Add the new oxygen cards to the array
	    oxygens[0] = single1;
	    oxygens[1] = single2;

	    return oxygens;
	}
	/**
	 * public class called draw oxygen
	 * @param value  value of card
	 * @return drawn card*/
	public Oxygen drawOxygen(int value)
	{
			/*do {
			//create Random object
			 Random random = new Random();
			 random.nextInt(cards);
			}
			while(card  != instanceof oxygen   );
			cards[random.nextInt(cards) ] = -1;
			return cards[random.nextInt(cards)];*/
		throw new IllegalStateException("Cannot draw that oxygen!");
	}




}


	

