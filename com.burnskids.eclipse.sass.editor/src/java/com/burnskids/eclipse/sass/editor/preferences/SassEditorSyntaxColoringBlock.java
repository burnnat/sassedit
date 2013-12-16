package com.burnskids.eclipse.sass.editor.preferences;

import java.io.InputStream;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

import com.burnskids.eclipse.sass.editor.SassEditorPlugin;
import com.burnskids.eclipse.sass.editor.ui.ISassColorConstants;
import com.burnskids.eclipse.sass.editor.ui.SassSourceViewerConfiguration;

public class SassEditorSyntaxColoringBlock
		extends AbstractScriptEditorColoringConfigurationBlock
		implements IPreferenceConfigurationBlock {
	
	private static final String PREVIEW_FILE_NAME = "preview.scss";

	private static String sSASSCategory = "SASS";
	private static String sCSSCategory = "CSS";
	
	private static final String[][] listModel = {
		{ ISassPreferenceMessages.DEFAULT, ISassColorConstants.DEFAULT, sCoreCategory },
		{ ISassPreferenceMessages.COMMENT_BLOCK, ISassColorConstants.COMMENT_BLOCK, sCommentsCategory },
		{ ISassPreferenceMessages.COMMENT_SINGLE, ISassColorConstants.COMMENT_SINGLE, sCommentsCategory },
		{ ISassPreferenceMessages.STRUCTURE, ISassColorConstants.STRUCTURE, sCoreCategory },
		{ ISassPreferenceMessages.OPERATOR, ISassColorConstants.OPERATOR, sCoreCategory },
		{ ISassPreferenceMessages.STRING, ISassColorConstants.STRING, sCoreCategory },
		{ ISassPreferenceMessages.NUMBER, ISassColorConstants.NUMBER, sCoreCategory },
		{ ISassPreferenceMessages.COLOR, ISassColorConstants.COLOR, sCoreCategory },
		{ ISassPreferenceMessages.BOOLEAN, ISassColorConstants.BOOLEAN, sCoreCategory },
		{ ISassPreferenceMessages.DIRECTIVE, ISassColorConstants.DIRECTIVE, sSASSCategory },
		{ ISassPreferenceMessages.VARIABLE, ISassColorConstants.VARIABLE, sSASSCategory },
		{ ISassPreferenceMessages.FUNCTION, ISassColorConstants.FUNCTION, sSASSCategory },
		{ ISassPreferenceMessages.MIXIN, ISassColorConstants.MIXIN, sSASSCategory },
		{ ISassPreferenceMessages.NULL, ISassColorConstants.NULL, sSASSCategory },
		{ ISassPreferenceMessages.KEYWORD, ISassColorConstants.KEYWORD, sCSSCategory },
		{ ISassPreferenceMessages.SELECTOR, ISassColorConstants.SELECTOR, sCSSCategory },
		{ ISassPreferenceMessages.PROPERTY, ISassColorConstants.PROPERTY, sCSSCategory },
		{ ISassPreferenceMessages.INTERPOLATION, ISassColorConstants.INTERPOLATION, sSASSCategory }
	};
	
	public SassEditorSyntaxColoringBlock(OverlayPreferenceStore store) {
		super(store);
	}
	
	@Override
	protected String[][] getSyntaxColorListModel() {
		return listModel;
	}

	@Override
	protected ProjectionViewer createPreviewViewer(
		Composite parent,
		IVerticalRuler verticalRuler,
		IOverviewRuler overviewRuler,
		boolean showAnnotationsOverview,
		int styles,
		IPreferenceStore store
	) {
		return new ScriptSourceViewer(
			parent,
			verticalRuler,
			overviewRuler,
			showAnnotationsOverview,
			styles,
			store
		);
	}
	
	@Override
	protected ScriptTextTools getTextTools() {
		return SassEditorPlugin.getDefault().getTextTools();
	}

	@Override
	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
		IColorManager colorManager,
		IPreferenceStore preferenceStore,
		ITextEditor editor,
		boolean configureFormatter
	) {
		return new SassSourceViewerConfiguration(
			colorManager,
			preferenceStore,
			editor,
			IDocument.DEFAULT_CONTENT_TYPE
		);
	}

	@Override
	protected void setDocumentPartitioning(IDocument document) {

	}
	
	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
}
