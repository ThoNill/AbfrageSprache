package abfragen;

import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.misc.NotNull;

import abfragen.AbfrageBaseVisitor;
import abfragen.AbfrageParser;
import abfragen.AbfrageParser.RegexpContext;
import abfragen.AbfrageParser.TeilContext;

public class VariableExtractor extends AbfrageBaseListener {
	private Vector namen = new Vector<String>();

	@Override
	public void exitVarname(@NotNull AbfrageParser.VarnameContext ctx) {

		String name = ctx.VARNAME().toString();
		if (!namen.contains(name)) {
			namen.addElement(name);
		}
	}

	public List<String> getNamen() {
		return namen;
	}

}
