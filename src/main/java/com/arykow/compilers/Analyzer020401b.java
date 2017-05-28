package com.arykow.compilers;

import java.io.IOException;
import java.io.InputStream;
/*
 * S -> S ( S ) S | e
 * 
 * S -> e R
 * R -> ( S ) S R | e
 */
import java.io.StringWriter;

public class Analyzer020401b extends Analyzer {

	public Analyzer020401b(InputStream inputStream) {
		super(inputStream);
	}

	public static void main(String[] args) throws IOException {
		final InputStream inputStream = new MyInputStream("((()(((()())()))))()()()");
		final Analyzer app = new Analyzer020401b(inputStream);
		System.out.println(app.parse(new StringWriter()).toString());
		inputStream.close();
	}

	protected void parse() {
		ruleS();
	}

	int i = 0;

	private void ruleS() {
		if (this.token() == '(') {
			match('(');
			i += 1;
			System.out.println(i);
			ruleS();
			match(')');
			i -= 1;
			ruleS();
		}
	}

}
