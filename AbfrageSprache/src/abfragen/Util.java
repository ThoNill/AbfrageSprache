package abfragen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Util {

	public Util() {

	}

	public static List<String> extractVariables(String text) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
			ANTLRInputStream input = new ANTLRInputStream(in);
			AbfrageLexer lexer = new AbfrageLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			AbfrageParser parser = new AbfrageParser(tokenStream);
			ParseTree tree = parser.text();

			ParseTreeWalker walker = new ParseTreeWalker();

			VariableExtractor variablenExtrahieren = new VariableExtractor();

			walker.walk(variablenExtrahieren, tree);

			return variablenExtrahieren.getNamen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Vector<String>();
	}

}
