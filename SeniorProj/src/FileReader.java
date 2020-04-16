import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) throws FileNotFoundException {

		String token;
		Scanner fileScan, tokenScan;
		int i =1;
		fileScan = new Scanner(new File("Test.inp"));

		while (fileScan.hasNext()) {
			System.out.print(i + "    ");
			token = fileScan.nextLine();
			// Conditional explanations Start
			
			if(token.contains("if")){
				System.out.print("LOGIC ");
			}
			
			if(token.contains("//")){
				System.out.print("SKIP ");
			}
			
			if (token.contains("=")){
				System.out.print("OBJECT ");
			}
			
			if(token.contains("public") || token.contains("private")){
				System.out.print("METHOD ");
			}
			// Conditional explanations end
			
			
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
