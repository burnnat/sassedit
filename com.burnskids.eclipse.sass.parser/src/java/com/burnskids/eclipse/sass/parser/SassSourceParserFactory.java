package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

public class SassSourceParserFactory implements ISourceParserFactory {
	@Override
	public ISourceParser createSourceParser() {
		return new SassSourceParser();
	}
}
