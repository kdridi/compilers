package com.arykow.compilers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class Analyzer {
	private final InputStream inputStream;
	private int token;
	private boolean eof;

	protected boolean eof() {
		return this.eof;
	}

	protected int token() {
		return this.token;
	}

	private int readNextToken() {
		int len;
		byte b[] = new byte[1];
		try {
			while (true) {
				len = this.inputStream.read(b);
				if (eof = (len == -1))
					break;

				if (len == 1)
					break;
			}
		} catch (IOException e) {
			throw new Error(e);
		}
		return b[0];
	}

	public Analyzer(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
		this.token = this.readNextToken();
	}

	public <W extends Writer> W parse(W w) {
		parse();

		if (w != null)
			return writeRemainsTo(w);

		return w;
	}

	protected abstract void parse();

	protected void match(int token) {
		if (this.token == token) {
			this.token = this.readNextToken();
		} else {
			throw new Error("Bad token");
		}
	}

	private <W extends Writer> W writeRemainsTo(final W w) {
		final PrintWriter writer = new PrintWriter(w);

		writer.print("remains: [");
		while (!this.eof()) {
			writer.print((char) this.token());
			this.match(this.token());
		}
		writer.print("]");
		writer.close();

		return w;
	}
}
