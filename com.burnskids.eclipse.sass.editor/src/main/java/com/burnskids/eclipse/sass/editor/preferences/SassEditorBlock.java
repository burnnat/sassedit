package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class SassEditorBlock extends AbstractConfigurationBlock {

	public SassEditorBlock(OverlayPreferenceStore store) {
		super(store);
	}

	@Override
	public Control createControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Preferences specific to the SASS editor.");
		return label;
	}
}
