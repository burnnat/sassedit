package com.burnskids.eclipse.sass.editor.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;

import com.burnskids.eclipse.sass.editor.SassPlugin;
import com.burnskids.eclipse.sass.editor.core.SassLanguageToolkit;

public class SassEditor extends ScriptEditor {
	
	public static final String EDITOR_ID = "com.burnskids.sass.editor";
	public static final String EDITOR_CONTEXT = "#SassEditorContext";
	
	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
	}

	@Override
	public String getEditorId() {
		return EDITOR_ID;
	}
	
	@Override
	public IPreferenceStore getScriptPreferenceStore() {
		return SassPlugin.getDefault().getPreferenceStore();
	}

	@Override
	public IDLTKLanguageToolkit getLanguageToolkit() {
		return SassLanguageToolkit.getDefault();
	}
	
	@Override
	public ScriptTextTools getTextTools() {
		return SassPlugin.getDefault().getTextTools();
	}
}
