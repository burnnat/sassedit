package com.burnskids.eclipse.sass.parser;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class SassParserPlugin extends Plugin {

	public static final String PLUGIN_ID = "com.burnskids.eclipse.sass.parser";
	
	private static SassParserPlugin plugin;
	
	private ParserLoadJob loader;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		plugin = this;
		
		loader = new ParserLoadJob(getBundle());
		loader.schedule();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		loader.cancel();
		
		super.stop(context);
	}

	/**
	 * @return
	 */
	public static SassParserPlugin getDefault() {
		return plugin;
	}

	public ISourceTokenParser getParser() throws InterruptedException {
		IStatus status = loader.getResult();
		
		if (status != null && !status.isOK()) {
			loader.schedule();
		}
		
		return loader.getParser();
	}
}
