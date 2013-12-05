package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import com.burnskids.eclipse.sass.editor.SassPlugin;

public class SassGlobalPreferencesPage extends AbstractConfigurationBlockPropertyAndPreferencePage {
	
	private static final String PROPERTY_PAGE_ID = "com.burnskids.sasseditor.propertyPage";
	private static final String PREFERENCE_PAGE_ID = "com.burnskids.sasseditor.preferences";
	
	@Override
	protected AbstractOptionsBlock createOptionsBlock(
		IStatusChangeListener newStatusChangedListener,
		IProject project,
		IWorkbenchPreferenceContainer container
	) {
		return new SassGlobalBlock(newStatusChangedListener, project, new PreferenceKey[0], container);
	}

	@Override
	protected String getHelpId() {
		return null;
	}

	@Override
	protected String getProjectHelpId() {
		return null;
	}

	@Override
	protected void setDescription() {
		
	}

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(SassPlugin.getDefault().getPreferenceStore());
	}

	@Override
	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	@Override
	protected String getPropertyPageId() {
		return PROPERTY_PAGE_ID;
	}

}
