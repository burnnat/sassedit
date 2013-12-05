package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

import com.burnskids.eclipse.sass.editor.SassPlugin;

public class SassEditorSyntaxColoringPreferencesPage extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(SassPlugin.getDefault().getPreferenceStore());
	}

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore store) {
		return new SassEditorSyntaxColoringBlock(store);
	}
}
