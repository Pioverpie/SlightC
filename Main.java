import java.io.*;
import java.net.URL;



public class Main {

	public static void main(String[] args) throws IOException {
		
		
		//BufferedReader firstIN = new BufferedReader(new InputStreamReader(System.in));
		String inFileName = args[0];//firstIN.readLine();//input file name from Standard IO --> written in PsuedoC
		
		//URL url = Main.class.getResource(inFileName);
		File PsuedoC = new File(inFileName);//new File(url.getPath());
		
		System.out.println("Filename: "+inFileName);
		//System.out.println("Filepath: "+url.getPath());
		
		
		 BufferedReader in = new BufferedReader(new FileReader(PsuedoC));
		 String[] pop = inFileName.split("//");
		 String outPutFileName = pop[pop.length-1].replace(".slc", ".c");
	     PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outPutFileName)), true);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
	     
	     String currLine;
	     
	     out.print("#include <stdio.h>\r\n" + 
	     		"#include <string.h>\r\n" + 
	     		"\r\n" + 
	     		"int main () {	     		\n");
	   
	     
	     int counter = 1;
	     while(in.ready()) {
	    	 currLine = in.readLine();
	    	 out.println(converter(currLine));
	    	 System.out.println("Line "+counter+" interpreted");
	    	 counter++;
	     }
	     
	     
	     
	     
	     out.println("\n }");

	    
	    
	   
	   in.close();
	   out.close();

	}//end of main method
	

	static boolean isInt(String str)
	{
		try
        {
            // checking valid integer using parseInt() method
            Integer.parseInt(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
	}// end of isInt()

	static boolean isFloat(String str)
	{
		try
        {
            // checking valid float using parseInt() method
            Float.parseFloat(str);
            return true;
        } 
        catch (NumberFormatException e)
        {
            return false;
        }
	}// end of isFloat()
	
	static String converter(String in) {
		String out = "";
				if(typeDetector(in) == 0) {//VAR
					String[] varLine = in.split(" ");
					String curVarType = ((Object)varLine[2]).getClass().getSimpleName();
					String curVar = varLine[2];
					if (curVar.charAt(0) != '\"' && curVar.charAt(curVar.length()-1) != '\"')
						curVarType = "Integer";

					if(curVarType.equals("Integer")) {
						out = "int "+ varLine[0] + " = " + Integer.parseInt(varLine[2])+";";
					}
					else if(curVarType.equals("Double")) {
						out = "float "+ varLine[0] + " = " + varLine[2]+";";
					}
					else if(in.contains(" \"") || in.contains("\" ") || in.contains("\";")) {
						out = "char "+ varLine[0] + "[] = \"" + in.substring(in.indexOf("\""), in.length()-1).replace("\"", "").replace(";", "") +"\";";
					}
					else if(curVarType.equals("Char")) {
						out = "char "+ varLine[0] + " = " + varLine[2]+";";
					}
					else if(curVarType.equals("Boolean")) {
						out = "boolean "+ varLine[0] + " = " + varLine[2]+";";
					}
					
					
				}
		else if(typeDetector(in) == 1) {//LOOP
			String[] varLine = in.split(" ");
			String start = varLine[1];
			//int end = Integer.parseInt(varLine[3]);
			String s = varLine[3];
			if (s.contains("{"))
				{
					s.replace("{", "");
				}
			String end = s;
			
			
			out += "for(int i="+start+";i<"+end+"; i++) {\n";
			
		}
		else if(typeDetector(in) == 2) {//IF-ELSE
			String[] varLine = in.split(" ");
			out = "";
		}
		else if(typeDetector(in) == 3) {//FUN
			out = "";
		}
		else if(typeDetector(in) == 4) {//PRINT
			String varLine = in.replace("output", "").replace(")", "").replace("(", "").replace(";", "");
			//System.out.println(varLine);
			if (GlobalVar.substringExists(varLine+" = "))
			{
				
				if(GlobalVar.Record.substring(GlobalVar.Record.indexOf(varLine+" =",GlobalVar.Record.length()-1)+1).contains("\"")) {//string var
					
					out = "printf(\"%s\", " + varLine + ");";
				}
				else{//int var
					int var = Integer.parseInt(varLine);
					out = "printf(\"%d\", " + var + ");";
				}
			}
			
			else 
			{
			out = "printf(" + varLine + ");";
			}
		}
		else if(typeDetector(in) == 5) {
			out = in;
		}
		
		
		
		GlobalVar.addToString(out);
		return out;
	}//end of converter
	
	
	static int typeDetector(String line) {
		String[] myLine = line.split(" ");
		if(line.contains(" is ") ) {
			return 0;//VARIABLE
		}
		else if(myLine[0].equals("from")) {
			return 1;//LOOP
		}
		else if(myLine[0].equals("if")) {
			return 2;//IF-ELSE
		}
		else if(myLine[0].equals("function")) {
			return 3;//FUNCTION
		}
		else if(myLine[0].contains("output")) {
			return 4;//Print
		}
		else if(myLine[0].contains("{") || myLine[0].contains("}")) {
			return 5;//Brackets
		}
		
		
		
		return -1;
	}
	
	
	
	
	
	
	
}//end of class


class GlobalVar{
	static String Record = "";
	static public void addToString(String s) {
		Record += s+"\n";
	}
	static public boolean substringExists(String ss) {
		if(Record.contains(ss)) {
			return true;
		}
		return false;
	}
	
}
//lolwut


