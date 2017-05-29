package com.arykow.compilers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/*
 * expr -> term exprR
 * 
 * exprR -> + term exprR
 * exprR -> - term exprR
 * exprR -> €
 *
 * term -> factor termR
 * 
 * termR -> * factor termR
 * termR -> / factor termR
 * termR -> €
 * 
 * factor -> ( expr )
 *         | 0 { print ('0') }
 *         | 1 { print ('1') }
 *         ...
 *         | 9 { print ('9') }
 */
public class Analyzer020502a extends Analyzer {

	public Analyzer020502a(InputStream inputStream) {
		super(inputStream);
	}

	public static void main(String[] args) throws IOException {
		final InputStream inputStream = new MyInputStream("10+200*3333+4/5+(6-7)");
		final Analyzer app = new Analyzer020502a(inputStream);
		System.out.println(app.parse(new StringWriter()).toString());
		inputStream.close();
	}

	protected void parse() {
		ruleExpr();
		System.out.println();
	}

	private void ruleExpr() {
		ruleTerm();
		ruleExprR();
	}

	private void ruleExprR() {
		if (this.token() == '+') {
			final int token = this.token();
			this.match(token);
			this.ruleTerm();
			System.out.print(String.format("%c ", token));
			this.ruleExprR();
		} else if (this.token() == '-') {
			final int token = this.token();
			this.match(token);
			this.ruleTerm();
			System.out.print(String.format("%c ", token));
			this.ruleExprR();
		}
	}

	private void ruleTerm() {
		ruleFactor();
		ruleTermR();
	}

	private void ruleTermR() {
		if (this.token() == '*') {
			final int token = this.token();
			this.match(token);
			this.ruleFactor();
			System.out.print(String.format("%c ", token));
			this.ruleTermR();
		} else if (this.token() == '/') {
			final int token = this.token();
			this.match(token);
			this.ruleFactor();
			System.out.print(String.format("%c ", token));
			this.ruleTermR();
		}

	}

	private void ruleFactor() {
		switch (this.token()) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			while (Character.isDigit(this.token())) {
				final int token = this.token();
				this.match(token);
				System.out.print(String.format("%c", token));
			}
			System.out.print(String.format(" "));
			break;
		case '(':
			this.match(this.token());
			this.ruleExpr();
			this.match(')');
			break;
		default:
			throw new Error("Bad factor");
		}
	}
}
