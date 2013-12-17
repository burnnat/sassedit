package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class SassSourceParser extends AbstractSourceParser {

	@Override
	public IModuleDeclaration parse(IModuleSource input, IProblemReporter reporter) {
		try {
			return SassParserPlugin.getDefault().getParser().parse(input.getSourceContents()).getTree();
		}
		catch (InterruptedException e) {
			return null;
		}
	}
}
