package com.burnskids.eclipse.sass.core;

import org.eclipse.dltk.core.AbstractLanguageToolkit;

import com.burnskids.eclipse.sass.SassPlugin;

public class SassLanguageToolkit extends AbstractLanguageToolkit {
	private static SassLanguageToolkit toolkit;
	
	public static SassLanguageToolkit getDefault() {
		if (toolkit == null) {
			toolkit = new SassLanguageToolkit();
		}
		
		return toolkit;
	}

	@Override
	public String getLanguageName() {
		return "Sass";
	}

	@Override
	public String getNatureId() {
		return SassNature.SASS_NATURE;
	}

	@Override
	public String getLanguageContentType() {
		return SassPlugin.PLUGIN_ID + ".content-type";
	}
}
