import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class FileRead {
	Toolkit toolkit;
	/**
	 * 
	 */
	Timer timer;
	static String reader = "";
	static String reserved = "";
	static String smallReserved = "";
	static String currentMethod = "";
	static StringWriter writer = new StringWriter();
	static List<String> Methods = new ArrayList<String>();
	static List<String> StaticMethods = new ArrayList<String>();
	static List<String> hold  =  new ArrayList<>();
	static List<String> numberhold =  new ArrayList<>();
	static List<String> StringHold =  new ArrayList<>();
	static List<String> CharHold =  new ArrayList<>();
	static List<String> DoubleHold =  new ArrayList<>();
	static List<String> FloatHold =  new ArrayList<>();
	static List<String> LongHold =  new ArrayList<>();
	static List<String> ShortHold =  new ArrayList<>();
	static List<String> ByteHold =  new ArrayList<>();
	static List<String> nothing =  new ArrayList<>();
	static List<String> ImportList = new ArrayList<>();
	static List<String> ClassList = new ArrayList<>();
	static List<String> ClassVariableList = new ArrayList<>();
	static List<String> intList = new ArrayList<String>();
	
	static Hashtable<String, List<String>> intMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> StringMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> CharMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> DoubleMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> FloatMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> LongMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> ShortMap = new Hashtable <String, List<String>>();
	static Hashtable<String, List<String>> ByteMap = new Hashtable <String, List<String>>();
	static Hashtable <String, List<String>> hmap = new Hashtable <String, List<String>>();
	static int call = 0;

	public FileRead(){
		Scanner scanning = new Scanner(System.in);
		System.out.print("Plese enter the location of the file: ");
		String Location = scanning.nextLine();
		Location = Location.trim();
		if (Location.contains("\\")){
			Location = Location.replace("\\", "/");
		}
		System.out.print("Plese enter the first line to be read: ");
		Scanner scan = new Scanner(System.in);
		int FirstLine = scan.nextInt();
		System.out.print("Plese enter the first last to be read: ");
		Scanner scan1 = new Scanner(System.in);
		int LastLine = scan1.nextInt();
		scanning.close();
		scan.close();
		scan1.close();
		
		try {
			reading(Location, FirstLine, LastLine);
		} catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param reader
	 */
	public static void conditional(String reader){
		if (reader.contains("import ")){
			ImportList.add(reader);
		}

		if (reader.contains("if") && (!(reserved == "MAIN "))) {
			smallReserved = "LOGIC ";
		}
		if (reader.contains("String ")){
			smallReserved = "OBJECT STRING ";
		}
		
		if (reader.contains("char ")){
			smallReserved = "OBJECT CHAR ";
		}
		
		if (reader.contains("double ")){
			smallReserved = "OBJECT DOUBLE ";
		}
		
		if (reader.contains("long ")){
			smallReserved = "OBJECT LONG ";
		}
		
		if (reader.contains("byte ")){
			smallReserved = "OBJECT BYTE ";
		}
		
		if (reader.contains("short ")){
			smallReserved = "OBJECT SHORT ";
		}
		
		if (reader.contains("float ")){
			smallReserved = "OBJECT FLOAT ";
		}		
	}
	
	public static void comments(String reader, String smallReserved){
		Pattern comment = Pattern.compile("\\//");
		Pattern comment2 = Pattern.compile("\\\\");
		Pattern comment3 = Pattern.compile("\\*");
		Pattern comment4 = Pattern.compile("\\*/");
		Pattern comment5 = Pattern.compile("\\/\\*\\*");

		Matcher commentMatch = comment.matcher(reader);
		Matcher commentMatch2 = comment2.matcher(reader);
		Matcher commentMatch3 = comment3.matcher(reader);
		Matcher commentMatch4 = comment4.matcher(reader);
		Matcher commentMatch5 = comment5.matcher(reader);
		
		if (commentMatch.find() || commentMatch2.find()
				|| commentMatch3.find() || commentMatch4.find()
				|| commentMatch5.find()) {
			smallReserved = "SKIP ";
		}
	}
		
	/**
	 * @param currentMethod
	 * @param reader
	 * @param Methods
	 * @param StaticMethods
	 * This method takes the line of code that creates a method and strips it down to just its name
	 */
	public static void methodRename(String currentMethod, String reader, List<String> Methods, List<String> StaticMethods){
		String words = reader.trim();
		String reservations[] = { "abstract", "assert", "boolean", "break",
				"byte", "case", "catch", "char", "class", "const", "continue",
				"default", "double", "do", "else", "enum", "extends", "false",
				"final", "finally", "float", "for", "goto", "if", "implements",
				"import", "instanceof", "int", "interface", "long", "native",
				"new", "null", "package", "private", "protected", "public",
				"return", "short", "static", "String", "strictfp", "super",
				"switch", "synchronized", "this", "throw", "throws",
				"transient", "true", "try", "void", "volitile", "while" };
	for (int n = 0; n <= 1; n++) {
		for (int m = 0; m <= reservations.length - 1; m++) {
			String change = reservations[m];
			try {
				if (words.substring(0, change.length()).contains(change)) {
					String newWord = words.substring(change.length() + 1);

					words = newWord;
					words = words.trim();
				}
				if (words.contains("(")) {
					int punct = words.indexOf("(");
					words = words.substring(0, punct);
					words = words.trim();

				} else if (words.contains("=")) {
					int punct = words.indexOf("=");
					words = words.substring(0, punct);
					words = words.trim();

				} else if (words.contains(",")) {
					int punct = words.indexOf(",");
					words = words.substring(0, punct);
					words = words.trim();
				}
				
				currentMethod = words.trim();
				FileRead.currentMethod = currentMethod;
			} catch (IndexOutOfBoundsException e) {
			}
		}
	}
	if (!(words.isEmpty())){
	Methods.add(words);
	}
	if (!(words.isEmpty()) && reader.contains("static")) {
		StaticMethods.add(words);
	}
}
	
	/**
	 * @param intMap
	 * @param x
	 * @param g
	 * This method takes recognizes if the line of code creates an int and places it in the int Hashtable
	 */
	public static void RecoverInt(Hashtable<String, List<String>> intMap, int x, int g){
		if(smallReserved == "OBJECT INT " && x != g && (reserved != "MAIN ")){
			String numName = reader.trim();
			if (numName.contains("=")){
				int punct = numName.indexOf("=");

				if(numName.contains("int ")){
					numName = numName.substring(numName.indexOf("i"), punct);
					numName = numName.substring(3, numName.length());
					numName.trim();
					call++;
					
					if(!(numName.isEmpty())){
						numberhold.add(numName.trim());
						intMap.put(currentMethod, numberhold);
					}
				}
			}
		}
	}

	/**
	 * @param StringMap
	 * This method takes recognizes if the line of code creates an String and places it in the String Hashtable
	 */
	public static void RecoverString(Hashtable<String, List<String>> StringMap){
		if(smallReserved == "OBJECT STRING "){
			String stringName = reader.trim();
			if (stringName.contains("=")){
				int punct = stringName.indexOf("=");
				stringName = stringName.substring(reader.indexOf("s"), punct);
				if(stringName.contains("String ")){
					stringName = stringName.substring(6, stringName.length());
					stringName.trim();
					}
			if(!(stringName.isEmpty())){
				StringHold.add(stringName.trim());
				StringMap.put(currentMethod, StringHold);
			}
			}
			}
	}
	
	/**
	 * @param CharMap
	 * This method takes recognizes if the line of code creates an char and places it in the char Hashtable
	 */
	public static void RecoverChar(Hashtable<String, List<String>> CharMap){
		if(smallReserved == "OBJECT CHAR "){
			String CharName = reader;
			if (CharName.contains("=")){
				int punct = CharName.indexOf("=");
				CharName = CharName.substring(CharName.indexOf("c"), punct);
				if(CharName.contains("char")){
					CharName = CharName.substring(4, CharName.length());
					CharName.trim();
				}
			}
			if(!(CharName.isEmpty())){
				CharHold.add(CharName.trim());
				CharMap.put(currentMethod, CharHold);
			}
		}
	}
	
	/**
	 * @param DoubleMap
	 * This method takes recognizes if the line of code creates an double and places it in the double Hashtable
	 */
	public static void RecoverDouble(Hashtable<String, List<String>> DoubleMap){
		if(smallReserved == "OBJECT DOUBLE "){
			String DoubleName = reader.trim();
			if (DoubleName.contains("=")){
				int punct = DoubleName.indexOf("=");
				DoubleName = DoubleName.substring(DoubleName.indexOf("d"), punct);
				if(DoubleName.contains("double")){
					DoubleName = DoubleName.substring(7, DoubleName.length());
					DoubleName.trim();
				}
			}
			if(!(DoubleName.isEmpty())){
				DoubleHold.add(DoubleName.trim());
				DoubleMap.put(currentMethod, DoubleHold);
			}
		}
	}
	
	/**
	 * @param FloatMap
	 * This method takes recognizes if the line of code creates an float and places it in the float Hashtable
	 */
	public static void RecoverFloat(Hashtable<String, List<String>> FloatMap){
		if (smallReserved == "OBJECT FLOAT ") {
			String FloatName = reader.trim();
			if (FloatName.contains("=")) {
				int punct = FloatName.indexOf("=");
				FloatName = FloatName.substring(FloatName.indexOf("f"), punct);
				if (FloatName.contains("float")) {
					FloatName = FloatName.substring(5, FloatName.length());
					FloatName.trim();
				}
			}
			if (!(FloatName.isEmpty())) {
				FloatHold.add(FloatName.trim());
				FloatMap.put(currentMethod, FloatHold);
			}
		}
	}
	
	/**
	 * @param LongMap
	 * This method takes recognizes if the line of code creates an long and places it in the long Hashtable
	 */
	public static void RecoverLong(Hashtable<String, List<String>> LongMap){
		if (smallReserved == "OBJECT LONG ") {
			String LongName = reader.trim();
			if (LongName.contains("=")) {
				int punct = LongName.indexOf("=");
				LongName = LongName.substring(LongName.indexOf("l"), punct);
				if (LongName.contains("long")) {
					LongName = LongName.substring(4, LongName.length());
					LongName.trim();
				}
			}
			if (!(LongName.isEmpty())) {
				LongHold.add(LongName.trim());
				LongMap.put(currentMethod, LongHold);
			}
		}
	}
	
	/**
	 * @param ShortMap
	 * This method takes recognizes if the line of code creates an short and places it in the short Hashtable
	 */
	public static void RecoverShort(Hashtable<String, List<String>> ShortMap){
		if (smallReserved == "OBJECT SHORT ") {
			String ShortName = reader.trim();
			if (ShortName.contains("=")) {
				int punct = ShortName.indexOf("=");
				ShortName = ShortName.substring(ShortName.indexOf("s"), punct);
				if (ShortName.contains("short")) {
					ShortName = ShortName.substring(5, ShortName.length());
					ShortName.trim();
					System.out.println(ShortName.trim());
				}
			}
			if (!(ShortName.isEmpty())) {
				ShortHold.add(ShortName.trim());
				ShortMap.put(currentMethod, ShortHold);
			}
		}
	}
	
	/**
	 * @param ByteMap
	 * This method takes recognizes if the line of code creates an byte and places it in the byte Hashtable
	 */
	public static void RecoverByte(Hashtable<String, List<String>> ByteMap){
		if (smallReserved == "OBJECT BYTE ") {
			String ByteName = reader;
			if (ByteName.contains("=")) {
				int punct = ByteName.indexOf("=");
				ByteName = ByteName.substring(ByteName.indexOf("b"), punct);
				if (ByteName.contains("byte")) {
					ByteName = ByteName.substring(4, ByteName.length());
					ByteName.trim();
				}
			}
			if (!(ByteName.isEmpty())) {
				ByteHold.add(ByteName.trim());
				ByteMap.put(currentMethod, ByteHold);
			}
		}
	}
		
	/**
	 * @param part2
	 * @param out
	 * @param i
	 * @param Methods
	 * This method determines which Hashtable to pull from so it can print the correct variables in the correct places
	 */
	public static void WhichMap (String part2, PrintWriter out, int i, List<String> Methods){
		if (part2.contains("int ") && part2.contains("=") || part2.contains("++")){
			for(int t = 0; t < intMap.get(Methods.get(i)).size(); t++){
				if(i < intMap.size()){
					String quote = "\"";
					String part3 = intMap.get(Methods.get(i)).get(t);
					if(part2.contains("int " + part3) || part2.contains(part3 + "++")){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + " + " + intMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}

		if (part2.contains("String ") && part2.contains("=")){
			System.out.println(StringMap.size());
			System.out.println(StringMap.values());
			for(int t = 0; t < StringMap.get(Methods.get(0)).size(); t++){
				if(i < StringMap.size()){
					String quote = "\"";
					String part3 = StringMap.get(Methods.get(i)).get(t);
					if(part2.contains("String " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + StringMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		if (part2.contains("char ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < CharMap.get(Methods.get(0)).size(); t++){
				if(i < CharMap.size()){
					String quote = "\"";
					String part3 = CharMap.get(Methods.get(i)).get(t);
					if(part2.contains("char " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + CharMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		
		if (part2.contains("double ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < DoubleMap.get(Methods.get(0)).size(); t++){
				if(i < DoubleMap.size()){
					String quote = "\"";
					String part3 = DoubleMap.get(Methods.get(i)).get(t);
					if(part2.contains("double " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + DoubleMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		
		if (part2.contains("float ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < FloatMap.get(Methods.get(0)).size(); t++){
				if(i < FloatMap.size()){
					String quote = "\"";
					String part3 = FloatMap.get(Methods.get(i)).get(t);
					if(part2.contains("float " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + FloatMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		
		if (part2.contains("long ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < LongMap.get(Methods.get(0)).size(); t++){
				if(i < LongMap.size()){
					String quote = "\"";
					String part3 = LongMap.get(Methods.get(i)).get(t);
					if(part2.contains("long " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + LongMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		
		if (part2.contains("short ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < ShortMap.get(Methods.get(0)).size(); t++){
				if(i < ShortMap.size()){
					String quote = "\"";
					String part3 = ShortMap.get(Methods.get(i)).get(t);
					if(part2.contains("short " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + ShortMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
		
		if (part2.contains("byte ") && (!(reserved == "MAIN ")) && part2.contains("=")){
			for(int t = 0; t < ByteMap.get(Methods.get(0)).size(); t++){
				if(i < ByteMap.size()){
					String quote = "\"";
					String part3 = ByteMap.get(Methods.get(i)).get(t);
					if(part2.contains("byte " + part3)){
						String output = "System.out.println(";
						String realWord = quote + part3 + " = " + quote;
						String output1 = output + realWord;
						String output2 = "System.out.print(";
						out.print(output2 + quote + Methods.get(i) + ": " + quote  +");");
						out.println(output1 + "+ " + ByteMap.get(Methods.get(i)).get(t) + ");");
					}
				}
			}
		}
	}
	
	/**
	 * @param lineNumber
	 * @param FirstLine
	 * @param LastLine
	 * @param hmap
	 * @param Methods
	 * @param reader
	 * @param writer
	 * @param out
	 * @param intMap
	 * @param intList
	 * @throws MalformedURLException
	 * @throws NullPointerException
	 * This method writes the new java class with all the correct imports and methods while also injecting print statements
	 */
	public static void Compilation(int lineNumber, int FirstLine, int LastLine, Hashtable <String, List<String>> hmap, List<String> Methods, String reader,StringWriter writer, PrintWriter out, Hashtable<String,List<String>> intMap ,List<String> intList) throws MalformedURLException, NullPointerException{
		System.setProperty("java.home", "C://Program Files//Java//jdk1.8.0_201");
		if (lineNumber == FirstLine) {
			if (!(ImportList.isEmpty())) {
				for (int i = 0; i < ImportList.size(); i++) {
					out.println(ImportList.get(i));
				}
			}
			out.println("public class " + ClassList.get(0) + "{");

			if (!(ClassVariableList.isEmpty())) {
				for (int i = 0; i < ClassVariableList.size(); i++) {
					out.println(ClassVariableList.get(i));
				}
			}
			for (int i = 0; i < Methods.size(); i++) {
				for (int w = 0; w < hmap.get(Methods.get(i)).size(); w++) {
					String part2 = hmap.get(Methods.get(i)).get(w);
					out.println(hmap.get(Methods.get(i)).get(w));
					WhichMap(part2, out, i, Methods);
				}
			}
			out.println("public static void main(String[] args){");
		}

		if (!(reader.contains("public static void main(String[] args)"))) {
			out.println(reader);
		}
		if (lineNumber == LastLine) {
			out.println("  }");
			out.println("}");
		}
	}
	
	/**
	 * @param writer
	 * @throws MalformedURLException
	 * This method is where the code is actually being compiled
	 */
	public static void RealCompilation(StringWriter writer) throws MalformedURLException{		
			System.setProperty("java.home","C://Program Files//Java//jdk1.8.0_201");
		 	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		    JavaFileObject file1 = new JavaSourceFromString(ClassList.get(0), writer.toString());

		    Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file1);
		    CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

		    boolean success = task.call();
		    for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
		      System.out.println(diagnostic.getCode());
		      System.out.println(diagnostic.getKind());
		      System.out.println(diagnostic.getPosition());
		      System.out.println(diagnostic.getStartPosition());
		      System.out.println(diagnostic.getEndPosition());
		      System.out.println(diagnostic.getSource());
		      System.out.println(diagnostic.getMessage(null));
		    }

		    if (success) {
		      try {
		          URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
		          Class.forName(ClassList.get(0), true, classLoader).getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
		      } catch (ClassNotFoundException e) {
		        System.err.println("Class not found: " + e);
		      } catch (NoSuchMethodException e) {
		        System.err.println("No such method: " + e);
		      } catch (IllegalAccessException e) {
		        System.err.println("Illegal access: " + e);
		      } catch (InvocationTargetException e) {
		        System.err.println("Invocation target: " + e);
		      }
		    }
	}
	
	/**
	 * @param file
	 * @param FirstLine
	 * @param LastLine
	 * @throws java.net.MalformedURLException
	 * @throws NullPointerException
	 * @throws IOException
	 * Reading is the main loop of the program
	 */
	public static void reading (String file, int FirstLine, int LastLine) throws java.net.MalformedURLException, NullPointerException, IOException{
	    PrintWriter out = new PrintWriter(writer);
		int g = 0;
		int x = 0;
		int i = 1;
		nothing.add("");
		String reset="";
		String words="";
		BufferedReader br = new BufferedReader(new FileReader(file));

		while ((reader = (br.readLine())) != null) {
			//System.out.print(i + "    ");
			// Conditional explanations START
			conditional(reader);
			

			// comment match STATRT
			comments(reader, smallReserved);
			// comment match END

			//This stores the method name
			if ( (reader.contains("public") || reader.contains("private"))  && !((reader.contains("main")) || (reader.contains("class")) || (reader.contains("="))) && (reader.contains("(")) ){
				reserved = "METHOD ";
				methodRename(currentMethod, reader, Methods, StaticMethods);
			}
			
			if ( (reader.contains("public") || reader.contains("private"))  && !((reader.contains("main")) || (reader.contains("class")) || (reader.contains("="))) && (!(reader.contains("("))) ){
				reserved = "CLASS VARIABLE ";
				ClassVariableList.add(reader);
			}

			if (reader.contains("public class ")) {
				reserved = "CLASS ";
				String className = reader.replace("public ", "static ");
				className= className.substring(13);
				className = className.replace("{", "");
				ClassList.add(className.trim());
			}
			
			if (reader.trim().startsWith("for") || reader.trim().startsWith("while")){
			}

			String quote = "\"";
			if (reader.contains("main") && (!(reader.contains(quote + "main" + quote)))) {
				reserved = "MAIN ";
			}


			// ints and int arrays START {
				Pattern intArray = Pattern.compile("int \\w+ \\[] =.+\\d.+");
				Pattern intArray2 = Pattern.compile("int \\w+\\[] =.+\\d.+");
				Pattern intArray3 = Pattern.compile("int \\w+ \\[.] =.+\\d.+");
				Pattern intArray4 = Pattern.compile("int \\w+ \\[.].+");
				Pattern intOnly = Pattern.compile("int \\w+ =.");
				
				Matcher intMatch = intOnly.matcher(reader.trim());
				Matcher intArrayMatch = intArray.matcher(reader.trim());
				Matcher intArrayMatch2 = intArray2.matcher(reader.trim());
				Matcher intArrayMatch3 = intArray3.matcher(reader.trim());
				Matcher intArrayMatch4 = intArray4.matcher(reader.trim());
				
			if ((intArrayMatch.find() || intArrayMatch2.find()|| intArrayMatch3.find() || intArrayMatch4.find()) && (reserved != "MAIN ")) {
				smallReserved = "OBJECT ARRAY INT ";
			}
			
			if (intMatch.find()) {
				smallReserved = "OBJECT INT ";
				if(reserved =="METHOD " && !(words.isEmpty())){
					intList.add(reader.trim());
				}
			}

			
			if (reserved == "METHOD " && (reserved != "MAIN ")){
				if (reader.contains("{")){
					g++;
				}
				if (reader.contains("}")){
					x++;
				}
					if(!(reader.trim().isEmpty())){
					hold.add(reader.trim());
					hmap.put(currentMethod, hold);
					if(smallReserved == "OBJECT INT "){
						RecoverInt(intMap, x, g);
						}
					
					if(smallReserved == "OBJECT STRING "){
						RecoverString(StringMap);
					}
					
					if(smallReserved == "OBJECT CHAR "){
						RecoverChar(CharMap);
					}
					
					if(smallReserved == "OBJECT DOUBLE "){
						RecoverDouble(DoubleMap);
					}
					
					if(smallReserved == "OBJECT FLOAT "){
						RecoverFloat(FloatMap);
					}
					
					if(smallReserved == "OBJECT LONG "){
						RecoverLong(LongMap);
					}
					
					if(smallReserved == "OBJECT SHORT "){
						RecoverShort(ShortMap);
					}
					
					if(smallReserved == "OBJECT BYTE "){
						RecoverByte(ByteMap);
					}
					
					}
				}
			 
			// ints and int arrays END }

			// Conditional explanations END
			if(x == g && (reserved != "MAIN ")){
				reserved = reset;
				smallReserved = reset;
				g=0;
				x=0;
				hold = new ArrayList<>();
				numberhold = new ArrayList<>();
				StringHold =  new ArrayList<>();
				CharHold =  new ArrayList<>();
				DoubleHold =  new ArrayList<>();
				FloatHold =  new ArrayList<>();
				LongHold =  new ArrayList<>();
				ShortHold =  new ArrayList<>();
				ByteHold =  new ArrayList<>();
			}
			
			if((i >= FirstLine)  && ((i <= LastLine))){
				Compilation(i, FirstLine, LastLine, hmap, Methods, reader, writer, out, intMap, intList);
			}
			
			//System.out.print(reader);
			//System.out.println();
			i++;
		}
		
		System.out.println("Now Compiling");
		RealCompilation(writer);
		out.close();
		br.close();
	}
	
	public static void main(String[] args){
		
		FileRead read = new FileRead();
		//System.out.println(writer);
		
	}
	}
	
		
		/**
		 *This class is needed for the compiler to work
		 */
		class JavaSourceFromString extends SimpleJavaFileObject {
		  final String code;

		  JavaSourceFromString(String name, String code) {
		    super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
		    this.code = code;
		  }

		  @Override
		  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		    return code;
		  }
	}

