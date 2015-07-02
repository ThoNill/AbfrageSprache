package tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import abfragen.AbfrageLexer;
import abfragen.AbfrageParser;
import abfragen.TextCreator;
import abfragen.TextVisitor;
import abfragen.VariableExtractor;

public class AbfragenTest2 {

	public AbfragenTest2() {
	}

	@Test
	public void firstTest1() {

		teste(null,"das ist ein ");
	}
	
	@Test
	public void firstTest2() {
		teste("a,b","das ist ein {{test ${a} ${b} }}");

	}
	
	protected void teste(String expectedString,String text) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
			ANTLRInputStream input;
			input = new ANTLRInputStream(in);
			AbfrageLexer lexer = new AbfrageLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			AbfrageParser parser = new AbfrageParser(tokenStream);

			System.out.println("Parsen ...");
			ParseTree tree = parser.text();
			
			ParseTreeWalker walker = new ParseTreeWalker();
			
			VariableExtractor variablenExtrahieren = new VariableExtractor();

			walker.walk(variablenExtrahieren, tree);
			
			String namen[] = (expectedString==null) ? new String[] {}: expectedString.split(" *, *");
			
		
			System.out.println(variablenExtrahieren.getNamen());

			Assert.assertEquals(namen,variablenExtrahieren.getNamen().toArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
