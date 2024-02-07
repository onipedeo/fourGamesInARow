package com.bptn.course.week2.fourInARowGame;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.bptn.course.week2.fourInARowGame.exceptions.ColumnFullException;
import com.bptn.course.week2.fourInARowGame.exceptions.InvalidMoveException;

class GameTimer {
	private Timer timer;
	private int timeLimitInSecs;
	private boolean timeUp;

	public GameTimer(int timeLimitInSecs) {
		this.timer = new Timer(true);
		this.timeLimitInSecs = timeLimitInSecs;
		this.timeUp = false;
	}

	public void start() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timeUp = true;
				timer.cancel(); // Stop the timer when time is up
				System.out.println("Time is up!");
				System.exit(0);
			}
		}, timeLimitInSecs * 1000); // Convert seconds to milliseconds
	}

	public boolean isTimeUp() {
		return timeUp;
	}
}

public class Game {
	private Player[] players;
	private Board board;
	private static Scanner scanner = new Scanner(System.in);

	public Game() {
		// Let's default it two players for now. Later, you can improve upon this to
		// allow the game creator to choose how many players are involved.
		this.players = new Player[2];// complete line.
		this.board = new Board();// complete line

	}

	public void setUpGame() {
		System.out.println("Enter player 1's name: ");
		players[0] = new Player(scanner.nextLine(), "1");
		
		System.out.println("Enter player 1's color: ");
		players[0].setColor(scanner.nextLine());

		System.out.println("Enter player 2's name: ");
		String playerTwoName = scanner.nextLine();
		/**
		 * add logic to prevent a user from giving a second name that's equal to the
		 * first. Allow the user to try as long as the names are not different.
		 */
		while (playerTwoName.equals(players[0].getName())) {
			System.out.println("Error! Both Players cannot have the same name.");
			System.out.println("Enter player 2's name: ");
			playerTwoName = scanner.nextLine();
		}

		players[1] = new Player(playerTwoName, "2");
		System.out.println("Enter player 2's color: ");
		players[1].setColor(scanner.nextLine());

		// set up the board using the appropriate method
		board.boardSetUp();
		// print the board the using appropriate method
		board.printBoard();
	}

	public void printWinner(Player player) {
		System.out.println(player.getName() + " is the winner");
	}

	public void playerTurn(Player currentPlayer) {
		try {
			int col;
			do {
				col = currentPlayer.makeMove();
				if (col < 0 || col >= board.getColumns()) {
					throw new InvalidMoveException("Invalid move! Please choose a valid column.");
				}

				if (board.columnFull(col)) {
					throw new ColumnFullException("Column is full! Choose another column.");
				}
			} while (!board.addToken(col, currentPlayer.getPlayerNumber()));

			// Print the board after successful token placement
			board.printBoard();
		} catch (InvalidMoveException e) {
			System.out.println("Invalid Move: " + e.getMessage());
		} catch (ColumnFullException e) {
			System.out.println("Column Full: " + e.getMessage());
		}
	}

	public void play() {
		boolean noWinner = true;
		this.setUpGame();
		int currentPlayerIndex = 0;

		int timeForGame = 60; // 1min
		GameTimer gameTimer = new GameTimer(timeForGame);
		gameTimer.start();

		while (gameTimer.isTimeUp() == false) {
			System.out.println("you have "+ timeForGame + " Seconds for this session!");
			while(noWinner) {

				if (board.boardFull()) {
					System.out.println("Board is now full. Game Ends.");
					return;
				}

				Player currentPlayer = players[currentPlayerIndex];
				System.out.println("It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer);
				playerTurn(currentPlayer);

				if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
					printWinner(currentPlayer);
					noWinner = false;
				} else {
					currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
				}
			}
		}
	}
}