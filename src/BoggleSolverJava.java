// Sam Gordon
// 5/16/12
// Java version of a Boggle solving program

import java.util.*;

public class BoggleSolverJava {
	private final int MIN_LENGTH = 4;
	
	private BoggleBoard board;
	private Set<String> dictionary;
	private int width;
	private int height;
	
	public BoggleSolverJava(int width, int height, String board, Set<String> dict) {
		this.width = width;
		this.height = height;
		this.dictionary = dict;
		String[] letters = new String[board.length()];
		for (int i = 0; i < letters.length; i++) {
			letters[i] = "" + board.charAt(i);
		}
		this.board = new BoggleBoard(this.width, this.height, letters);
	}
	
	public Set<String> solutions() {
		Set<String> solutions = new HashSet<String>();
		board.solve(dictionary, solutions);
		return solutions;
	}
	
	private class BoggleBoard {
		
		private BoggleSquare[][] board;
		
		public BoggleBoard(int width, int height, String[] letters) {
			board = new BoggleSquare[height][width];
			
			int place = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					board[i][j] = new BoggleSquare(letters[place]);
					place++;
				}
			}
		}
		
		public void solve(Set<String> dict, Set<String> solutions) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					solve(dict, solutions, j, i, "");
				}
			}
		}
		
		public void solve(Set<String> dict, Set<String> solutions, int x, int y, String word) {
			if (dict.size() > 0 && !board[y][x].used) {
				String thisLetter = board[y][x].letter;
				board[y][x].used = true;
				word = word + thisLetter;
				Set<String> possibles = new HashSet<String>();
				for (String s : dict) {
					if (s.startsWith(word)) {
						possibles.add(s);
						if (word.length() >= MIN_LENGTH && s.equalsIgnoreCase(word)) {
							solutions.add(word);
						}
					}
				}
				
				if (x - 1 >= 0) {
					solve(possibles, solutions, x - 1, y, word);
					if (y - 1 >= 0) {
						solve(possibles, solutions, x - 1, y - 1, word);
					} 
					if (y + 1 < height) {
						solve(possibles, solutions, x - 1, y + 1, word);
					}
				} 
				if (x + 1 < width) {
					solve(possibles, solutions, x + 1, y, word);
					if (y - 1 >= 0) {
						solve(possibles, solutions, x + 1, y - 1, word);
					}
					if (y + 1 < height) {
						solve(possibles, solutions, x + 1, y + 1, word);
					}
				}
				if (y - 1 >= 0) {
					solve(possibles, solutions, x, y - 1, word);
				} 
				if (y + 1 < width) {
					solve(possibles, solutions, x, y + 1, word);
				}
				board[y][x].used = false;
			}
		}
		
		public class BoggleSquare {
			
			public String letter;
			public boolean used;
			
			public BoggleSquare(String letter) {
				this.letter = letter;
				this.used = false;
			}
		}
	}
}