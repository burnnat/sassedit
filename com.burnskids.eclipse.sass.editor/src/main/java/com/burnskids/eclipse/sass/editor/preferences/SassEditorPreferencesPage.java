package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

import com.burnskids.eclipse.sass.editor.SassPlugin;

public class SassEditorPreferencesPage extends AbstractConfigurationBlockPreferencePage {

	@Override
	protected String getHelpId() {
		return null;
	}
	
	@Override
	protected void setDescription() {
		
	}

	@Override
	protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore store) {
		return new SassEditorBlock(store);
	}

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(SassPlugin.getDefault().getPreferenceStore());
	}
}
