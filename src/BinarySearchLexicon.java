import java.util.*;

public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    /**
     * Description: 
     * Returns value specifying whether is is in the
     * lexicon: WORD, is the prefix of a word in
     * the lexicon: PREFIX, or is not a prefix and
     * not a word: NOT_WORD. See LexStatus
     * @param s represents the word/sequence  queried
     * @return status of s as to how it appears in lexicon
     */
    public LexStatus wordStatus(String s) {
    	int search = Collections.binarySearch(myWords, s); 
    	
    	if (search >= 0) 
    		return LexStatus.WORD; 
    	if ((-1*(search))-1 == myWords.size())
    		return LexStatus.NOT_WORD; 
    	if(myWords.get((-1*(search))-1).startsWith(s)) 
        	return LexStatus.PREFIX; 
        else {
        	return LexStatus.NOT_WORD;
        }
     
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
