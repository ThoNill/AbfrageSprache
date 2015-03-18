package abfragen;

import java.util.List;

import java.util.Vector;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.misc.NotNull;

import abfragen.AbfrageBaseVisitor;
import abfragen.AbfrageParser;
import abfragen.AbfrageParser.RegexpContext;
import abfragen.AbfrageParser.TeilContext;

public class TextVisitor extends AbfrageBaseVisitor<TextCreator> {

	@Override
	public TextCreator visitTeil(@NotNull AbfrageParser.TeilContext ctx) {
		if (ctx.TEXT() != null) {
			return new TextConstant(ctx.TEXT().getText());
		}
		if (ctx.VARNAME() != null) {
			return new TextConstant(ctx.VARNAME().getText());
		}
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public TextCreator visitText(@NotNull AbfrageParser.TextContext ctx) {
		TextTeil text = new TextTeil();
		for (TeilContext tctx : ctx.teil()) {
			text.addTextCreator(visitTeil(tctx));
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public TextCreator visitOptional(@NotNull AbfrageParser.OptionalContext ctx) {
		TextTeil text = new TextOptional();
		for (TeilContext tctx : ctx.teil()) {
			text.addTextCreator(visitTeil(tctx));
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public TextCreator visitVarname(@NotNull AbfrageParser.VarnameContext ctx) {
		Vector<Pattern> regexpList = null;
		if (!ctx.regexp().isEmpty()) {
			regexpList = new Vector<Pattern>();
			for (RegexpContext regexpContext : ctx.regexp()) {
				String re = null;
				re = regexpContext.getText();
				re = re.substring(1, re.length()-1); // die / / weg 
				
				regexpList.add(Pattern.compile(re));
			}
		}
		return new TextVariable(ctx.VARNAME().getText(),regexpList);
	}

}
