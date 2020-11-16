import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;



public class patternMatch {

	public static void main(String[] args) throws IOException {
		File file = new File(
				"Test2.inp");
		String token = "/**";
		Pattern intArray = Pattern.compile("int \\w+ \\[] =.+\\d.+");
		Pattern intArray2 = Pattern.compile("int \\w+\\[] =.+\\d.+");
		Pattern intArray3 = Pattern.compile("int \\w+ \\[.] =.+\\d.+");
		Pattern intArray4 = Pattern.compile("int \\w+ \\[.].+");
		Pattern intOnly = Pattern.compile("int \\w+ =");
		Pattern n = Pattern.compile("\n");
		
		Pattern ifs = Pattern.compile("if .+");
		Pattern ifs2 = Pattern.compile("else if .+");

		Pattern numbers = Pattern.compile("ss");
		Pattern whiles = Pattern.compile("while.+");
		Pattern Strings = Pattern.compile("\\w");

		// Pattern comment = Pattern.compile("\\//");
		// Pattern comment2 = Pattern.compile("\\/**");
		// Pattern comment3 = Pattern.compile("*");
		// Pattern comment4 = Pattern.compile(" */");

		Pattern method = Pattern.compile("55");

		Matcher intOnlyMatch = intOnly.matcher(token);
		Matcher intArrayMatch = intArray.matcher(token);
		Matcher intArrayMatch2 = intArray2.matcher(token);
		Matcher intArrayMatch3 = intArray3.matcher(token);
		Matcher intArrayMatch4 = intArray4.matcher(token);

		Matcher ifMatch = ifs.matcher(token);
		Matcher ifMatch2 = ifs2.matcher(token);

		Matcher methodMatch = method.matcher(token);

		Matcher numberMatch = numbers.matcher(token);
		Matcher whileMatch = whiles.matcher(token);
		Matcher stringMatch = Strings.matcher(token);

		// Matcher commentMatch = comment.matcher(token);
		// Matcher commentMatch2 = comment2.matcher(token);
		// Matcher commentMatch3 = comment3.matcher(token);
		// Matcher commentMatch4 = comment4.matcher(token);
		System.out.println("start");
		while (intArrayMatch2.find()) {
			System.out.print("Start index: " + intArrayMatch2.start());
			System.out.print(" End index: " + intArrayMatch2.end() + " ");
			System.out.println(intArrayMatch2.group());
		}

		int i = 1;
		int j = 1;
		int k = 1;
		
		
		// now create a new pattern and matcher to replace whitespace with tabs
		Pattern replace = Pattern.compile("\\s+");
		Matcher matcher2 = replace.matcher(token);
		// System.out.println(matcher2.replaceAll("\t"));

		if (file.canExecute() == true) {
			try {

				BufferedReader br = new BufferedReader(new FileReader(file));
				PrintWriter writer = new PrintWriter("C:/Users/reiki/git/SeniorProject/SeniorProj/src/DebugView.java");

				String reader = "";

				while ((reader = (br.readLine())) != null) {
					System.out.print(i + "");
					if (reader.contains("import") && j==1) {
						writer.write(reader + "\n");
						System.out.println(reader);
						k++;
					}
					if (k==1 || (reader.isEmpty() && j<=1)) {
						writer.write("\n");
							writer.write("public class DebugView{\n");
							j++;
							k++;
					}
					if (reader.contains("public class")){
						writer.write(reader.replace("public ", "static "));
						System.out.println(reader.replace("public ", "static "));
					
					}
					if (!(reader.contains("public static void main(String[] args)") ||(reader.contains("public class")) || reader.contains("import"))){
						System.out.println(reader);
						writer.write(reader + "\n");
				}
					if (reader.contains("public static void main(String[] args)")){
						writer.write("}\n");
						writer.write(reader.replace("public static void main(String[] args)", "public static void execute()\n"));
						System.out.println(reader.replaceAll("main", "public static void execute()\n"));
					}
					
					i++;
				}
				br.close();
				writer.close();
				System.out.println("end");
				DebugView.execute();
				// JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				//int b = compiler.run(null, null,
				//null,"C:/Users/reiki/git/SeniorProject/SeniorProj/src/DebugView.java");
				//System.out.println(b);
				//System.out.println(file.toString());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
