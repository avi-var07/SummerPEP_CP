/*Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter s: ");
        String s =sc.nextLine();

        System.out.println("Enter number of words in dictionary: ");
        int n=sc.nextInt();

        List<String>arr =new ArrayList<>();
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++)arr.add(sc.next());

        Solution sol =new Solution();
        System.out.println(sol.wordBreak(s, arr));

        sc.close();
    }
    class Trie{
        Trie children[];
        boolean eow;

        Trie(){
            children =new Trie[26];
            for(int i=0;i<26;i++)children[i]=null;
            eow=false;
        }
    }
    Trie root=new Trie();
    void insert(String s){
        Trie curr=root;
        for(char ch: s.toCharArray()){
            int idx= ch-'a';
            if(curr.children[idx]==null)curr.children[idx]=new Trie();
            curr=curr.children[idx];
        }
        curr.eow=true;
    }
    // boolean search(String s){
    //     Trie curr=root;
    //     for(char ch: s.toCharArray()){
    //         int idx=ch-'a';
    //         if(curr.children[idx]==null)return false;
    //         curr=curr.children[idx];
    //     }
    //     return curr.eow;
    // }
    Boolean dp[];
    public boolean wordBreak(String s, List<String> wordDict) {
        for(String ele: wordDict)insert(ele);

        //return solve(s);
        dp=new Boolean[s.length()];
        return solve(0,s);
    }
    boolean solve(int index, String s){
        //TLE: 
        // int n =s.length();
        // if(n==0)return true;

        // for(int i=1;i<=n;i++){
        //     String first =s.substring(0,i);
        //     String second=s.substring(i);

        //     if(search(first)&&solve(second))return true;
        // }
        // return false;

        //better:

        // int n=s.length();
        // if(index==n)return true;

        // if(dp[index]!=null)return dp[index];
        // for(int i=index;i<n;i++){
        //     String pre=s.substring(index, i+1);
        //     if(search(pre)){
        //         if(solve(i+1,s))return dp[index]=true;
        //     }

        // }
        // return dp[index]=false;

        //optimal trie k andr hi chlenge aur recursion laagayenge:

        Trie curr=root;
        int n=s.length();
        if(index==n)return true;
        if(dp[index]!=null)return dp[index];
        for(int i=index;i<n;i++){
            int idx=s.charAt(i)-'a';
            if(curr.children[idx]==null)return dp[index]=false;

            curr=curr.children[idx];

            if(curr.eow){
                if(solve(i+1,s))return dp[index]=true;
            }
        }
        return dp[index]=false;
    }
}