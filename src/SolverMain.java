
import java.util.*;
import java.io.*;

public class SolverMain {

	/**
	 * @param args
	 */
	private static final String DICTIONARY_FILE = "mbsingle.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Welcome to the Boggle solver.");
        System.out.println("Using dictionary file " + DICTIONARY_FILE + ".");

        // read dictionary into a set
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        Set<String> dictionary = new TreeSet<String>();
        while (input.hasNextLine()) {
            dictionary.add(input.nextLine());
        }
		
		Scanner s = new Scanner(System.in);
		System.out.println("Width?");
		int width = s.nextInt();
		System.out.println("Height?");
		int height = s.nextInt();
		System.out.println("Please type in the board (Unseparated list of letters)");
		
		String boardLetters = s.next().trim();
		
		String answer = solutions(width, height, boardLetters, dictionary);
		
		System.out.println(answer);

	}
	
	public static String solutions(int width, int height, String letters, Set<String> dictionary) {
		BoggleSolverJava solver = new BoggleSolverJava(width, height, letters, dictionary);
		return solver.solutions().toString();
	}

}
