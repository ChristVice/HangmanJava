package com.Hangman;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		try {
			Hangman.GetWords();
		} catch (Exception e) {
			System.out.print("Exception: "+e);
		}
		
		Scanner usr = new Scanner(System.in);
		System.out.println("   |----+"+
						 "\n   |    |"+
				 		 "\n   |    0"+
						 "\n   |   /|\\"+
				 		 "\n   |    |"+
						 "\n   |   / \\"+
				 		 "\n   |"+
						 "\n----------");
		
		System.out.println("***WELCOME TO HANGMAN***");
		
		while(true) {
			System.out.println("\n1.Start a new game");
			System.out.println("2.Quit");
			
			int usrInput = usr.nextInt();
			
			switch(usrInput) {
				case 1 : Hangman.StartGame();
					break;
				case 2 : System.out.println("Goodbye!!");
					break;
				default : System.out.println("Invalid input"); 
			}
//			
//			if(usrInput == 1) {
//				Hangman.StartGame();
//			}
//			else if(usrInput == 2) {
//				System.out.println("Goodbye!!");
//				break;
//			}
//			else {
//				System.out.println("Invalid input");
//			}
			
		}

	}

}
