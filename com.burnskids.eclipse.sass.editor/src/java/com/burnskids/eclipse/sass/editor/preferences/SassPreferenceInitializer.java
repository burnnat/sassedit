package com.burnskids.eclipse.sass.editor.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.EditorsUI;

import com.burnskids.eclipse.sass.editor.SassPlugin;
import com.burnskids.eclipse.sass.editor.ui.ISassColorConstants;

public class SassPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = SassPlugin.getDefault().getPreferenceStore();
 
		EditorsUI.useAnnotationsPreferencePage(store);
		EditorsUI.useQuickDiffPreferencePage(store);
 
		// Initialize DLTK default values
		PreferenceConstants.initializeDefaultValues(store);
 
		store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 4);
		store.setDefault(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);
 
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.TAB);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "4");
		store.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE,"4");
 
		// Initialize python constants
		PreferenceConverter.setDefault(store, ISassColorConstants.DEFAULT, new RGB(0, 0, 0));
		store.setDefault(ISassColorConstants.DEFAULT + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.DEFAULT + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.COMMENT_BLOCK, new RGB(50, 50, 220));
		store.setDefault(ISassColorConstants.COMMENT_BLOCK + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.COMMENT_BLOCK + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.COMMENT_SINGLE, new RGB(0, 150, 0));
		store.setDefault(ISassColorConstants.COMMENT_SINGLE + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.COMMENT_SINGLE + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.STRUCTURE, new RGB(175, 175, 175));
		store.setDefault(ISassColorConstants.STRUCTURE + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.STRUCTURE + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.OPERATOR, new RGB(175, 175, 0));
		store.setDefault(ISassColorConstants.OPERATOR + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.OPERATOR + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.STRING, new RGB(0, 0, 255));
		store.setDefault(ISassColorConstants.STRING + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.STRING + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.NUMBER, new RGB(200, 0, 200));
		store.setDefault(ISassColorConstants.NUMBER + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.NUMBER + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.COLOR, new RGB(128, 0, 128));
		store.setDefault(ISassColorConstants.COLOR + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.COLOR + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.BOOLEAN, new RGB(255, 0, 255));
		store.setDefault(ISassColorConstants.BOOLEAN + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.BOOLEAN + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.DIRECTIVE, new RGB(255, 155, 0));
		store.setDefault(ISassColorConstants.DIRECTIVE + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.DIRECTIVE + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.VARIABLE, new RGB(200, 0, 0));
		store.setDefault(ISassColorConstants.VARIABLE + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.VARIABLE + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.FUNCTION, new RGB(200, 100, 100));
		store.setDefault(ISassColorConstants.FUNCTION + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.FUNCTION + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.MIXIN, new RGB(0, 100, 200));
		store.setDefault(ISassColorConstants.MIXIN + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.MIXIN + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.NULL, new RGB(218, 112, 214));
		store.setDefault(ISassColorConstants.NULL + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.NULL + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.KEYWORD, new RGB(255, 215, 0));
		store.setDefault(ISassColorConstants.KEYWORD + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.KEYWORD + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.SELECTOR, new RGB(32, 178, 170));
		store.setDefault(ISassColorConstants.SELECTOR + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.SELECTOR + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.PROPERTY, new RGB(123, 104, 238));
		store.setDefault(ISassColorConstants.PROPERTY + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.PROPERTY + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		PreferenceConverter.setDefault(store, ISassColorConstants.INTERPOLATION, new RGB(255, 0, 0));
		store.setDefault(ISassColorConstants.INTERPOLATION + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(ISassColorConstants.INTERPOLATION + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
	}
}
