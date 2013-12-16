package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;

import com.burnskids.eclipse.sass.core.SassNature;

public class SassSourceElementParser extends AbstractSourceElementParser {

	@Override
	protected SourceElementRequestVisitor createVisitor() {
		return new SassSourceElementRequestor(getRequestor());
	}

	@Override
	protected String getNatureId() {
		return SassNature.SASS_NATURE;
	}
}
