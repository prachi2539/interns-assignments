import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class ApproximateSearch {

	 public static void main(String[] args) {
		 File file = new File("approximateSearch.txt");
		 ArrayList<String> strings = new ArrayList<>();
		 Scanner input;
		 try {
			input = new Scanner(file);
		    int count = 0;
		    while (input.hasNext()) {
		      String word  = input.next();
		      strings.add(word);
		      count = count + 1;
		    }
		}catch (FileNotFoundException e) {
				e.printStackTrace();
		}

		 Scanner scanner=new Scanner(System.in);
	     System.out.print("Enter a word to find similar strings: ");
	     String searchWord = scanner.nextLine();
	     System.out.print("Enter how many similar words you want : ");
	     int k = scanner.nextInt(); // Number of top similar strings to find
	     ArrayList<String> similarStrings = approximateSearch(strings, searchWord, k);
	     if(similarStrings.size()==k) {
	     	System.out.println("Top " + k + " similar strings to '" + searchWord + "':");
	     }else {
	     	System.out.println("Only " + similarStrings.size() + " similar strings to '" + searchWord + "' found :");
	     }
	     for (String string : similarStrings) {
	         System.out.print(string+", ");
	     }
	 }
	 
	 public static ArrayList<String> approximateSearch(ArrayList<String> strList, String word, int k){
		 ArrayList<String> ans=new ArrayList<String>();
		 int count=0;
			 for(String str : strList) {
				 if(matchString(str, word) && !ans.contains(str)) {
					 ans.add(str);
					 count++;
				 }
				 if(count==k) {
					 return ans;
				 }
			 }
		 return ans;
	 }

	 public static boolean matchString(String str1, String str2) {
		 boolean bool=true;
		 for(int i=0;i<str2.length();i++) {
			 boolean temp=false;
			 for(int j=0;j<str1.length();j++) {
				 if(str2.charAt(i)==str1.charAt(j) || str2.charAt(i)+32==str1.charAt(j) || str2.charAt(i)-32==str1.charAt(j)) {
					 temp=true;
					 if(i<str2.length()-1) {
						 i++; 
						 temp=false;
					 }
				 }				 
			 }
			 if(!temp) {
				 bool=false;
				 break;
			 }			 
		 }	
		 return bool;
	 }
}