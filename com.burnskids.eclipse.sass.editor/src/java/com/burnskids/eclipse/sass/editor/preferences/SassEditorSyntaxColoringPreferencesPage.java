package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

import com.burnskids.eclipse.sass.editor.SassEditorPlugin;

public class SassEditorSyntaxColoringPreferencesPage extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(SassEditorPlugin.getDefault().getPreferenceStore());
	}

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore store) {
		return new SassEditorSyntaxColoringBlock(store);
	}
}
