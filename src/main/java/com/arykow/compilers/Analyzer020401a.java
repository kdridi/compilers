package com.arykow.compilers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/*
 * S -> + S S | - S S | a
 */
public class Analyzer020401a extends Analyzer {

	public Analyzer020401a(InputStream inputStream) {
		super(inputStream);
	}

	public static void main(String[] args) throws IOException {
		final InputStream inputStream = new MyInputStream("++aa-a+aa");
		final Analyzer app = new Analyzer020401a(inputStream);
		System.out.println(app.parse(new StringWriter()).toString());
		inputStream.close();
	}

	protected void parse() {
		ruleS();
		System.out.print('\n');
	}

	private void ruleS() {
		switch (this.token()) {
		case 'a':
			match('a');
			System.out.print('a');
			break;
		case '+':
			System.out.print('(');
			match('+');
			ruleS();
			System.out.print('+');
			ruleS();
			System.out.print(')');
			break;
		case '-':
			System.out.print('(');
			match('-');
			ruleS();
			System.out.print('-');
			ruleS();
			System.out.print(')');
			break;
		default:
			throw new Error("Bad token");
		}
	}

}
