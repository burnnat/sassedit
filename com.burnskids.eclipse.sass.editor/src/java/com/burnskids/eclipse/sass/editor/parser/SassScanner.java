package com.burnskids.eclipse.sass.editor.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import com.burnskids.eclipse.sass.editor.ui.ISassColorConstants;
import com.burnskids.eclipse.sass.parser.ISourceTokenParser;
import com.burnskids.eclipse.sass.parser.ISourceToken;
import com.burnskids.eclipse.sass.parser.SassParserPlugin;

public class SassScanner extends AbstractScriptScanner {
	
	private static final Map<String, String> TOKEN_TYPES = new HashMap<String, String>();
	
	static {
		TOKEN_TYPES.put(ISourceToken.DEFAULT, ISassColorConstants.DEFAULT);
		TOKEN_TYPES.put(ISourceToken.BLOCK_COMMENT, ISassColorConstants.COMMENT_BLOCK);
		TOKEN_TYPES.put(ISourceToken.SINGLE_COMMENT, ISassColorConstants.COMMENT_SINGLE);
		TOKEN_TYPES.put(ISourceToken.STRUCTURE, ISassColorConstants.STRUCTURE);
		TOKEN_TYPES.put(ISourceToken.OPERATOR, ISassColorConstants.OPERATOR);
		TOKEN_TYPES.put(ISourceToken.STRING, ISassColorConstants.STRING);
		TOKEN_TYPES.put(ISourceToken.NUMBER, ISassColorConstants.NUMBER);
		TOKEN_TYPES.put(ISourceToken.COLOR, ISassColorConstants.COLOR);
		TOKEN_TYPES.put(ISourceToken.BOOLEAN, ISassColorConstants.BOOLEAN);
		TOKEN_TYPES.put(ISourceToken.DIRECTIVE, ISassColorConstants.DIRECTIVE);
		TOKEN_TYPES.put(ISourceToken.VARIABLE, ISassColorConstants.VARIABLE);
		TOKEN_TYPES.put(ISourceToken.FUNCTION, ISassColorConstants.FUNCTION);
		TOKEN_TYPES.put(ISourceToken.MIXIN, ISassColorConstants.MIXIN);
		TOKEN_TYPES.put(ISourceToken.NULL, ISassColorConstants.NULL);
		TOKEN_TYPES.put(ISourceToken.KEYWORD, ISassColorConstants.KEYWORD);
		TOKEN_TYPES.put(ISourceToken.SELECTOR, ISassColorConstants.SELECTOR);
		TOKEN_TYPES.put(ISourceToken.PROPERTY, ISassColorConstants.PROPERTY);
		TOKEN_TYPES.put(ISourceToken.INTERPOLATION, ISassColorConstants.INTERPOLATION);
	}

	private IDocument document;
	private ISourceTokenParser parser;
	
	private List<ISourceToken> tokens;
	private ISourceToken currentToken;
	private int nextIndex;

	public SassScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		this.initialize();
		
		parser = SassParserPlugin.getDefault().getParser();
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		this.document = document;
		
		tokens = new ArrayList<ISourceToken>();
		nextIndex = 0;
		
		try {
			tokens.addAll(parser.parse(document.get(offset, length)).getTokens());
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public IToken getTokenForSource(ISourceToken source) {
		return this.getToken(
			TOKEN_TYPES.get(source.getType())
		);
	}

	@Override
	public IToken nextToken() {
		try {
			currentToken = tokens.get(nextIndex++);
			
			int offset = currentToken.getOffset();
			int length = currentToken.getLength();
			
			System.out.println(
				"[" + currentToken.getType() + "] " +
				"(" + offset + ", " + length + "): " +
				document.get(offset, length)
			);
			
			return this.getTokenForSource(currentToken);
		}
		catch (IndexOutOfBoundsException e) {
			currentToken = null;
			return Token.EOF;
		}
		catch (Exception e) {
			currentToken = null;
			return this.getToken(ISassColorConstants.DEFAULT);
		}
	}

	@Override
	public int getTokenOffset() {
		if (currentToken == null)
			return 0;
		
		return currentToken.getOffset();
	}

	@Override
	public int getTokenLength() {
		if (currentToken == null)
			return document.getLength();
		
		return currentToken.getLength();
	}

	@Override
	protected List<IRule> createRules() {
		return null;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKEN_TYPES.values().toArray(new String[0]);
	}
}
