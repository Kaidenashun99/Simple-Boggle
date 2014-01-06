import java.util.ArrayList;
import java.util.Scanner;

public class CompressedTrieLexicon extends TrieLexicon {

	private ArrayList<Node> leaf;

	public CompressedTrieLexicon() {
		leaf = new ArrayList<Node>();
	}

	@Override
	public void load(ArrayList<String> list) {
		super.load(list);
		compress();
	}

	public void load(Scanner s) {
		super.load(s);
		compress();
	}

	private void compress() {
		leaves(myRoot);
		for (int k = 0; k < leaf.size(); k++) {
			compressHelp(leaf.get(k));
		}
	}

	private void compressHelp(Node current) {
		if (current.parent.children.size() == 1 && !current.parent.isWord) {
			//consolidate the information of children
			current.parent.info += current.info;
			current.parent.children.clear();
			//make it clear that the parent is a word after consolidation
			current.parent.isWord = true;
			compressHelp(current.parent);
		}
	}

	private void leaves(Node current) {
		if (current.children.isEmpty()) {
			leaf.add(current);
			return;
		}
		for (Character child : current.children.keySet()) {
			Node next = current.children.get(child);
			leaves(next);
		}
	}


	public LexStatus wordStatus(String s) {
		Node t = myRoot;
		for (int k = 0; k < s.length(); k++) {
			if(t.info.length()>1) {
				if(s.substring(k-1).indexOf(t.info)==0 && s.substring(k-1).length()==t.info.length()) {
					return LexStatus.WORD;
				}
				if(t.info.startsWith(s.substring(k-1))) {
					return LexStatus.PREFIX;
				}
			}
			char ch = s.charAt(k);
			t = t.children.get(ch);
			if (t == null) {
				return LexStatus.NOT_WORD;
			}
		}
		return LexStatus.NOT_WORD;
	}

}