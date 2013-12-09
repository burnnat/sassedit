package com.burnskids.eclipse.sass.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.burnskids.eclipse.sass.parser.ISourceParser;
import com.burnskids.eclipse.sass.parser.ISourceToken;
import com.burnskids.eclipse.sass.parser.SassParserPlugin;

public class ParserTest {

	private static final String SASS_EXTENSION = "scss";
	private static final String EXPECT_EXTENSION = "txt";

	private static ISourceParser parser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		parser = SassParserPlugin.getDefault().getParser();
		parser.setDebug(true);
	}

	private static InputStream getResource(String name, String extension) {
		return ParserTest.class.getResourceAsStream(name + "." + extension);
	}

	private static void testTokens(String name) throws IOException {
		String sass = IOUtils.toString(getResource(name, SASS_EXTENSION), "UTF-8");
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
	public void testFunctions() throws IOException {
		testTokens("functions");
	}

	@Test
	public void testVariables() throws IOException {
		testTokens("variables");
	}
}
