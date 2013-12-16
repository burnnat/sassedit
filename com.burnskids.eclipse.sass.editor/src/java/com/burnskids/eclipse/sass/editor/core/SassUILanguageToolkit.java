package com.burnskids.eclipse.sass.editor.core;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.jface.preference.IPreferenceStore;

import com.burnskids.eclipse.sass.core.SassLanguageToolkit;
import com.burnskids.eclipse.sass.editor.SassEditorPlugin;

public class SassUILanguageToolkit extends AbstractDLTKUILanguageToolkit {

	@Override
	public IDLTKLanguageToolkit getCoreToolkit() {
		return SassLanguageToolkit.getDefault();
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		return SassEditorPlugin.getDefault().getPreferenceStore();
	}
}
