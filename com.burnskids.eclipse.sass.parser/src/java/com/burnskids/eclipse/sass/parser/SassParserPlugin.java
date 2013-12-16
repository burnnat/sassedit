package com.burnskids.eclipse.sass.parser;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.jruby.embed.PathType;
import org.jruby.embed.osgi.OSGiScriptingContainer;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class SassParserPlugin extends Plugin {

	public static final String PLUGIN_ID = "com.burnskids.eclipse.sass.parser";
	private static final String PARSER_HOOK = "eclipse.rb";
	
	private static SassParserPlugin plugin;
	
	private OSGiScriptingContainer container;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		long start = System.currentTimeMillis();
		
		plugin = this;
		container = new OSGiScriptingContainer(getBundle());
		
		long end = System.currentTimeMillis();
		
		getLog().log(new Status(Status.INFO, PLUGIN_ID, "JRuby container loaded in " + ((end - start) / 1000.0) + " seconds"));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * @return
	 */
	public static SassParserPlugin getDefault() {
		return plugin;
	}

	public ISourceTokenParser getParser() {
		Bundle bundle = getBundle();
		Object parser;
		
		long start = System.currentTimeMillis();
		
		// When running locally, source files haven't been added to the
		// bundle root, so load directly from the classpath instead.
		if (bundle.getEntry(PARSER_HOOK) == null) {
			parser = container.runScriptlet(PathType.CLASSPATH, PARSER_HOOK);
		}
		else {
			parser = container.runScriptlet(bundle, PARSER_HOOK);
		}
		
		long end = System.currentTimeMillis();
		
		getLog().log(new Status(Status.INFO, PLUGIN_ID, "Sass parser loaded in " + ((end - start) / 1000.0) + " seconds"));
		
		return container.getInstance(parser, ISourceTokenParser.class);
	}
}
