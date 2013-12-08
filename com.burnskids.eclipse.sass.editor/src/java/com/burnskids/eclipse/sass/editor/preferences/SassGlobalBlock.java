package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class SassGlobalBlock extends AbstractOptionsBlock {

	public SassGlobalBlock(
		IStatusChangeListener context,
		IProject project,
		PreferenceKey[] allKeys,
		IWorkbenchPreferenceContainer container
	) {
		super(context, project, allKeys, container);
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("General preferences when working with SASS files.");
		return label;
	}
}
