package com.burnskids.eclipse.sass.parser;

import java.util.List;

public interface ISourceParser {
	public void setDebug(boolean debug);
	public List<ISourceToken> parse(String string);
}
