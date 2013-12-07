package com.burnskids.eclipse.sass.editor.parser;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserTest {

	private static final String SASS_EXTENSION = "scss";
	private static final String EXPECT_EXTENSION = "txt";

	private static ISourceParser parser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ScriptingContainer container = new ScriptingContainer();
		
		parser = container.getInstance(
			container.runScriptlet(PathType.CLASSPATH, "eclipse.rb"),
			ISourceParser.class
		);
		
		parser.setDebug(true);
	}

	private static InputStream getResource(String name, String extension) {
		return ParserTest.class.getResourceAsStream(name + "." + extension);
	}

	private static String readString(InputStream input) throws IOException {
		StringBuffer buffer = new StringBuffer();
		Reader reader = new BufferedReader(
			new InputStreamReader(input, Charset.forName("UTF-8"))
		);
		
		char[] buf = new char[1024];
		int numRead = 0;
		
		try {
			while ((numRead=reader.read(buf)) != -1) {
				buffer.append(String.valueOf(buf, 0, numRead));
			}
		}
		finally {
			reader.close();
		}
		
		return buffer.toString();
	}

	private static void testTokens(String name) throws IOException {
		String sass = readString(getResource(name, SASS_EXTENSION));
		List<ISourceToken> tokens = parser.parse(sass);
		
		Scanner expected = new Scanner(getResource(name, EXPECT_EXTENSION));
		
		int i = 0;
		
		try {
			while (expected.hasNext()) {
				ISourceToken token = tokens.get(i);
				
				assertEquals(expected.next(), token.getType());
				
				assertEquals(
					expected.next(),
					sass.substring(token.getOffset(), token.getOffset() + token.getLength())
				);
				
				i++;
			}
		}
		finally {
			expected.close();
		}
	}

	@Test
	public void testExpressions() throws IOException {
		testTokens("expressions");
	}

	@Test
	public void testVariables() throws IOException {
		testTokens("variables");
	}
}
