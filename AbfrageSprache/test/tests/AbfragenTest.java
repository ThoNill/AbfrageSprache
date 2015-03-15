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

public class AbfragenTest {

	public AbfragenTest() {
	}

	@Test
	public void firstTest() {

		String text = "das ist ein ";
		Hashtable<String, String> hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		text = "das ist ein {{test ${a} }}";
		hash = new Hashtable<>();

		teste("das ist ein ",text,hash);
		
		hash.put("a","inhalt");
		
		teste("das ist ein test inhalt ",text,hash);

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

			TestVisitor visitor = new TestVisitor();

			TextCreator textCreator = visitor.visit(tree);


			Assert.assertEquals(expected, textCreator.create(hash));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
