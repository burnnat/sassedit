package com.burnskids.eclipse.sass.editor.ui;

import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

import com.burnskids.eclipse.sass.editor.SassEditorPlugin;

public class SassContentAssistPreference extends ContentAssistPreference {
	
	private static SassContentAssistPreference instance;
	 
	public static ContentAssistPreference getDefault() {
		if (instance == null) {
			instance = new SassContentAssistPreference();
		}
		
		return instance;
	}
	
	@Override
	protected ScriptTextTools getTextTools() {
		return SassEditorPlugin.getDefault().getTextTools();
	}
}
