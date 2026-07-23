/*A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith. */
import java.util.*;

class Solution{
    static class Node{
        Node children[]; //26 bacche banane hai a-z
        boolean isEnd;  //check krne k liye word ka end

        Node(){
            children = new Node[26];
            isEnd=false;
        }
    }
    Node root;
    public Solution() {
        root=new Node();
    }

    public void insert(String word) {
        Node current =root; //wohi temp banaya yr
        int n=word.length();
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            int index=ch-'a';
            
            if(current.children[index]==null)current.children[index]=new Node(); //agar child exist nhi krta to naya banaya
            
            //move to that child...agar exist krta to bhi, nhi krta to banaya tha n humne..bus
            current=current.children[index];
        }
        current.isEnd = true; //word ban gaya ek
    }
    
    public boolean search(String word) {
        Node current = root;
        int n =word.length();
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            int index = ch-'a';

            if(current.children[index]==null)return false;
            
            current = current.children[index]; //current move krwate rhenge
        }
        
        return current.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node current = root;
        int n = prefix.length();

        for(int i=0;i<n;i++){
            char ch = prefix.charAt(i);
            int index=  ch-'a';

            if(current.children[index]==null)return false;

            current=current.children[index];
        }
        return true;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Solution trie = new Solution();

        System.out.println("Enter number of words:");
        int n = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        System.out.println("Enter words:");
        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            trie.insert(word);
        }

        System.out.println("Enter word to search:");
        String searchWord = sc.nextLine();
        System.out.println("Search result: " + trie.search(searchWord));

        System.out.println("Enter prefix to check:");
        String prefix = sc.nextLine();
        System.out.println("Prefix result: " + trie.startsWith(prefix));

        sc.close();
    }
}