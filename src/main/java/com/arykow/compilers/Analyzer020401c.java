package com.arykow.compilers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class Analyzer020401c extends Analyzer {

	public Analyzer020401c(InputStream inputStream) {
		super(inputStream);
	}

	public static void main(String[] args) throws IOException {
		final InputStream inputStream = new MyInputStream("00110");
		final Analyzer app = new Analyzer020401c(inputStream);
		System.out.println(app.parse(new StringWriter()).toString());
		inputStream.close();
	}

	protected void parse() {
		ruleS();
	}

	private void ruleS() {
		switch (this.token()) {
		case '0':
			match('0');
			ruleR();
			break;
		default:
			throw new Error("Bad parsing");
		}
	}

	private void ruleR() {
		if (this.token() == '1') {
			match('1');
		} else {
			ruleS();
			match('1');
		}
	}

}
