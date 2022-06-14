import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

class TrieNode{

    private char c;
    private Map<Character,TrieNode> children = new HashMap<>();
    private boolean isLeaf = false;

    TrieNode(){

    }

    /**
     * @param c the c to set
     */
    public void setC(char c) {
        this.c = c;
    }

    /**
     * @return the children
     */
    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    /**
     * @return the isLeaf
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * @return the c
     */
    public char getC() {
        return c;
    }

    /**
     * @param isLeaf the isLeaf to set
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}

class Trie{

    TrieNode rootNode;

    public Trie(){
        rootNode = new TrieNode();
    }

    public void insertWord(String word){
        TrieNode current = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character,TrieNode> children = current.getChildren();
            if(children.containsKey(c)){
                current = children.get(c);
            }
            else{
                TrieNode trieNode = new TrieNode();
                children.put(c, trieNode);
                current = children.get(c);
            }
        }
        current.setLeaf(true);
    }

    public boolean searchWord(String word){
        TrieNode current = rootNode;
        for (int i = 0; i < word.length(); i++) {
            Map<Character,TrieNode> children = current.getChildren();
            char c = word.charAt(i);
            if(children.containsKey(c)){
                current = children.get(c);
            }
            else{
                return false;
            }
        }

        if(current.isLeaf() && current!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public void print(TrieNode rootNode,int level, StringBuilder sequence) {
        if(rootNode.isLeaf()){
            sequence = sequence.insert(level, rootNode.getC());
            System.out.println(sequence);
        }

        Map<Character, TrieNode> children = rootNode.getChildren();
        Iterator<Character> iterator = children.keySet().iterator();
        while (iterator.hasNext()) {
            char character = iterator.next();
            sequence = sequence.insert(level, character); 
            print(children.get(character), level+1, sequence);
            sequence.deleteCharAt(level);
        }
    }
}

class TrieImplementation{
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insertWord("Fahri");
        trie.insertWord("Dio");
        trie.insertWord("Ilham");
        trie.insertWord("Amplop");
        trie.insertWord("Kucing");
        trie.insertWord("Mobil");
        trie.insertWord("Lampu");
        TrieNode root = trie.rootNode;
        System.out.println("\nTrie Root: ");
        trie.print(root,0,new StringBuilder(""));
        System.out.println("");

        System.out.println(trie.searchWord("Fahri"));
        System.out.println(trie.searchWord("Kucing"));
        System.out.println(trie.searchWord("Motor"));
        System.out.println(trie.searchWord("Dio"));
        System.out.println(trie.searchWord("Charger"));
        System.out.println(trie.searchWord("Ilham"));
    }
}