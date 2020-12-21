package com.Hangman;
/*
Hangman in console window class


	|----+
	|    |
	|    0
	|   /|\
	|    |
	|   / \
	|
----------

 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Hangman {
	
	public static ArrayList<String> words = new ArrayList<String>();

	//Start the game
	public static void StartGame() {
		Scanner game = new Scanner(System.in);
		String word = GetRandomWrd().toLowerCase();
		
		List<Character> guesses = new ArrayList<Character>(); //keep track of guesses
		List<Character> wrongGuesses = new ArrayList<Character>(); //keep track of wrong guesses
		List<HashMap<Character, Character>> wordList = new ArrayList<HashMap<Character,Character>>(word.length()); //set letter in word to '_'
		
		for(int i=0; i<word.length(); ++i) {
			HashMap<Character, Character> emptySlots = new HashMap<Character, Character>();
			emptySlots.put(word.charAt(i), '_');
			wordList.add(emptySlots);
		}
		
		String[] emptyHangman = {"   +----+",
								 "   |    |",
						 		 "   |",		// i = 2 , head
								 "   |",		//arms
						 		 "   |",		//body
								 "   |",		//legs
						 		 "   |",
								 "----------"};
		
		String[] draw = {"   |    0",
					     "   |    |",
						 "   |   /|", 
						 "   |   /|\\",
						 "   |    |",
						 "   |   /",
						 "   |   / \\"};
		
		int errors = 0;
		int filled = 1; //set this to anything above 0
		boolean solved = false;
		while(!solved) {
			
			for(String e : emptyHangman) //print empty hang
				System.out.println(e);
			
			System.out.println();
			if(errors == draw.length) {
				System.out.println("You loose!!");
				System.out.println("Word :: "+word+"\nGame Over");
				solved = true;
			}
			else if(filled < 1) {
				System.out.println("Word :: "+word);
				System.out.println("Congratulations, you guessed it");
				solved = true;
			}
			else {
				
				for(int i=0; i<word.length(); ++i) //print out the slots
					System.out.print(wordList.get(i).get(word.charAt(i)) + " ");
				
				System.out.println("\nWrong Guesses "+wrongGuesses); //print out guesses made
				
				System.out.println("Enter letter guess :: "); //print out user guess
				String letter = game.next();//game.next().charAt(0);
				
				if(!word.contains(letter.toLowerCase()) || guesses.contains(letter.charAt(0))) {
					wrongGuesses.add(letter.charAt(0));
					
					//redraw hangman
					if(errors == 0)
						emptyHangman[2] = draw[errors];
					else if(0 < errors && errors < 4)
						emptyHangman[3] = draw[errors];
					else if(errors == 4)
						emptyHangman[4] = draw[errors];
					else if(4 < errors && errors < 7)
						emptyHangman[5] = draw[errors];
					
					errors++;
				}
				else {
					guesses.add(letter.charAt(0));
					for(HashMap<Character, Character> e : wordList) {
						if(e.containsValue('_') && e.containsKey(letter.charAt(0))) {
								e.replace(letter.charAt(0), letter.charAt(0));
						}
					}
				}
			
				int n = 0;
				for(HashMap<Character, Character> e : wordList) {
					if(e.containsValue('_'))
						n++;
				}
				filled = n;
				
			}
			
		}
		game.close();
		
	}
	
	public static void GetWords() throws FileNotFoundException {
		Scanner wordsFile = new Scanner(new File("src/EnglishWords.txt")); //extract the file
		
		while(wordsFile.hasNextLine()) {
			String line = wordsFile.nextLine();
			words.add(line);
		}
		
	}
	
	public static String GetRandomWrd() {
		return words.get( (int)(Math.random()*words.size()) );
	}
	
}
