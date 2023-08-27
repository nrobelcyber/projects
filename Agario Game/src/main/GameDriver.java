import java.util.ArrayList;
import java.util.List;
import java.io.Console;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.Collections;

import selfish.*;
import selfish.deck.*;

//GameDriver a public class 
public class GameDriver
{
	static String[] avatars = {"👽", "🤖", "👾", "🐙", "🦖"};
	static List<Astronaut> startingOrder;

	/**
	 * A helper function to centre text in a longer String.
	 * @param width The length of the return String.
	 * @param s The text to centre.
	 * @return A longer string with the specified text centred.
	 */
	public static String centreString (int width, String s)
	{
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}

	public static String getBoard (GameEngine gameEngine)
	{
//		List<Astronaut> players = gameEngine.getAllPlayers();
		List<Astronaut> players = startingOrder;
		String board = "";

		for (int i = 0; i < players.size(); i++)
		{
			board += "🛸";
		}

		board += "\n";

		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < players.size(); j++)
			{
				if (players.get(j).distanceFromShip() > i)
				{
					board += "⬛️";
				}

				else
				{
					board += "⬜️";
				}
			}

			board += "\n";
		}

		for (int i = 0; i < players.size(); i++)
		{
			board += avatars[i];
		}

		return board;
	}

	//public GameDriver Constructor
	public GameDriver()
	{
		// Empty
	}

	public static void main(String[] args)
	{
		// Create new rng object
		Random random = new Random();
		long rng = random.nextLong();
		
		// Initialize a new GameEngine
		GameEngine gameEngine = new GameEngine(rng, "../../io/ActionCards.txt", "../../io/SpaceCards.txt");
	 
		// Read the art file
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("../../io/Art.txt"));
			String line = reader.readLine();
			while (line != null)
			{
				line = line.replaceAll("XXX", "Nathan Robel");
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Collect player names
		String answer;
		System.out.println("Enter Player names (enter 'No' to stop):");
		String name;
		StringJoiner joiner = new StringJoiner(", ", "", "");
		Scanner input = new Scanner(System.in);
		System.out.println();
		while (true)
		{
			System.out.print("Enter name: ");
			name = input.nextLine();
			if (name.equalsIgnoreCase("No"))
			{
				break;
			}
			joiner.add(name);
			gameEngine.addPlayer(name);
		}

		// Get the list of all astronauts playing the game
		gameEngine.startGame();
		List<Astronaut> players = gameEngine.getAllPlayers();
		startingOrder = new ArrayList<>(Collections.nCopies(players.size(), null));
		Collections.copy(startingOrder, players);

		// Ensure player count is between 2 and 5
		if (players.size() <= 0)
		{
			System.out.println("You need astronauts to play this game! Add at least two people...");
			System.exit(1);
		}

		if (players.size() <= 1)
		{
			System.out.println("You can't play this game by yourself! Find one more astronaut...");
			System.exit(1);
		}

		if (players.size() > 5)
		{
			System.out.println("Too many players! Only up to five Astronauts can play this game at a time...");
			System.exit(1);
		}

		// Print intro message
        String sentence = joiner.toString();
		sentence = sentence.replaceAll(", (?=[^,]*$)", " and ");
		System.out.println("After a dazzling day (but doomed) space mission, " + sentence + " are floating in space and their oxygen supplies are running low.\n");

		// PHASE ONE
		//Collections.shuffle(players); // Roll a dice to decide who goes first
		GameDeck gameDeck = gameEngine.getGameDeck();
		//gameDeck.shuffle(random);

		for (int i = 0; i < players.size(); i++)
		{
			Astronaut player = players.get(i);

			player.addToHand(new Oxygen(2));

			for (int j = 0; j < 4; j++)
			{
				player.addToHand(new Oxygen(1));

				if (gameDeck.size() <= 0)
				{
					GameDeck discard = gameEngine.getGameDiscard();
					discard.shuffle(random);

					for (int k = 0; k < discard.size(); k++)
					{
						gameDeck.add(discard.draw());
					}
					
				}

				player.addToHand(gameDeck.draw());
			}
		}

		// PHASE TWO
		System.out.println(getBoard(gameEngine) + "\n");

		int turn = 0;

		String choice;
		while (true)
		{
			System.out.println("It's your turn, " + avatars[turn] + " " + gameEngine.getCurrentPlayer().toString() + "! You have " + gameEngine.getCurrentPlayer().oxygenRemaining() + " oxygen left! What would you like to do?\n\na: Play a card\nb: Travel\nc: Breathe\n");


			turn++;
			if (turn >= players.size())
			{
				turn = 0;
			}

			System.out.print("Make a choice: ");
			choice = input.nextLine();

			if (choice.strip().equalsIgnoreCase("a"))
			{
				System.out.println(gameEngine.getCurrentPlayer().getHandStr());
			}

			if (choice.strip().equalsIgnoreCase("b"))
			{
				gameEngine.getCurrentPlayer().breathe();
				int oxygenLeft = gameEngine.getCurrentPlayer().breathe();
				gameEngine.getCurrentPlayer().addToTrack(gameEngine.getSpaceDeck().draw());

				System.out.println("\n" + avatars[turn] + " " + gameEngine.getCurrentPlayer().toString() + " takes a step forward, sacrificing their oxygen in the process. They now have " + oxygenLeft + " oxygen remaining.\n");

				gameEngine.endTurn();
				gameEngine.startTurn();
				System.out.println(getBoard(gameEngine));
			}

			if (choice.strip().equalsIgnoreCase("c"))
			{
				int oxygenLeft = gameEngine.getCurrentPlayer().breathe();

				System.out.println("\n" + avatars[turn] + " " + gameEngine.getCurrentPlayer().toString() + " takes a deep breath, they have " + oxygenLeft + " oxygen remaining.\n");

				gameEngine.endTurn();
				gameEngine.startTurn();
				System.out.println(getBoard(gameEngine));
			}
		}
	}

}
		  
		
		





