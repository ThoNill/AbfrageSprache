package tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import abfragen.AbfrageLexer;
import abfragen.AbfrageParser;
import abfragen.TextCreator;
import abfragen.TextVisitor;

public class AbfragenTest {

	public AbfragenTest() {
	}

	@Test
	public void firstTest1() {

		String text = "das ist ein ";
		Hashtable<String, String> hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		text = "das ist ein {{test ${a} }}";
		hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		hash.put("a","inhalt");
		
		teste("das ist ein test inhalt ",text,hash);

	}
	
	@Test
	public void firstTest2() {

		String text = "das ist ein ";
		Hashtable<String, String> hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		text = "das ist ein {{test ${a/3/} }}";
		hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		hash.put("a","inhalt");
		
		teste("das ist ein ",text,hash);
		
		hash.put("a","36");
		
		teste("das ist ein test 36 ",text,hash);
		
		text = "das ist ein {{test ${a/[1-9]*/} }}";
		
		teste("das ist ein test 36 ",text,hash);

	}

	protected void teste(String expected,String text,Hashtable<String, String> hash) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
			ANTLRInputStream input;
			input = new ANTLRInputStream(in);
			AbfrageLexer lexer = new AbfrageLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			AbfrageParser parser = new AbfrageParser(tokenStream);

			System.out.println("Parsen ...");
			ParseTree tree = parser.text();

			System.out.println(tree.getText());

			TextVisitor visitor = new TextVisitor();

			TextCreator textCreator = visitor.visit(tree);


			Assert.assertEquals(expected, textCreator.create(hash));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
