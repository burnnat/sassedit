package com.burnskids.eclipse.sass.parser;

import org.eclipse.core.runtime.Plugin;
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
		
		plugin = this;
		container = new OSGiScriptingContainer(getBundle());
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

	public ISourceParser getParser() {
		Bundle bundle = getBundle();
		Object parser;
		
		// When running locally, source files haven't been added to the
		// bundle root, so load directly from the classpath instead.
		if (bundle.getEntry(PARSER_HOOK) == null) {
			parser = container.runScriptlet(PathType.CLASSPATH, PARSER_HOOK);
		}
		else {
			parser = container.runScriptlet(bundle, PARSER_HOOK);
		}
		
		return container.getInstance(parser, ISourceParser.class);
	}
}
