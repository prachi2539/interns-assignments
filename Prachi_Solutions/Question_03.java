package com.ignitershub.problem3;

import java.util.Scanner;
public class PelindromeOrNot {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		System.out.print("Enter the String :");
		String st=scan.nextLine();
		if(isPelindrome(st)) {
			System.out.println(st+" is Pelindrome.");
		}
		else {
			System.out.println(st+" is not a Pelindrome.");
		}
			scan.close();
	}
	
	private static boolean isPelindrome(String s) {	
		int len=s.length();
		char[] c=new char[len];
		for(int i=0;i<len;i++) {
			c[i]=s.charAt(i);
		}
		String temp="";
		for(int j=len-1;j>=0;j--) {
			temp=temp+c[j];
		}
		if(temp.equals(s)) {
			return true;
		}
		else {
			return false;
		}
	}

}