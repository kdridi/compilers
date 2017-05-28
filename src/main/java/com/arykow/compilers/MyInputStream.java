package com.arykow.compilers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class MyInputStream extends InputStream {
	private final InputStream inputStream;
	private final boolean closeOnExit;

	public MyInputStream(final File file) {
		this(toInputStream(file), true);
	}

	public MyInputStream(final String data) {
		this(toInputStream(data), true);
	}

	public MyInputStream(InputStream inputStream) {
		this(inputStream, false);
	}

	private MyInputStream(InputStream inputStream, boolean closeOnExit) {
		super();
		this.inputStream = inputStream;
		this.closeOnExit = closeOnExit;
	}

	@Override
	protected void finalize() throws Throwable {
		if (closeOnExit)
			inputStream.close();
		super.finalize();
	}

	private static InputStream toInputStream(final File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new Error(e);
		}
	}

	private static InputStream toInputStream(final String str) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintWriter writer = new PrintWriter(baos);
		writer.print(str);
		writer.close();
		try {
			baos.close();
		} catch (IOException e) {
			throw new Error(e);
		}

		final InputStream istream = new ByteArrayInputStream(baos.toByteArray());
		return istream;
	}

	public int read() throws IOException {
		return inputStream.read();
	}

	public int hashCode() {
		return inputStream.hashCode();
	}

	public int read(byte[] b) throws IOException {
		return inputStream.read(b);
	}

	public boolean equals(Object obj) {
		return inputStream.equals(obj);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		return inputStream.read(b, off, len);
	}

	public long skip(long n) throws IOException {
		return inputStream.skip(n);
	}

	public String toString() {
		return inputStream.toString();
	}

	public int available() throws IOException {
		return inputStream.available();
	}

	public void close() throws IOException {
		inputStream.close();
	}

	public void mark(int readlimit) {
		inputStream.mark(readlimit);
	}

	public void reset() throws IOException {
		inputStream.reset();
	}

	public boolean markSupported() {
		return inputStream.markSupported();
	}

}
