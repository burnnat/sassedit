package com.burnskids.eclipse.sass.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.compiler.env.IModuleSource;
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
	
	@Override
	public void parseSourceModule(final IModuleSource module) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				parseSourceModuleSync(module);
			}
		}).start();
	}
	
	private void parseSourceModuleSync(IModuleSource module) {
		super.parseSourceModule(module);
	}
}
