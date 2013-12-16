package com.burnskids.eclipse.sass.parser;

import java.util.List;

import com.burnskids.eclipse.sass.parser.ast.SassModuleDeclaration;

public interface ISourceTokenParseResult {
	public SassModuleDeclaration getTree();
	public List<ISourceToken> getTokens();
}
