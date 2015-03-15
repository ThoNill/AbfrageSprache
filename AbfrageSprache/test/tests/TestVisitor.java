package tests;

import org.antlr.v4.runtime.misc.NotNull;

import abfragen.AbfrageBaseVisitor;
import abfragen.AbfrageParser;
import abfragen.AbfrageParser.TeilContext;

public class TestVisitor extends AbfrageBaseVisitor<TextCreator> {

	@Override
	public TextCreator visitTeil(@NotNull AbfrageParser.TeilContext ctx) {
		if (ctx.TEXT() != null) {
			return new TextConstant(ctx.TEXT().getText());
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
		for( TeilContext tctx : ctx.teil()) {
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
		for( TeilContext tctx : ctx.teil()) {
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
		return new TextVariable(ctx.TEXT().getText());
	}

}
