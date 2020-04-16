import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) throws FileNotFoundException {

		String url;
		Scanner fileScan, urlScan;
		int i =1;
		fileScan = new Scanner(new File("website.inp"));

		while (fileScan.hasNext()) {
			System.out.print(i + "    ");
			url = fileScan.nextLine();
			// Conditional explanations Start
			
			if(url.contains("if")){
				System.out.print("LOGIC ");
			}
			
			if(url.contains("//")){
				System.out.print("SKIP ");
			}
			
			if (url.contains("=")){
				System.out.print("OBJECT ");
			}
			
			if(url.contains("public") || url.contains("private")){
				System.out.print("METHOD ");
			}
			// Conditional explanations end
			
			
			urlScan = new Scanner(url);
			urlScan.useDelimiter("");
			

			while (urlScan.hasNext())
				System.out.print("" + urlScan.next());
			System.out.println();
			i++;
		}
		fileScan.close();
	}
}
