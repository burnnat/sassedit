package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class SassSourceParser implements ISourceParser {

	@Override
	public IModuleDeclaration parse(IModuleSource input, IProblemReporter reporter) {
		return SassParserPlugin.getDefault().getParser().parse(input.getSourceContents()).getTree();
	}
}
