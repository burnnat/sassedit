package com.burnskids.eclipse.sass.parser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jruby.embed.PathType;
import org.jruby.embed.osgi.OSGiScriptingContainer;
import org.osgi.framework.Bundle;

public class ParserLoadJob extends Job {

	private static final String PARSER_HOOK = "eclipse.rb";

	private Bundle parent;
	private ISourceTokenParser parser;

	public ParserLoadJob(Bundle parent) {
		super("Load SASS Parser");
		this.parent = parent;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		OSGiScriptingContainer container = new OSGiScriptingContainer(parent);
		
		if (monitor.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		
		Object scriptlet;
		
		// When running locally, source files haven't been added to the
		// bundle root, so load directly from the classpath instead.
		if (parent.getEntry(PARSER_HOOK) == null) {
			scriptlet = container.runScriptlet(PathType.CLASSPATH, PARSER_HOOK);
		}
		else {
			scriptlet = container.runScriptlet(parent, PARSER_HOOK);
		}
		
		if (monitor.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		
		parser = container.getInstance(scriptlet, ISourceTokenParser.class);
		
		return Status.OK_STATUS;
	}

	public ISourceTokenParser getParser() throws InterruptedException {
		this.join();
		return parser;
	}
}
