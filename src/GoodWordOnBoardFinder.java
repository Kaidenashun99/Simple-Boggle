import java.util.*;

public class GoodWordOnBoardFinder implements IWordOnBoardFinder {

	public List<BoardCell> cellsForWord(BoggleBoard Board, String word) {
		List<BoardCell> List = new ArrayList<BoardCell>();
		for (int row = 0; row < Board.size(); row++) {
			for (int col = 0; col < Board.size(); col++) {
				if (helperFunction(word, 0, row, col, Board, List)) {
					return List;
				}
			}
		}
		List.clear();
		return List;
	}

	private boolean helperFunction(String TestWord, int index, int row, int col, BoggleBoard Board, List<BoardCell> List) {
		//out of bounds so no more options left so false
		if (row >= Board.size() || col >= Board.size() || row < 0 || col < 0)
			return false;
		//you've already found the word
		if (index >= TestWord.length())
			return true;
		String current;
		//setting current
		if (TestWord.substring(index, index + 1).equals("q") && index + 1 != TestWord.length()) {
			current = TestWord.substring(index, index + 2);
		} else {
			current = TestWord.substring(index, index + 1);
		}
		//recursing 8 ways
		if (Board.getFace(row, col).equals(current)) {
			BoardCell Cell = new BoardCell(row, col);
			if (!List.contains(Cell)) {
				List.add(Cell);
				int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
				int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
				for (int k = 0; k < cdelta.length; k++) {
						if (helperFunction(TestWord, index + current.length(), row + rdelta[k], col + cdelta[k], Board, List))
							return true;
				}
				List.remove(Cell);
			}
		}
		return false;
	}
}
