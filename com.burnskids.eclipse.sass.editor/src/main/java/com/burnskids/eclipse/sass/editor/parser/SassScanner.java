package com.burnskids.eclipse.sass.editor.parser;

import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public class SassScanner extends AbstractScriptScanner {

	private IDocument document;

	public SassScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		this.initialize();
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		this.document = document;
	}

	@Override
	public IToken nextToken() {
		return null;
	}

	@Override
	public int getTokenOffset() {
		return 0;
	}

	@Override
	public int getTokenLength() {
		return document.getLength();
	}

	@Override
	protected List<IRule> createRules() {
		return null;
	}

	@Override
	protected String[] getTokenProperties() {
		return new String[] {};
	}
}
