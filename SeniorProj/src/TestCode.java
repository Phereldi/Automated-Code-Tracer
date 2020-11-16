import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;



public class TestCode {

	static class JavaSourceFromString extends SimpleJavaFileObject {
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
	
	public static final String EXAMPLE_TEST = "This int hello [] is my small example string which I'm going to use for pattern matching.";

	/**
	 * 
	 * @param A
	 * @param p
	 *            = start of the array
	 * @param r
	 *            = end of the array
	 */
	public static void Quicksort(int A[], int p, int r) {
		if (p < r) {
			int q = Partition(A, p, r);
			Quicksort(A, p, q - 1);
			Quicksort(A, q + 1, r);
		}
	}

	public static void RandomizedQuicksort(int A[], int p, int r) {
		if (p < r) {
			Random z = new Random();
			int someint = z.nextInt(r - p + 1) + p;
			int swap = A[someint];
			A[someint] = A[r];
			A[r] = swap;
			int q = Partition(A, p, r);
			RandomizedQuicksort(A, p, q - 1);
			RandomizedQuicksort(A, q + 1, r);
		}
	}

	private static int Partition(int A[], int p, int r) {
		int pivot = A[r];
		int first = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] <= pivot) {
				first++;
				int hold = A[first];
				A[first] = A[j];
				A[j] = hold;
			}
			// System.out.println(java.util.Arrays.toString(A));
			// System.out.print(p + " ");
			// System.out.print(r);
			// System.out.println("");
		}
		int swap = A[first + 1];
		A[first + 1] = A[r];
		A[r] = swap;
		System.out.println(swap);
		return first + 1;
	}

	public static Class<?> parseType(final String className) {
	    switch (className) {
	        case "boolean":
	            return boolean.class;
	        case "byte":
	            return byte.class;
	        case "short":
	            return short.class;
	        case "int":
	            return int.class;
	        case "long":
	            return long.class;
	        case "float":
	            return float.class;
	        case "double":
	            return double.class;
	        case "char":
	            return char.class;
	        case "void":
	            return void.class;
	        default:
	            String fqn = className.contains(".") ? className : "java.lang.".concat(className);
	            try {
	                return Class.forName(fqn);
	            } catch (ClassNotFoundException ex) {
	                throw new IllegalArgumentException("Class not found: " + fqn);
	            }
	    }
	}
	public static int makeInt(String name, int value){
		return 5;
	}
	
	public static void main(String[] args) throws InstantiationException,  IOException  {
		//String words = "   private static int Partition(int A[], int p, int r) {";
		String words = "5";
		words = words.trim();

		String reservations[] = { "abstract", "assert", "boolean", "byte",
				"catch", "char", "const", "continue", "default", "double",
				"do", "else", "enum", "extends", "final", "finally", "float",
				"implements", "instanceof", "int", "interface", "long",
				"native", "new", "package", "private", "protected", "public",
				"return", "short", "static", "String", "strictfp", "super",
				"void", "volitile" };
		for (int j = 0; j <= 1; j++) {

			for (int i = 0; i <= reservations.length - 1; i++) {
				String change = reservations[i];
				try {
					if (words.substring(0, change.length()).contains(change)) {
						String newWord = words.substring(change.length() + 1);
						System.out.println(newWord);
						words = newWord;
						words = words.trim();
					}
					if (words.contains("(")) {
						System.out.println(words.indexOf("("));
						int punct = words.indexOf("(");
						words = words.substring(0, punct);
						words = words.trim();
						System.out.println(words);
					} else if (words.contains("=")) {
						System.out.println(words.indexOf("="));
						int punct = words.indexOf("=");
						words = words.substring(0, punct);
						words = words.trim();
						System.out.println(words);
					}
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
		System.out.println(words);
		System.out.println(words.replace(words, "TestCode." + words));

		int A[] = { 15, 8, 41, 3, 86, 50, 38, 12, 52, 70 };
		System.out.println(java.util.Arrays.toString(A));

		Quicksort(A, 0, A.length - 1);
		System.out.println("The sorted array");
		System.out.println(java.util.Arrays.toString(A));
		System.out.println("");
		int B[] = { 26, 98, 41, 3, 16, 50, 38, 12, 52, 70, 109, 13 };

		// System.out.println(java.util.Arrays.toString(B));
		// RandomizedQuicksort(B, 0, B.length - 1);
		// System.out.println(java.util.Arrays.toString(B));

		Pattern pattern = Pattern.compile("int \\w+ \\[]");
		// in case you would like to ignore case sensitivity,
		// you could use this statement:
		// Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(EXAMPLE_TEST);
		// check all occurance
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
		

List<Object> intList = new ArrayList<>();
String m = "int j = 5";

Object n [] = {"Zero", "One", "Two", "Three"};
if (m.contains("String")){
	n[1] = m;
	System.out.println(n[1].getClass());
}

	String reserved = "";
	Pattern intOnly = Pattern.compile("int \\w+ =");
	Matcher intMatch = intOnly.matcher(m);
	if (intMatch.find()) {
		reserved = "OBJECT INT ";
		System.out.println(reserved);
	}

	if (reserved =="OBJECT INT "){
		System.out.println("Start");
		String name=m;
		name = name.trim();
		for(int i =0; i< name.length();i++){
			
			if (name.contains("=")){
				if(i == name.indexOf("=")){
				name = name.trim();
				String equals  = name.substring(i+1, name.length());
				name = name.substring(4,i-1);
				equals = equals.trim();
				int value = Integer.parseInt(equals);
				System.out.println(value);
				System.out.println(name + value);
				Hashtable <String, Integer> hmap = new Hashtable <String, Integer>();
				hmap.put(name, value);
				hmap.put(name, value+1);
				System.out.println(hmap);

			}
		}
	}
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/reiki/git/SeniorProject/SeniorProj/src/TrinarySearch.java"));
		String reader = "";
		System.setProperty("java.home","C:\\Program Files\\Java\\jdk1.8.0_201");
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
	    StringWriter writer = new StringWriter();
	    PrintWriter out = new PrintWriter(writer);
	    

	    while ((reader = (br.readLine())) != null){
	    	if(!(reader.contains("import"))){
	    		out.println(reader);
	    	}
	    }

	    out.close();
	    JavaFileObject file  =  new JavaSourceFromString("TrinarySearch", writer.toString());
	    Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
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
	    System.out.println("Success: " + success);

	    if (success) {
	      try {

	          URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
	          System.out.println("DONE");
	          Class.forName("TrinarySearch", true, classLoader).getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });

	      } catch (ClassNotFoundException e) {
	        System.err.println("Class not found: " + e);
	      } catch (NoSuchMethodException e) {
	        System.err.println("No such method: " + e);
	      } catch (IllegalAccessException e) {
	        System.err.println("Illegal access: " + e);
	      } catch (InvocationTargetException e) {
	        System.err.println("Invocation target: " + e);
	      } catch (NullPointerException e) {
		        System.err.println("Class not found: " + e);
	      
	    }
	  }
	}
	 int a = 2;
	 int b = 4;
	 int c = a + b;
	 System.out.format("The sum of %d  and  %d is %d ", a, b, c);
		// now create a new pattern and matcher to replace whitespace with tabs
		//Pattern replace = Pattern.compile("\\s+");
	//	Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
	//	System.out.println(matcher2.replaceAll("\t"));
	
}}
