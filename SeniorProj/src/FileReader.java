import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileReader {

	public static String comment(String comment){
		return comment;
		
	}
			
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("C:/Users/reiki/git/SeniorProject/SeniorProj/src/DebugView.java");
		String token;
		Scanner fileScan, tokenScan;
		int i = 1;
		String reserved = "";
		fileScan = new Scanner(new File("C:/Users/reiki/git/SeniorProject/SeniorProj/src/DebugView.java"));
		
		while (fileScan.hasNext()) {
			System.out.print(i + "    ");
			token = fileScan.nextLine();
			
			Pattern intArray = Pattern.compile("int \\w+ \\[] =.+\\d.+");
			Pattern intArray2 = Pattern.compile("int \\w+\\[] =.+\\d.+");
			Pattern intArray3 = Pattern.compile("int \\w+ \\[.] =.+\\d.+");
			Pattern intArray4 = Pattern.compile("int \\w+ \\[.].+");
			Pattern intOnly = Pattern.compile("int \\w+ =");
			
			Pattern comment = Pattern.compile("\\//");
			Pattern comment2 = Pattern.compile("\\\\");
			Pattern comment3 = Pattern.compile("\\*");
			Pattern comment4 = Pattern.compile("\\*/");
			Pattern comment5 = Pattern.compile("\\/\\*\\*");
			
			Matcher intArrayMatch = intArray.matcher(token);
			Matcher intArrayMatch2 = intArray2.matcher(token);
			Matcher intArrayMatch3 = intArray3.matcher(token);
			Matcher intArrayMatch4 = intArray4.matcher(token);
			Matcher intMatch = intOnly.matcher(token);
			
			Matcher commentMatch = comment.matcher(token);
			Matcher commentMatch2 = comment2.matcher(token);
			Matcher commentMatch3 = comment3.matcher(token);
			Matcher commentMatch4 = comment4.matcher(token);
			Matcher commentMatch5 = comment5.matcher(token);

			// Conditional explanations START

			if(token.contains("if")){
				reserved = "LOGIC ";
				System.out.print(reserved);
			}
			
			//comment match STATRT
			if(commentMatch.find() || commentMatch2.find() || commentMatch3.find() || commentMatch4.find() || commentMatch5.find()){
				reserved = "SKIP ";
				System.out.print(reserved);
			}
			//comment match END

			
			if(token.contains("public")&&! (token.contains("main")  || token.contains("private") &&! (token.contains("main")))){
				reserved = "METHOD ";
				System.out.print(reserved);
			}
			
			if(token.contains("class")){
				reserved = "CLASS ";
				System.out.print(reserved);
			}
			
			if (token.contains("main")){
				reserved = "MAIN ";
				System.out.print(reserved);
			}
			
			//ints and int arrays START
			if (intArrayMatch.find() || intArrayMatch2.find() || intArrayMatch3.find() || intArrayMatch4.find()){
				reserved = "OBJECT INT ARRAY ";
				System.out.print(reserved);
			}
			
				if(intMatch.find()){
				reserved = "OBJECT INT ";
				System.out.print(reserved);
			}
			//ints and int arrays END
			
				
			// Conditional explanations END
			
			
			tokenScan = new Scanner(token);
			tokenScan.useDelimiter("");
			

			while (tokenScan.hasNext())
				System.out.print("" + tokenScan.next());
			System.out.println();
			i++;
		}
		fileScan.close();
	}
}