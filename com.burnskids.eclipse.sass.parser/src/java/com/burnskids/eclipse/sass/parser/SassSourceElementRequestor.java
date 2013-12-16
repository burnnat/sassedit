package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.compiler.IElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;

public class SassSourceElementRequestor extends SourceElementRequestVisitor {

	public SassSourceElementRequestor(IElementRequestor requesor) {
		super(requesor);
	}

}
