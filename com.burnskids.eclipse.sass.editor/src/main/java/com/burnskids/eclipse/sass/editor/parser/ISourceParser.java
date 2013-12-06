package com.burnskids.eclipse.sass.editor.parser;

import java.util.List;

public interface ISourceParser {
	List<ISourceToken> parse(String string);
}
