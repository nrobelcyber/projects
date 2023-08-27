package selfish.deck; //creating package selfish.deck

import java.io.Serializable;

/**
 * Public class called SpaceDeck
 *
 * @param ASTEROID_FIELD asteroid field
 * @param BLANK_SPACE blank spaced
 * @param COSMIC_RADIATION actions
 * @param GRAVITATION_ANOMALY gravitation anomaly
 * @param HYPERSPACE hyperspace
 * @param METEOROID meteoroid
 * @param MYSTERIOUS_NEBULA  mysterious nebula
 * @param SOLAR_FLARE solar flare
 * @param USEFUL_JUNK useful junk
 * @param WORMHOLE wormhole
 */
public class SpaceDeck extends Deck{// class is public and is a subclass inherits from superclass deck

	private static final long serialVersionUID = 1L;

	// AsteroidField is static and  attributes are readonly
	public static final String  ASTEROID_FIELD = "Asteroid field";
	// BlankSpace is static and attributes  are readonly
	public static final String  BLANK_SPACE = "Blank space";
	//CosmicRadiation is static and  attributes are readonly
	public static final String  COSMIC_RADIATION = "Cosmic radiation";
	// GravitationalAnomaly is  static  and attributes  are readonly
	public static final String  GRAVITATIONAL_ANOMALY = "Gravitational anomaly";
	//Hyperspace is static and  attributes are readonly
	public static final String  HYPERSPACE = "Hyperspace";
	//Meteoroid is static and attributes  are readonly
	public static final String  METEOROID = "Meteoroid" ;
	//MysteriousNebula is static and attributes  are readonly
	public static final String  MYSTERIOUS_NEBULA = "Mysterious nebula";
	//SolarFlare is static and  attributes are readonly
	public static final String  SOLAR_FLARE = "Solar flare";
	//UsefulJunk is static and  attributes  are readonly
	public static final String  USEFUL_JUNK = "Useful junk";
	//Wormhole is static and  attributes are readonly
	public static final String  WORMHOLE = "Wormhole";
	//Wormhole is static attributes and are readonly

	/**
 * Public constructor called SpaceDeck

 */
	public SpaceDeck()
	{

	}
	/**
 * Public class called SpaceDeck
 *
 * @param path filepath
 */
	public SpaceDeck(String path)
	{
		try
		{
			add(loadCards(path));
		}
		catch(selfish.GameException e)
		{
			e.printStackTrace();
		}

	}
} 
