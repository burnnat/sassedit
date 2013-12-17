package com.burnskids.eclipse.sass.editor.ui;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

import com.burnskids.eclipse.sass.editor.parser.SassScanner;

public class SassSourceViewerConfiguration extends ScriptSourceViewerConfiguration {
	private ITextDoubleClickStrategy doubleClickStrategy;
	private SassScanner scanner;

	public SassSourceViewerConfiguration(
			IColorManager colorManager,
			IPreferenceStore preferenceStore,
			ITextEditor editor,
			String partitioning
	) {
		super(colorManager, preferenceStore, editor, partitioning);
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE
		};
	}
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null) {
			doubleClickStrategy = new SassDoubleClickStrategy();
		}
		
		return doubleClickStrategy;
	}
	
	protected void initializeScanners() {
		scanner = new SassScanner(this.getColorManager(), this.fPreferenceStore);
	}
	
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (scanner.affectsBehavior(event)) {
			scanner.adaptToPreferenceChange(event);
		}
	}

	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return scanner.affectsBehavior(event);
	}

	protected ITokenScanner getScanner() {
		return scanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		AsyncPresentationReconciler reconciler = new AsyncPresentationReconciler();
		NaiveDamagerRepairer dr = new NaiveDamagerRepairer(getScanner());
		
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		return reconciler;
	}

	@Override
	protected ContentAssistPreference getContentAssistPreference() {
		return SassContentAssistPreference.getDefault();
	}
}