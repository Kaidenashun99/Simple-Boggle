import java.util.*;

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {


	public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
		clear();
		List<BoardCell> CellList = new ArrayList<BoardCell>();

		for (int row = 0; row < board.size(); row++) {
			for (int col = 0; col < board.size(); col++) {
				helper(board, row, col, minLength, lex, CellList,
						new StringBuilder());
			}
		}
	}

	private void helper(BoggleBoard board, int row, int col, int minLength, ILexicon lex, List<BoardCell> CellList, StringBuilder currentString) {

		int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
		BoardCell CurrentCell = new BoardCell(row, col);

		// base cases
		if (row >= board.size() || col >= board.size() || row < 0 || col < 0)
			return;
		if (CellList.contains(CurrentCell))
			return; // ensure computer does not return to the same square

			String letter = board.getFace(row, col); //a letter or Qu
			CellList.add(CurrentCell); //add the current cell to the list of used cells
			currentString.append(letter);// add current letter

			//if the current string is a prefix or a word
			if (lex.wordStatus(currentString).equals(LexStatus.PREFIX) || lex.wordStatus(currentString).equals(LexStatus.WORD)) {

				//check if word, if so then add the word to the list of words found;
				if (lex.wordStatus(currentString).equals(LexStatus.WORD)) {
					if (currentString.length() >= minLength) { // a correct word has been found
						String result = currentString.toString();
						add(result);
					}
				}
				//check surrounding cells to create new words or complete a word
				for (int k = 0; k < rdelta.length; k++) {
					helper(board, row + rdelta[k], col + cdelta[k], minLength, lex, CellList, currentString);
				}
			}

			CellList.remove(CurrentCell);
			if (letter.length() > 1) // special case for Qu
				currentString.setLength(currentString.length() - 2);
			if (letter.length() == 1)
				currentString.deleteCharAt(currentString.length() - 1);
		}
	}

