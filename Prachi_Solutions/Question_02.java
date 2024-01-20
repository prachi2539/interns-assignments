package com.ignitershub.problem2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class ArithmeticExpressionSolver {

    public static void main(String[] args) throws IOException {


        // Create a buffered reader to read the input file
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        // Create a buffered writer to write the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        // Read each line from the input file
        String line;
        while ((line = reader.readLine()) != null) {

            // Solve the arithmetic expression
            int result = solveExpression(new StringBuilder(line));

            // Write the answer to the output file
            writer.write(line+String.valueOf(result));
            writer.newLine();
        }

        // Close the input and output files
        reader.close();
        writer.close();
    }

    public static int solveExpression(StringBuilder exp) {
		int first,last;

		if(exp.indexOf("(")>-1 && exp.indexOf(")")>-1) {
			first=exp.lastIndexOf("(");
			last=exp.indexOf(")");
		}
		else if(exp.indexOf("{")>-1 && exp.indexOf("}")>-1) {
			first=exp.lastIndexOf("{");
			last=exp.indexOf("}");
		}
		else if(exp.indexOf("[")>-1 && exp.indexOf("]")>-1) {
			first=exp.lastIndexOf("[");
			last=exp.indexOf("]");
		}
		else {
			first=0;
			last=exp.length()-1;
		}
		
		StringBuilder temp=new StringBuilder();
		for(int i=first+1;i<last;i++) {
			temp.append(exp.charAt(i));
		}
		exp.delete(first,last+1);	
		exp.insert(first,calculate(temp));
		if(exp.indexOf("+")>-1 || exp.indexOf("-")>-1 || exp.indexOf("*")>-1 || exp.indexOf("/")>-1 || exp.indexOf("^")>-1) {
			solveExpression(exp);
		}
		return Integer.parseInt(new String(exp));
	}
	
	public static int calculate(StringBuilder sb) {
		int prevNo,nextNo;
		int first,last;
		
		if(sb.indexOf("^")>-1) {
			int[] temp=getPrevNo(sb,sb.indexOf("^"));
			first=(int)temp[0];
			prevNo=temp[1];
			temp=getNextNo(sb, sb.indexOf("^"));
			last=(int)temp[0];
			nextNo=temp[1];
			sb.delete(first, last+1);
			sb.insert(first, applyOperator('^', prevNo,nextNo));
		}else if(sb.indexOf("/")>-1) {
			int[] temp=getPrevNo(sb,sb.indexOf("/"));
			first=(int)temp[0];
			prevNo=temp[1];
			temp=getNextNo(sb, sb.indexOf("/"));
			last=(int)temp[0];
			nextNo=temp[1];
			sb.delete(first, last+1);
			sb.insert(first, applyOperator('/', prevNo,nextNo));
		}else if(sb.indexOf("*")>-1) {
			int[] temp=getPrevNo(sb,sb.indexOf("*"));
			first=(int)temp[0];
			prevNo=temp[1];
			temp=getNextNo(sb, sb.indexOf("*"));
			last=(int)temp[0];
			nextNo=temp[1];
			sb.delete(first, last+1);
			sb.insert(first, applyOperator('*', prevNo,nextNo));
		}else if(sb.indexOf("+")>-1) {
			int[] temp=getPrevNo(sb,sb.indexOf("+"));
			first=(int)temp[0];
			prevNo=temp[1];
			temp=getNextNo(sb, sb.indexOf("+"));
			last=(int)temp[0];
			nextNo=temp[1];
			sb.delete(first, last+1);
			sb.insert(first, applyOperator('+', prevNo,nextNo));
		}else if(sb.indexOf("-")>-1) {
			int[] temp=getPrevNo(sb,sb.indexOf("-"));
			first=(int)temp[0];
			prevNo=temp[1];
			temp=getNextNo(sb, sb.indexOf("-"));
			last=(int)temp[0];
			nextNo=temp[1];
			sb.delete(first, last+1);
			sb.insert(first, applyOperator('-', prevNo,nextNo));
		}
		
		if(sb.indexOf("+")>-1 || sb.indexOf("-")>-1 || sb.indexOf("*")>-1 || sb.indexOf("/")>-1 || sb.indexOf("^")>-1) {
			calculate(sb);
		}
		return Integer.parseInt(new String(sb));
	}
    
    private static int applyOperator(char operator, int a, int b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case '^':
            	if(b==0 || a==0) {
            		return 1;
            	}
            	int result=1;
            	for(int i=0;i<(int)b;i++) {
            		result*=a;
            	}
            	return result;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    public static int[] getPrevNo(StringBuilder sb,int index){
    	int[] res=new int[2];
    	StringBuilder s=new StringBuilder();
    	int temp=-1;
    	for(int i=index-1;i>=0;i--) {
    		if(Character.isDigit(sb.charAt(i)) || sb.charAt(i)=='.') {
    			s.insert(0,sb.charAt(i));
    			temp=i;
    		}
    		else {
    			res[0]=temp;
    			break;
    		}
    	}
    	System.out.println(s);
    	res[1]=Integer.parseInt(new String(s));
    	return res;
    }
    public static int[] getNextNo(StringBuilder sb,int index){
    	int[] res=new int[2];
    	StringBuilder s=new StringBuilder();
    	int temp=-1;
    	for(int i=index+1;i<sb.length();i++) {
    		if(Character.isDigit(sb.charAt(i))) {
    			s.append(sb.charAt(i));
    			temp=i;
    		}
    		else {
    			res[0]=temp;
    			break;
    		}
    	}
    	res[1]=Integer.parseInt(new String(s));
    	return res;
    }
}