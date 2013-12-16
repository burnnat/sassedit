package com.burnskids.eclipse.sass.parser;

public interface ISourceTokenParser {
	public void setDebug(boolean debug);
	public ISourceTokenParseResult parse(String string);
}
