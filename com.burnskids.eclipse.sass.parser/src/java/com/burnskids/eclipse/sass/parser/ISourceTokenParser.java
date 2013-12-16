package com.burnskids.eclipse.sass.parser;

import java.util.List;

public interface ISourceTokenParser {
	public void setDebug(boolean debug);
	public ISourceTokenParseResult parse(String string);
}
