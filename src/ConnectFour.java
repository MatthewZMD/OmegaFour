/*
 * [ConnectFour.java]
 * Graphics and Computer Engineering Unit Project
 * @author Sasha Khart, Matthew Zeng and Brian Ryu
 * April-May, 2016
 * @version 1.5
*/

//Import classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

//Class
class ConnectFour {

	// Declare variables
	static int turn; // The variable that keep track of turns (-1 = player, 1 =
						// AI)
	static int round = 0; // indicates turn#
	static int roundA = 0; // indicates how many turns AI has made
	static int roundP = 0; // indicates how many turns player has made
	static int[][] board = new int[6][7];
	static double startTimeA = 0;
	static double endTimeA = 0;
	static double avgTimeA = 0;
	static double startTimeP = 0;
	static double endTimeP = 0;
	static double avgTimeP = 0; // variables for use with timing moves

	// Declare windows
	static JFrame window0;
	static JFrame window1;
	static JFrame window2;
	static JFrame window3;
	static JFrame window4;
	static JFrame window5;

	// Declare Labels (game window)
	static JLabel move, label00, label01, label02, label03, label04, label05, label06, label07, winLabel;

	// Declare buttons

	/// Menu buttons
	static JButton playButton;
	static JButton rulesButton;
	static JButton creditsButton;

	static JButton backButtonRules;
	static JButton backButtonCredits;

	static JButton playerButton;
	static JButton AIButton;

	static JButton againButton;

	/// Game buttons
	static JButton dropButton0;
	static JButton dropButton1;
	static JButton dropButton2;
	static JButton dropButton3;
	static JButton dropButton4;
	static JButton dropButton5;
	static JButton dropButton6;
	static JButton endGame;

	// Game board panel
	static graphicsPanel gameBoard;

	// Main method
	public static void main(String args[]) {

		// *******************************************************************************************************************
		// WINDOW CREATION
		// *******************************************************************************************************************

		// Window 0 - "Main Menu"

		// Create window
		window0 = new JFrame("Connect4 - Main Menu");
		window0.setSize(700, 500);
		window0.setResizable(false);
		window0.setLayout(new GridLayout(2, 1));
		window0.setLocationRelativeTo(null);
		window0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create panels
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.yellow);
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBackground(Color.yellow);

		// Create icon
		ImageIcon titleIcon = new ImageIcon("Title.JPG");

		// Create components
		JLabel titleLabel = new JLabel(titleIcon, JLabel.CENTER);
		playButton = new JButton("PLAY");
		playButton.addActionListener(new playButtonListener());
		rulesButton = new JButton("RULES");
		rulesButton.addActionListener(new rulesButtonListener());
		creditsButton = new JButton("CREDITS");
		creditsButton.addActionListener(new creditsButtonListener());

		// Assemble window
		titlePanel.add(titleLabel);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(playButton);
		rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(rulesButton);
		creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(creditsButton);
		window0.add(titlePanel);
		window0.add(controlPanel);

		// Set window visibility
		window0.setVisible(true);

		// Window 1 - "Rules"

		// Create window
		window1 = new JFrame("Connect4 - Rules");
		window1.setSize(700, 500);
		window1.setResizable(false);
		window1.setLayout(new BorderLayout());

		// Create panels
		JPanel toolbar1 = new JPanel();
		toolbar1.setBackground(Color.yellow);
		JPanel rulePanel = new JPanel();
		rulePanel.setBackground(Color.yellow);

		// Create icon
		ImageIcon ruleIcon = new ImageIcon("Rules.JPG");

		// Create components
		backButtonRules = new JButton("Back to Main Menu");
		backButtonRules.addActionListener(new backButtonRulesListener());
		JLabel ruleLabel = new JLabel(ruleIcon, JLabel.CENTER);

		// Assemble window
		toolbar1.add(backButtonRules);
		rulePanel.add(ruleLabel);
		window1.add(rulePanel, BorderLayout.CENTER);
		window1.add(toolbar1, BorderLayout.SOUTH);

		// Set window visibility
		window1.setVisible(false);

		// Window 2 - "Credits"

		// Create window
		window2 = new JFrame("Connect4 - Credits");
		window2.setSize(700, 500);
		window2.setResizable(false);
		window2.setLayout(new BorderLayout());

		// Create panels
		JPanel toolbar2 = new JPanel();
		toolbar2.setBackground(Color.yellow);
		JPanel creditsPanel = new JPanel();
		creditsPanel.setBackground(Color.yellow);

		// Create icon
		ImageIcon creditIcon = new ImageIcon("Credits.JPG");

		// Create components
		backButtonCredits = new JButton("Back to Main Menu");
		backButtonCredits.addActionListener(new backButtonCreditsListener());
		JLabel creditsLabel = new JLabel(creditIcon, JLabel.CENTER);

		// Assemble window
		toolbar2.add(backButtonCredits);
		creditsPanel.add(creditsLabel);
		window2.add(creditsPanel, BorderLayout.CENTER);
		window2.add(toolbar2, BorderLayout.SOUTH);

		// Set window visibility
		window2.setVisible(false);

		// Window 3 - "Who Starts?"

		// Create window
		window3 = new JFrame("Connect4 - Who Starts?");
		window3.setSize(700, 500);
		window3.setResizable(false);
		window3.setLayout(new GridLayout(2, 1));

		// Create panels
		JPanel whoStartsPanel = new JPanel();
		whoStartsPanel.setBackground(Color.yellow);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(Color.yellow);

		// Create icon
		ImageIcon whoStartsIcon = new ImageIcon("WhoStarts.JPG");
		ImageIcon playerButtonIcon = new ImageIcon("playerButton.jpg");
		ImageIcon AIButtonIcon = new ImageIcon("AIButton.jpg");

		// Create components
		playerButton = new JButton(playerButtonIcon);
		playerButton.addActionListener(new playerButtonListener());
		AIButton = new JButton(AIButtonIcon);
		AIButton.addActionListener(new AIButtonListener());
		JLabel whoStartsLabel = new JLabel(whoStartsIcon, JLabel.CENTER);

		// Assemble window
		buttonPanel.add(playerButton);
		buttonPanel.add(AIButton);
		whoStartsPanel.add(whoStartsLabel);
		window3.add(whoStartsPanel);
		window3.add(buttonPanel);

		// Set window visibility
		window3.setVisible(false);

		// Window 4 - "Game"

		// Create window
		window4 = new JFrame("Connect 4 - Game");
		window4.setSize(1200, 700);
		window4.setResizable(false);

		// Create panels
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(630, 650));

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(450, 650));
		rightPanel.setLayout(new GridLayout(12, 1));
		rightPanel.setBackground(Color.yellow);

		gameBoard = new graphicsPanel();
		gameBoard.setPreferredSize(new Dimension(630, 600));
		gameBoard.setBackground(Color.yellow);

		JPanel dropPanel = new JPanel();
		dropPanel.setLayout(new FlowLayout());
		dropPanel.setPreferredSize(new Dimension(630, 50));
		dropPanel.setBackground(Color.yellow);

		// Create components left panel
		dropButton0 = new JButton("DROP");
		dropButton0.setPreferredSize(new Dimension(85, 40));
		dropButton0.addActionListener(new dropButton0Listener());
		dropButton1 = new JButton("DROP");
		dropButton1.setPreferredSize(new Dimension(85, 40));
		dropButton1.addActionListener(new dropButton1Listener());
		dropButton2 = new JButton("DROP");
		dropButton2.setPreferredSize(new Dimension(85, 40));
		dropButton2.addActionListener(new dropButton2Listener());
		dropButton3 = new JButton("DROP");
		dropButton3.setPreferredSize(new Dimension(85, 40));
		dropButton3.addActionListener(new dropButton3Listener());
		dropButton4 = new JButton("DROP");
		dropButton4.setPreferredSize(new Dimension(85, 40));
		dropButton4.addActionListener(new dropButton4Listener());
		dropButton5 = new JButton("DROP");
		dropButton5.setPreferredSize(new Dimension(85, 40));
		dropButton5.addActionListener(new dropButton5Listener());
		dropButton6 = new JButton("DROP");
		dropButton6.setPreferredSize(new Dimension(85, 40));
		dropButton6.addActionListener(new dropButton6Listener());

		// create components right panel
		label00 = new JLabel("Calculated Times (in seconds for player, milliseconds for Omega Four): ");
		label01 = new JLabel("Player's last move time: 0 seconds");
		label02 = new JLabel("Player's average move time: 0 seconds");
		label03 = new JLabel("Omega Four's last move time: 0 milliseconds");
		label04 = new JLabel("Omega Four's average move time: 0 milliseconds");
		label05 = new JLabel("Move Number: " + round);
		label06 = new JLabel("Current player's move number: " + roundP);
		label07 = new JLabel("Current Omega Four's move number: " + roundA);
		move = new JLabel(); // is changed depending on who's turn it is
		winLabel = new JLabel(); // text added when there is a win or draw
		endGame = new JButton("Quit");
		endGame.addActionListener(new endGameListener());
		endGame.setEnabled(true); // deactivated unitl there is a win or draw

		// Assemble window
		dropPanel.add(dropButton0);
		dropPanel.add(dropButton1);
		dropPanel.add(dropButton2);
		dropPanel.add(dropButton3);
		dropPanel.add(dropButton4);
		dropPanel.add(dropButton5);
		dropPanel.add(dropButton6);

		leftPanel.add(dropPanel);
		leftPanel.add(gameBoard);

		rightPanel.add(move);
		rightPanel.add(label05);
		rightPanel.add(label06);
		rightPanel.add(label07);
		rightPanel.add(label00);
		rightPanel.add(label01);
		rightPanel.add(label02);
		rightPanel.add(label03);
		rightPanel.add(label04);
		rightPanel.add(winLabel);
		rightPanel.add(endGame);

		masterPanel.add(leftPanel);
		masterPanel.add(rightPanel);

		window4.add(masterPanel);

		// Set window visibility
		window4.setVisible(false);

		// Turn off buttons
		turnOffDrop();

	} // End of main method

	// *******************************************************************************************************************
	// TURN ON DROP METHOD
	// *******************************************************************************************************************
	public static void turnOnDrop() {
		if (board[5][0] == 0) {
			dropButton0.setEnabled(true);
		}
		if (board[5][1] == 0) {
			dropButton1.setEnabled(true);
		}
		if (board[5][2] == 0) {
			dropButton2.setEnabled(true);
		}
		if (board[5][3] == 0) {
			dropButton3.setEnabled(true);
		}
		if (board[5][4] == 0) {
			dropButton4.setEnabled(true);
		}
		if (board[5][5] == 0) {
			dropButton5.setEnabled(true);
		}
		if (board[5][6] == 0) {
			dropButton6.setEnabled(true);
		}
	}

	// *******************************************************************************************************************
	// TURN OFF DROP METHOD
	// *******************************************************************************************************************
	public static void turnOffDrop() {
		dropButton0.setEnabled(false);
		dropButton1.setEnabled(false);
		dropButton2.setEnabled(false);
		dropButton3.setEnabled(false);
		dropButton4.setEnabled(false);
		dropButton5.setEnabled(false);
		dropButton6.setEnabled(false);
	}

	// *******************************************************************************************************************
	// MAIN GAME METHOD
	// *******************************************************************************************************************
	public static void mainGame() {
		// local variables
		int win = 0;
		boolean draw = false;
		int x = 0;
		int y = 0;
		boolean dropped = false;

		// initialize board array if new game
		if (round == 1) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					board[i][j] = 0;
				}
			}
			label01.setText("Player's last move time: 0 seconds");
			label02.setText("Player's average move time: 0 seconds");
			label03.setText("Omega Four's last move time: 0 milliseconds");
			label04.setText("Omega Four's average move time: 0 milliseconds");
			window4.repaint(); // Repaint window
			avgTimeA = 0;
			avgTimeP = 0;
		}

		// ---TURNS---//
		System.out.println("New Turn: " + turn);
		if (turn == -1) { // ---------------------------------------------------------------player
			turn = 1;
			roundP++;
			System.out.println("RoundP: " + roundP);
			label06.setText("Current player's move number: " + roundP);
			move.setText("Player's Turn");
			label05.setText("Move Number: " + round); // Update move number
			startTimeP = System.currentTimeMillis(); // turn on timer for player
			turnOnDrop(); // activate buttons and wait for player to make
							// decision

		} else if (turn == 1) { // -----------------------------------------------------------Omega
								// Four
			turn = -1;
			roundA++;
			label07.setText("Current Omega Four's move number: " + roundA);
			System.out.println("RoundA: " + roundA);
			move.setText("Omega Four's Turn");
			label05.setText("Move Number: " + round); // Update move number
			startTimeA = System.currentTimeMillis(); // begin timer
			x = omega4(); // get the AI's choice of column
			endTimeA = System.currentTimeMillis(); // end timer

			for (int i = 0; i < 6; i++) { // update board
				if (board[i][x] == 0 && !dropped) {
					board[i][x] = 1;
					y = i;
					dropped = true;
				}
			}
			window4.repaint(); // update window
			avgTimeA += endTimeA - startTimeA; // calculate and display last and
												// avg time (milliseconds)
			label03.setText("Omega Four's last move time: " + (double) (endTimeA - startTimeA) + " milliseconds");
			label04.setText("Omega Four's average move time: " + avgTimeA / roundA + " milliseconds");

			// Perform win/tie checks
			win = winCheck(board, y, x, 1);
			System.out.println(win + "(A)");
			draw = drawCheck();
			System.out.println(draw + "(A)");

			// If there is a win or a tie, redirect to gameEnd window
			if (win == 1) {
				winLabel.setText("Better Luck Next Time Player. You Lose.");
				label05.setText("Total Moves Made: " + round); // Update moves
																// made
				endGame.setText("Back to Main Menu");
			} else if (draw == true) {
				winLabel.setText("Draw. Good Game.");
				label05.setText("Total Moves Made: " + round); // Update moves
																// made
				endGame.setText("Back to Main Menu");
			} else { // If not win/tie, allow player to go
				round++;
				label05.setText("Move Number: " + round); // Update move number
				//from 'current' to 'last' move
				label07.setText("Last Omega Four's move number: " + roundA);
				mainGame();
			}
		}

	}

	// *******************************************************************************************************************
	// PLAYER DROP METHOD
	// *******************************************************************************************************************
	public static void playerDrop(int column) {
		endTimeP = System.currentTimeMillis(); // Stop timer for player
		// Declare local variables
		boolean dropped = false;
		int win = 0;
		boolean draw = false;
		int x = 0;

		// Turn off drop buttons
		turnOffDrop();

		// Place counter in appropriate place (update array)
		for (int i = 0; i < 6; i++) {
			if (board[i][column] == 0 && !dropped) {
				board[i][column] = -1;
				dropped = true;
				x = i;
			}
		}
		window4.repaint(); // Repaint window

		//calculate and display last and avg time (seconds)
		avgTimeP += ((endTimeP - startTimeP) / 1000);
		label01.setText("Player's last move time: " + ((endTimeP - startTimeP) / 1000) + " seconds");
		label02.setText("Player's average move time: " + (avgTimeP / roundP) + " seconds");
		round++;
		label05.setText("Move Number: " + round); // Update move number

		// Perform win/tie checks
		win = winCheck(board, x, column, -1);
		System.out.println(win + "(P)");
		draw = drawCheck();
		System.out.println(draw + "(P)");

		// If there is a win or a tie, redirect to gameEnd window
		if (win == 1) {
			winLabel.setText("Congradulations Player. You Win.");
			label05.setText("Total Moves Made: " + round); // Update moves made
			endGame.setText("Back to Main Menu");
		} else if (draw == true) {
			winLabel.setText("Draw. Good Game.");
			label05.setText("Total Moves Made: " + round); // Update moves made
			endGame.setText("Back to Main Menu");
		} else { // If not win/tie, allow AI to go
			label06.setText("Last player's move number: " + roundP);
			mainGame();
		}
	}

	// *******************************************************************************************************************
	// AI METHOD
	// *******************************************************************************************************************
	//Matthew Zeng
	//This is the omegaFour AI Program
	public static int omega4(){
		int[]choices = basicChoice();
		int[]sortedChoice = bubbleSort(choices.clone());
		for(int j = 0;j<7;j++){
			if(choices[j]==sortedChoice[0]){
				return j;
			}
		}
		
		return 0;
	}

//	public static int minimax(int j, int player, int depth) {
//		if (depth == 0) {
//			for (int J = 0; J < 7; J++) {
//				for (int I = 0; I < 6; I++) {
//					if (board[I][J] != 0) {
//						if (winCheck(board, I, J, board[I][J]) == 1) {
//							if (board[I][J] == 1) {
//								return 10000;
//							}
//						} else if (winCheck(board, I, J, board[I][J]) == 1) {
//							if (board[I][J] == -1) {
//								return -10000;
//							}
//						}
//					}
//				}
//			}
//		}
//		int compare;
//		if (player == -1) {
//			compare = 10000;
//			for (int row = 0; row < 7; row++) {
//				for (int col = 0; col < 6; col++) {
//					if (board[col][row] == 0) {
//						if (choose(j, player)) {
//							compare = Math.min(compare,
//									minimax(row, -player, depth - 1));
//							board[col][row] = 0;
//						}
//					}
//				}
//			}
//		} else {
//			compare = -10000;
//			for (int row = 0; row < 7; row++) {
//				for (int col = 0; col < 6; col++) {
//					if (board[col][row] == 0) {
//						if (choose(j, player)) {
//							compare = Math.max(compare,
//									minimax(row, -player, depth - 1));
//							board[col][row] = 0;
//						}
//					}
//				}
//			}
//		}
//
//		return compare;
//	}

	// Matthew Zeng
	// This is the BACKUP method that explicitly counts the importance/necessity of putting a piece at a
	// specified place
	// the higher the count, the more likely a piece will be put there
	// Basic Selection - Selecting from individual pieces with very limited view
	// of the entire game
	// Then put the necessity in order and choose the highest one
	public static int[] basicChoice(){
		int[][]board2 = duplicateBoard();
		int[]choice = new int[7];
		for(int j = 0;j<7;j++){
			choice[j] = 0;
			for(int i = 0;i<6;i++){
				if(board2[i][j]==0){
					choice[j] = basicSelection(board2,i,j,1);
					i = 6;
				}else{
					choice[j] = -100;
				}
			}
		}
		display();
		System.out.println(Arrays.toString(choice));
		return choice;
	}
	public static int basicSelection(int[][] board2, int i, int j, int player) {
		// Count the importance of putting a piece
		int count = 0;
		// Column 3,4,5 have higher chance in first 5 rounds
		if (round < 10 && j > 1 && j < 5) {
			if (j == 3) //Column 4 will have the highest chance
				count += 3;
			else count++;
		}
		// If the player puts a piece and it wins, definitely put the piece
		// there
		if (winCheck(board2, i, j, player) == 1) count += 200;
		board[i][j] = 0;
		// If the player puts a piece that increases the chance of winning,
		// count+=10
		if (winCheck(board2, i, j, player) > 1) count += 5;
		board[i][j] = 0;
		// Simulate if the opponent puts a piece at that place, if the opponent
		// wins, count+=100
		if (winCheck(board2, i, j, -player) == 1) count += 100;
		board[i][j] = 0;
		// Simulate if the opponent puts a piece at that place,
		// if the opponent increases the chance of winning, count+=10
		if (winCheck(board2, i, j, -player) > 1) count += 5;
		board[i][j] = 0;

		// The AI should be more aggressive by stacking a 3 which
		// forces the opponent to put a piece on top
		if (check(board, i - 1, j, 0) == check(board, i - 2, j, 0) && check(board, i + 1, j, 0) != 5) {
			if (check(board, i - 1, j, 0) == player) count += 7;
			else if (check(board, i - 1, j, 0) == -player) count += 3;
		}

		//If there is a piece at bottom left and bottom left
		if (check(board, i - 1, j - 1, 0) == check(board, i - 2, j - 2, 0) && check(board, i - 1, j - 1, 0) != 5) {
			if (check(board, i - 1, j - 1, 0) == player) count += 10;
			else count += 5;
		} else if (check(board, i - 1, j + 1, 0) == check(board, i - 2, j + 2, 0) && check(board, i - 1, j + 1, 0) != 5) {
			if (check(board, i - 1, j + 1, 0) == player) count += 10;
			else count += 5;
		}

		if (check(board, i + 1, j + 1, 0) == check(board, i - 1, j - 1, 0) && check(board, i - 1, j - 1, 0) != 5 && check(board, i - 1, j - 1, 0) != 0) {
			if (check(board, i + 2, j + 2, 0) == 0 || check(board, i - 2, j - 2, 0) == 0) count += 20;
			else count += 5;
		} else if (check(board, i - 1, j + 1, 0) == check(board, i + 1, j - 1, 0) && check(board, i - 1, j + 1, 0) != 5 && check(board, i - 1, j + 1, 0) != 0) {
			if (check(board, i + 2, j + 2, 0) == 0 || check(board, i - 2, j - 2, 0) == 0) count += 20;
			else count += 5;
		}

		if (check(board2, i + 1, j, 0) != 5) {
			// If the player put a piece there
			if (choose(j, 2)) {
				// If the opponent stack a piece on top and wins, count-=50
				if (winCheck(board2, i + 1, j, -player) == 1) count -= 50;
				board[i + 1][j] = 0;
				// If the player then stack a piece on top
				if (winCheck(board2, i + 1, j, player) == 1) {
					// If that is not intentionally done, count-=20
					if (check(board, i - 1, j, 0) != check(board, i - 2, j, 0) && check(board, i - 1, j, 0) != 2) count -= 20;
				}
				board[i + 1][j] = 0;
			}
			board[i][j] = 0;
		}

		int right = check(board, i, j + 1, 0); // The piece at the right
		if (right == 0 && check(board, i - 1, j + 1, 0) != 0) // If there is nothing at the right
			count++; // Increase importance by 1
		else if (right != 0 && right != 5) { // If the right piece is not 0 and it's not end
			// If the right piece equal to second pieces to the right
			if (right == check(board, i, j + 2, 0)) {
				if (check(board, i, j - 1, 0) == 0) count += 10;
			}
		}

		// If higher-right piece is same as player, increase by 1
		if (check(board, i + 1, j + 1, 0) == player) count++;
		if (check(board, i + 1, j + 1, 0) == check(board, i + 2, j + 2, 0)) {
			if (check(board, i + 1, j + 1, 0) != 0 && check(board, i + 1, j + 1, 0) != 5) {
				if (check(board, i + 2, j + 3, 0) != 0 && check(board, i - 1, j - 1, 0) == 0) count += 10;
				count += 5;
			}
		}
		// If lower-right piece is same as player, increase by 1
		if (check(board, i - 1, j + 1, 0) == player) count++;
		if (check(board, i - 1, j + 1, 0) == check(board, i - 2, j + 2, 0)) {
			if (check(board, i - 1, j + 1, 0) != 0 && check(board, i - 1, j + 1, 0) != 5) {
				if (check(board, i - 4, j + 3, 0) != 0 && check(board, i + 1, j - 1, 0) == 0) count += 10;
				else count += 5;
			}
		}

		int left = check(board, i, j - 1, 0);
		if (left == 0 && check(board, i - 1, j - 1, 0) != 0) count++;
		else if (left != 0 && left != 5) {
			if (left == check(board, i, j - 2, 0)) {
				if (check(board, i, j + 1, 0) == 0) count += 10;
			}
		}

		// If higher-left piece is same as player, increase by 1
		if (check(board, i + 1, j - 1, 0) == player) count++;
		if (check(board, i + 1, j - 1, 0) == check(board, i + 2, j - 2, 0)) {
			if (check(board, i + 1, j - 1, 0) != 0 && check(board, i + 1, j - 1, 0) != 5) {
				if (check(board, i + 2, j - 3, 0) != 0 && check(board, i - 1, j + 1, 0) == 0) count += 10;
				else count += 5;
			}
		}
		// If lower-left piece is same as player, increase by 1
		if (check(board, i - 1, j - 1, 0) == player) count++;
		if (check(board, i - 1, j - 1, 0) == check(board, i - 2, j - 2, 0)) {
			if (check(board, i - 1, j - 1, 0) != 0 && check(board, i - 1, j - 1, 0) != 5) {
				if (check(board, i - 4, j + 3, 0) != 0 && check(board, i + 1, j + 1, 0) == 0) count += 10;
				else count += 5;
			}
		}

		// If there are pieces left and right
		if (left == right && (right == 1 || right == -1)) {
			if (check(board, i, j - 2, 0) == 0 || check(board, i, j + 2, 0) == 0) count += 10;
			else count += 5;
		}

		//Set up & block traps
		count += trap(board2, i, j);

		return count;
	}

	//Matthew Zeng
	//This method sets up and blocks the traps
	public static int trap(int[][] simB, int i, int j) {
		int count = 0;
		int player;
		boolean even = (i + 1) % 2 == 0;
		int[][] oriB = duplicateBoard();
		boolean aiTurn;

		for (int start = 0; start < 2; start++) {
			// AI stacks first, will stack vertically to simulate setting a trap
			if (start == 0)
				aiTurn = true;
			else // Human stacks second, to simulate blocking a trap
				aiTurn = false;
			for (int stack = i; stack < 6; stack++) {
				//Stacking
				boolean end = false;
				if (aiTurn) { //If it's ai's turn
					//Changes Turns
					player = 1;
					if (choose(j, player)) {
						aiTurn = false;
					} else
						stack = 6;
				} else { //If it's simulating player's turn
					//Changes turns
					player = -1;
					if (choose(j, player)) {
						aiTurn = true;
					} else
						stack = 6;
				}

				// Check for win
				for (int J = Math.max(0, j - 3); J < Math.min(7, j + 3); J++) {
					for (int I = 0; I < 6; I++) {
						if (!end) {
							if (board[I][J] == 0) {
								//If there is a win and also a win on the top
								if (winCheck(board, I, J, player) == 1) {
									if (check(board, I + 1, J, 0) != 5) {
										if (winCheck(board, I + 1, J, player) == 1) {
											count += 10; //The necessity to put a piece there increase
											end = true; //Set end to true to exit the if
										}
										//Restore the piece selected
										board[I + 1][J] = 0;
									}
								}
								board[I][J] = 0;
								//Set I to 6 to exit the for loop
								I = 6;
							}
						}
					}
				}
			}
			//Restore to the original board
			restore(oriB);
			//Duplicate the board again
			simB = duplicateBoard();
		}
		//Return the necessity
		return count;

	}

	//Matthew Zeng
	//This method restores the board state by getting a copy of the board
	public static void restore(int[][] originalCopy) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = originalCopy[i][j]; //Set to the original states
			}
		}
	}
	
	// Matthew Zeng
	// Display the board on console
	public static void display() {
		System.out.println("Board: ");
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == -1)
					System.out.print("H ");
				else if (board[i][j] == 1)
					System.out.print("A ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Matthew Zeng
	// Duplicate the game board
	public static int[][] duplicateBoard() {
		int[][] dupliBoard = new int[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				//Set everything from the previous board to the new board
				dupliBoard[i][j] = board[i][j];
			}
		}
		return dupliBoard;
	}

	// Matthew Zeng
	// Bubble sort algorithm
	public static int[] bubbleSort(int[] arr) {
		int ph;
		int counter;
		do {
			counter = 0;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i + 1] > arr[i]) {
					ph = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = ph;
					counter++;
				}
			}
		} while (counter > 0);
		return arr;
	}

	// Matthew Zeng
	// Check for draw
	public static boolean drawCheck() {
		int count = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] != 0)
					count++;
			}
		}
		if (count == 42)
			return true;
		else
			return false;
	}

	// Matthew Zeng
	public static boolean choose(int j, int player) {
		for (int i = 0; i < 6; i++) {
			if (i == 5 && board[i][j] != 0)
				return false;
			else if (board[i][j] == 0) {
				board[i][j] = player;
				// win = winCheck(board,i,j,board[i][j])==1;
				return true;
			}
		}
		return true;
	}

	// ---WinCheck Method---//
	public static int winCheck(int[][] gameBoard, int i, int j, int player) {
		board[i][j] = player;
		for (int t = 0; t < 4; t++) {
			int count = 1;
			boolean first = true;
			boolean second = true;
			for (int o = 0; o < 3; o++) {
				if (t == 0) {
					if (first && board[i][j] == check(gameBoard, i + 1 + o, j, 0))
						count++;
					else
						first = false;
					if (second && board[i][j] == check(gameBoard, i - 1 - o, j, 0))
						count++;
					else
						second = false;
				} else if (t == 1) {
					if (first && board[i][j] == check(gameBoard, i + 1 + o, j + 1 + o, 0))
						count++;
					else
						first = false;
					if (second && board[i][j] == check(gameBoard, i - 1 - o, j - 1 - o, 0))
						count++;
					else
						second = false;
				} else if (t == 2) {
					if (first && board[i][j] == check(gameBoard, i, j + 1 + o, 0))
						count++;
					else
						first = false;
					if (second && board[i][j] == check(gameBoard, i, j - 1 - o, 0))
						count++;
					else
						second = false;
				} else if (t == 3) {
					if (first && board[i][j] == check(gameBoard, i - 1 - o, j + 1 + o, 0))
						count++;
					else
						first = false;
					if (second && board[i][j] == check(gameBoard, i + 1 + o, j - 1 - o, 0))
						count++;
					else
						second = false;
				}
			}
			if (count >= 4) {
				return 1;
			} else if (t == 3 && count >= 2) {
				return count;
			}
		}

		return 0;
	}
	
	// Check method
	public static int check(int[][] boarD, int i, int j, int player) {
		if (i < 0 || i > 5 || j < 0 || j > 6)
			return 5;
		else if (player == 0)
			return boarD[i][j];
		else
			return 0;
	}

	// *******************************************************************************************************************
	// GRAPHICS PANEL
	// *******************************************************************************************************************
	static class graphicsPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Paint board
			g.setColor(Color.BLUE);
			g.fillRect(27, 0, 630, 540);

			// Paint counters
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[j][i] == -1) {
						g.setColor(Color.RED);
						g.fillOval(90 * i + 32, 90 * (5 - j) + 5, 80, 80);
					} else if (board[j][i] == 1) {
						g.setColor(Color.YELLOW);
						g.fillOval(90 * i + 32, 90 * (5 - j) + 5, 80, 80);
					} else {
						g.setColor(Color.WHITE);
						g.fillOval(90 * i + 32, 90 * (5 - j) + 5, 80, 80);
					}
				}
			}

		}
	}

	// *******************************************************************************************************************
	// BUTTON LISTENERS
	// *******************************************************************************************************************

	// Play Button Listener
	static class playButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window3 position at window0
			window3.setLocationRelativeTo(window0);

			// Deactivate window0
			window0.setVisible(false);

			// Activate window1
			window3.setVisible(true);

		}
	}

	// Rules Button Listener
	static class rulesButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window1 position at window0
			window1.setLocationRelativeTo(window0);

			// Deactivate window0
			window0.setVisible(false);

			// Activate window1
			window1.setVisible(true);

		}
	}

	// Credits Button Listener
	static class creditsButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window2 position at window0
			window2.setLocationRelativeTo(window0);

			// Deactivate window0
			window0.setVisible(false);

			// Activate window2
			window2.setVisible(true);

		}
	}

	// Back Button (Rules) Listener
	static class backButtonRulesListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window0 position at window1
			window0.setLocationRelativeTo(window1);

			// Deactivate window1
			window1.setVisible(false);

			// Activate window0
			window0.setVisible(true);

		}
	}

	// Back Button (Credits) Listener
	static class backButtonCreditsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window0 position at window1
			window0.setLocationRelativeTo(window2);

			// Deactivate window2
			window2.setVisible(false);

			// Activate window0
			window0.setVisible(true);

		}
	}

	// Player Button Listener
	static class playerButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window04 position at window3
			window4.setLocationRelativeTo(window3);

			// Deactivate window2
			window3.setVisible(false);

			// Activate window0
			window4.setVisible(true);

			// Set turn and round variable
			turn = -1;
			round = 1;
			System.out.println("New Game");

			// redirect to mainGame method
			mainGame();

		}
	}

	// AI Button Listener
	static class AIButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Set window04 position at window3
			window4.setLocationRelativeTo(window3);

			// Deactivate window2
			window3.setVisible(false);

			// Activate window0
			window4.setVisible(true);

			// Set turn and round variable
			turn = 1;
			round = 1;
			System.out.println("New Game");

			// redirect to mainGame method
			mainGame();

		}
	}

	// Drop Button 0 Listener
	static class dropButton0Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(0);
		}
	}

	// Drop Button 1 Listener
	static class dropButton1Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(1);
		}
	}

	// Drop Button 2 Listener
	static class dropButton2Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(2);
		}
	}

	// Drop Button 3 Listener
	static class dropButton3Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(3);
		}
	}

	// Drop Button 4 Listener
	static class dropButton4Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(4);
		}
	}

	// Drop Button 5 Listener
	static class dropButton5Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(5);
		}
	}

	// Drop Button 6 Listener
	static class dropButton6Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			playerDrop(6);
		}
	}

	// endGame listener
	static class endGameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			window4.setVisible(false); // Deactivate window4
			window0.setVisible(true); // Activate window0
			round = 0; // reset variables
			roundA = 0;
			roundP = 0;
			startTimeA = 0;
			endTimeA = 0;
			avgTimeA = 0;
			startTimeP = 0;
			endTimeP = 0;
			avgTimeP = 0;
			System.out.println("End Game");
			winLabel.setText("");
			endGame.setText("Quit");
			// window4.repaint();
		}
	}

} // End of class