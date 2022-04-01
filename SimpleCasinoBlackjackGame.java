import textio.TextIO;
public class SimpleCasinoBlackjackGame {
	
	public static void main(String[] args) {
		boolean gameOutcome;
		int balance = 100, bet;
		boolean play, playAgain;
		
		System.out.println("This program will let you play a simple Casino Blackjack game.");
		System.out.println("These are the rules:");
		System.out.println("1. Ace holds 11 points, while Jack, Queen and King all hold 10 points. But in the case of two Aces, the second Ace will hold a single point.");
		System.out.println("   Note: All other cards hold their individual value.\n");
		System.out.println("2. Cards will be dealt, two each for both you and the dealer. The total value of your cards must not be more than 21.");
		System.out.println("   Once it is over 21, you have lost the game.\n");
		System.out.println("3. The general idea of the game is to get a hand of cards whose value is as close to 21 as possible without going over.\n");
		System.out.println("4. If the value of your card is not over 21, you can decide to hit or stand.");
		System.out.println("   Cards are compared with that of the dealer, the higher value wins.\n\n");
		System.out.println("Do you wish to continue?");
		play = TextIO.getlnBoolean();
		if (play) {
			Mainloop: do {
				System.out.println();
				System.out.print("Your total balance is: " + balance + " USD. ");
				System.out.print("Place your bet: ");
				
				
				while (true) {
					bet = TextIO.getlnInt();
					if (bet <= 0 || bet > balance) {
						System.out.println("Invalid bet. Please place a valid bet.");
					}
					else
						break;
					
				}
				
				gameOutcome = playGame();
				if (gameOutcome == false) {
					System.out.println("You lost.");
					balance -= bet;
					System.out.println("Your new balance is: " + balance + " USD.");
				}
				else {
					System.out.println("You won.");
					balance += bet;
					System.out.println("Your new balance is: " + balance + " USD.");
				}
				
				while (true) {
					if (balance == 0) {
						System.out.println("Insufficient balance.");
						break Mainloop;
					}
					else
						break;
				}	
				System.out.print("\n\n\n");
				System.out.print("Do you wish to play again? ");
				playAgain = TextIO.getlnBoolean();
			} while (playAgain);
			
			System.out.print("Goodbye.");
			System.exit(0);
	
		}
		else {
			System.out.println("Goodbye.");
			System.exit(0);
		}
		
		
	}//end of main().
	
	
	
	
	
	
	static boolean playGame() {
		Deck deck = new Deck();
		BlackjackHand userHand, dealerHand;
		userHand = new BlackjackHand();
		dealerHand = new BlackjackHand();
		boolean choose;
		int position = (int)(2*Math.random());
		int max = 21;
		
		deck.shuffle();
		for (int i = 0; i < 2; i++) {
			userHand.addCard(deck.dealCard());
			dealerHand.addCard(deck.dealCard());
		}
		
		if (userHand.getBlackjackValue() >= max) {
			return false;
		}
		else {
			System.out.println("The cards in your hand are:\n" + userHand.getCard(0) + "\n" + userHand.getCard(1) + "\n");
			System.out.println("One of the Dealer's cards is:\n" + dealerHand.getCard(position));
			System.out.println();
			
			Mainloop: while (true) {
				System.out.println("Do you wish to Hit or Stand?");
				System.out.println("Type \"yes\" for Hit or \"no\" for Stand.");
				choose = TextIO.getlnBoolean();
				System.out.println();
				
				if (choose == true) {
					userHand.addCard(deck.dealCard());
					if (userHand.getBlackjackValue() >= max) {
						System.out.println("Your cards value is over 21.");
						return false;
					}
					else {
						System.out.println("The value of your cards is: " + userHand.getBlackjackValue());
						continue Mainloop;
					}
				}
				else {
					
					do {
						dealerHand.addCard(deck.dealCard());
					} while (dealerHand.getBlackjackValue() <= 16);
					
					System.out.println("All the dealer's cards are:");
					for (int i = 0; i < dealerHand.getCardCount(); i++) {
						System.out.println(dealerHand.getCard(i));
					}
					
					if (dealerHand.getBlackjackValue() < max && dealerHand.getBlackjackValue() > userHand.getBlackjackValue())
						return false;
					else
						return true;
				}
			}//end of while loop.
		}//end of the first else loop.
	
		
	}//end of playGame subroutine.

}
