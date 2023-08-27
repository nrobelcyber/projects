// create package called selfish
package selfish;
import selfish.deck.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Public class called GameEngine
 *
 * @param random random
 * @param Astronaut  player
 * @param gameDeck  game Deck
 * @param spaceDeck    space deck
 * @param spaceDiscard  space discard pile
 * @param gameDiscard   game discard pile
 * @param hasStarted    check if game started
 * @param activePlayers
 * @param  corpses  dead astronauts
 */
public class GameEngine implements Serializable{

	private static final long serialVersionUID = 1L;

	private Random random;
	private Astronaut currentPlayer;
	private GameDeck gameDeck;
	private GameDeck gameDiscard;
	private SpaceDeck spaceDeck;
	private SpaceDeck spaceDiscard;
	private boolean hasStarted;
	private Collection<Astronaut> activePlayers;
	private List<Astronaut> corpses;
	/**
 * Private constructor called GameException
 *
 */ 
	private GameEngine() 
	{

	}
	/**
	 * Public constructor called GameEngine
	 * @param seed seed
	 * @param gameDeck gameDeck
	 * @param spaceDeck spaceDeck
	 */
	public GameEngine(long seed, String gameDeck, String spaceDeck) 
	{
		activePlayers = new ArrayList<Astronaut>();
		corpses = new ArrayList<Astronaut>();

		this.gameDeck = new GameDeck(gameDeck);
		this.spaceDeck = new SpaceDeck(spaceDeck);
		this.gameDiscard = new GameDeck();
		this.spaceDiscard = new SpaceDeck();
		this.random = new Random(seed);
	}

	/**
 	* Public class called addPlayer
 	*
 	* @param player player
 	* @return new amount of players
 
 	*/
	public int addPlayer(String player)
	{
		Astronaut newPlayer = new Astronaut(player, this);
		activePlayers.add(newPlayer);

		if (activePlayers.size() > 5)
		{
			throw new IllegalStateException("Too many players!");
		}

		if (hasStarted)
		{
			throw new IllegalStateException("Game has already begun!");
		}

		return activePlayers.size();
	}
	/**
 	* Public class called startgame which starts game by shuffling both space and game deck and initiating the first turn
 
 	*/
	public void startGame() 
	{
		if (activePlayers.size() <= 1)
		{
			throw new IllegalStateException("Not enough players!");
		}

		if (activePlayers.size() > 5)
		{
			throw new IllegalStateException("Too many players!");
		}

		if (hasStarted)
		{
			throw new IllegalStateException("Game has already begun!");
		}
		hasStarted = true;
		spaceDeck.shuffle(random);
		gameDeck.shuffle(random);
		startTurn();
	}
	/**
 	* Public class called startTurn which starts turn
 	*
 
 	*/
	public void startTurn()
	{
		if (!hasStarted)
		{
			throw new IllegalStateException("Game is yet to begin!");
		}

		if (corpses.size() == getFullPlayerCount())
		{
			throw new IllegalStateException("Everyone is dead!");
		}


		Iterator<Astronaut> iterator = activePlayers.iterator();

		if (iterator.hasNext())
		{
			currentPlayer = iterator.next();
			activePlayers.remove(currentPlayer);
		}
	}
	/**
 	* Public class called endTurn 
 	*
 	* @return size of of active players
 
 	*/
	public int endTurn()
	{
		activePlayers.add(currentPlayer);
		return activePlayers.size();
	}
	/**
 	* Public class called mergeDecks 
 	*
 	* @param deck1 deck.
 	* @param deck2 deck
 
 	*/
	public void mergeDecks(Deck deck1, Deck deck2)
	{
		while (deck2.size() > 0)
		{
			deck1.add(deck2.draw());
		}
	}
	/**
 	* Public class called getAllPlayers 
 	*
 	* @return list of players or astonauts
 
 	*/
	public List<Astronaut> getAllPlayers()
	{
		List allPlayers = new ArrayList<Astronaut>();

		allPlayers.add(currentPlayer);

		Iterator<Astronaut> iterator = activePlayers.iterator();
		while (iterator.hasNext())
		{
			allPlayers.add(iterator.next());
		}

		for (int i = 0; i < corpses.size(); i++)
		{
			allPlayers.add(corpses.get(i));
		}

		return allPlayers;
	}
	/**
 * Public class called getFullPlayerCount
 *
 * @return size of players playing
 * 
 */ 
	public int getFullPlayerCount()
	{
		return getAllPlayers().size();
	}
	/**
 	* Public class called mergeDecks 
 	*
 	* @param corpse dead astronaut.
 
 	*/	
	public void killPlayer(Astronaut corpse)
	{
		if (activePlayers.contains(corpse))
		{
			activePlayers.remove(corpse);
			corpses.add(corpse);
		}
	}
	/**
 	* Public class called getCurrentPlayer 
 	*
 	* @return current player 
 
 	*/
	public Astronaut getCurrentPlayer()
	{
		return currentPlayer;
	}

	/**
 	* Public class called saveState which saves names
 	*
 	* @return  game Deck
 
 	*/
	public GameDeck getGameDeck()
	{
		return gameDeck;
	}
	/**
 	* Public class called SpaceDeck
 	*
 	* @return  the deck
 
 	*/
	public SpaceDeck getSpaceDeck()
	{
		return spaceDeck;
	}
	/**
 	* Public class called getGameDiscard which returns game discard pile
 	*
 	* @return return game Discard pile .
 
 	*/
	public GameDeck getGameDiscard()
	{
		return gameDiscard;
	}
	/**
 	* Public class called getSpaceDiscard with returns space discard pile
 	*
 	* @return space discard pile
 
 	*/	
	public SpaceDeck getSpaceDiscard()
	{
		return spaceDiscard;
	}
	/**
 	* Public class called saveState which saves names
 	*
 	* @param path filepath.
 
 	*/
	public void saveState(String path)
	{
		try{
			// create a file to dump the current game state to
			FileOutputStream f = new FileOutputStream(new File(path));

			// create an object output to dump our current game state to the text file
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write the game state to the text file
			o.writeObject(this);

			// close the output streams
			o.close();
			f.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
	/**
 	* Public class called loadState
 	*
 	* @param path the filepath
 	* @return  a GameEngine object representing the loaded game state
 
 	*/
	public static GameEngine loadState(String path) 
	{
		GameEngine savedGame = new GameEngine();

		try
		{
			FileInputStream f = new FileInputStream(new File(path));

			// create an object output to dump our current game state to the text file
			ObjectInputStream o = new ObjectInputStream(f);
			savedGame = (GameEngine) o.readObject();
			//persist all values
			GameEngine game = new GameEngine();
			game.random = savedGame.random;
			game.currentPlayer = savedGame.currentPlayer;
			game.gameDeck = savedGame.gameDeck;
			game.gameDiscard = savedGame.gameDiscard;
			game.spaceDeck = savedGame.spaceDeck;
			game.spaceDiscard = savedGame.spaceDiscard;
			game.activePlayers = savedGame.activePlayers;
			game.corpses = savedGame.corpses;

			//close the streams
			o.close();
			f.close();
			
			//System.out.println(activePlayers.get(0).toString());

			// close the input streams

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return savedGame;

	}
	/**
 	* Public class called travel 
 	*
 	* @param traveller traveller.
 	* @return card
 
 	*/
	public Card travel(Astronaut traveller)
	{
		/*
		if (traveller.oxygenRemaining() == 0)
		{
			throw new IllegalStateException("Can't travel with no oxygen!");
		}
		*/


		/*
		traveller.oxygenRemaining() = oxygenRemaining() -2;
		Card card = spaceDeck.draw();
		if (card.spaceDeck() != Card.GRAVITATIONAL_ANOMALY) {
			player.getTrack().add(card);
		}
		*/
		return null;
	}
   // split double oxygen to 2 single oxygen cards
	/**
 	* Public class called splitOxygen
 	*
 	* @param dbl double oxygen card
 	* @return oxygen split
 
 	*/
	public Oxygen[] splitOxygen(Oxygen dbl)
	{
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
 	* Public class called getWinner
 	*
 	* @return the winner
 
 	*/
	public Astronaut getWinner()
	{
		return getAllPlayers().get(0);
	}
	/**
 	* Public class called gameOver
 	*
 	* @return {@code true } game is over
 	*          {@code false} game continues
 
 	*/
	public boolean gameOver()
	{
		for (int i = 0; i < getFullPlayerCount(); i++)
		{
			if (getAllPlayers().get(i).hasWon())
			{
				return true;
			}
		}


		if (corpses.size() == getFullPlayerCount())
		{
			return true;
		}

		return false;
	}

}
