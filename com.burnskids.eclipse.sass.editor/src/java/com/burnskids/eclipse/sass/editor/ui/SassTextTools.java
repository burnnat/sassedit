package com.burnskids.eclipse.sass.editor.ui;

import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.ui.texteditor.ITextEditor;

public class SassTextTools extends ScriptTextTools {
	
	public SassTextTools(boolean autoDisposeOnDisplayDispose) {
		super(IDocumentExtension3.DEFAULT_PARTITIONING, new String[] { IDocument.DEFAULT_CONTENT_TYPE }, autoDisposeOnDisplayDispose);
	}
 
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore,
			ITextEditor editor,
			String partitioning
	) {
		return new SassSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
	}
}
