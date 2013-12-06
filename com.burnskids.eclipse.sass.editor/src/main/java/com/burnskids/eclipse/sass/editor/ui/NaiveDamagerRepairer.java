package com.burnskids.eclipse.sass.editor.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

/**
 * Based on the {@link DefaultDamagerRepairer}, but specialized for naive token scanners,
 * refreshing the entire partition each time (inefficient, I know... perhaps we can find
 * a better way in the future) and allowing the scanner to return non-contiguous tokens.
 */
public class NaiveDamagerRepairer implements IPresentationDamager, IPresentationRepairer {


	/** The document this object works on */
	protected IDocument document;
	/** The scanner it uses */
	protected ITokenScanner scanner;
	/** The default text attribute if non is returned as data by the current token */
	protected TextAttribute defaultTextAttribute;

	/**
	 * Creates a damager/repairer that uses the given scanner. The scanner may not be <code>null</code>
	 * and is assumed to return only token that carry text attributes.
	 *
	 * @param scanner the token scanner to be used, may not be <code>null</code>
	 */
	public NaiveDamagerRepairer(ITokenScanner scanner) {
		Assert.isNotNull(scanner);

		this.scanner = scanner;
		defaultTextAttribute = new TextAttribute(null);
	}

	/*
	 * @see IPresentationDamager#setDocument(IDocument)
	 * @see IPresentationRepairer#setDocument(IDocument)
	 */
	public void setDocument(IDocument document) {
		this.document = document;
	}


	//---- IPresentationDamager

	/**
	 * Returns the end offset of the line that contains the specified offset or
	 * if the offset is inside a line delimiter, the end offset of the next line.
	 *
	 * @param offset the offset whose line end offset must be computed
	 * @return the line end offset for the given offset
	 * @exception BadLocationException if offset is invalid in the current document
	 */
	protected int endOfLineOf(int offset) throws BadLocationException {
		IRegion info= document.getLineInformationOfOffset(offset);
		
		if (offset <= info.getOffset() + info.getLength())
			return info.getOffset() + info.getLength();

		int line = document.getLineOfOffset(offset);
		
		try {
			info = document.getLineInformation(line + 1);
			return info.getOffset() + info.getLength();
		}
		catch (BadLocationException x) {
			return document.getLength();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation damages entire lines unless clipped by the given partition.
	 * </p>
	 * 
	 * @return the full lines containing the document changes described by the document event,
	 *         clipped by the given partition. If there was a partitioning change then the whole
	 *         partition is returned.
	 */
	public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent e, boolean documentPartitioningChanged) {
//		if (!documentPartitioningChanged) {
//			try {
//				IRegion info = document.getLineInformationOfOffset(e.getOffset());
//				int start = Math.max(partition.getOffset(), info.getOffset());
//
//				int end = e.getOffset() + (e.getText() == null ? e.getLength() : e.getText().length());
//
//				if (info.getOffset() <= end && end <= info.getOffset() + info.getLength()) {
//					// optimize the case of the same line
//					end = info.getOffset() + info.getLength();
//				}
//				else {
//					end = endOfLineOf(end);
//				}
//
//				end = Math.min(partition.getOffset() + partition.getLength(), end);
//				return new Region(start, end - start);
//
//			}
//			catch (BadLocationException x) {}
//		}

		return partition;
	}

	//---- IPresentationRepairer

	/*
	 * @see IPresentationRepairer#createPresentation(TextPresentation, ITypedRegion)
	 */
	public void createPresentation(TextPresentation presentation, ITypedRegion region) {
		scanner.setRange(document, region.getOffset(), region.getLength());

		while (true) {
			IToken token = scanner.nextToken();
			
			if (token.isEOF()) {
				break;
			}
			
			System.out.println(
				"(" + scanner.getTokenOffset() +
				", " + scanner.getTokenLength() +
				")"
			);
			
			addRange(
				presentation,
				scanner.getTokenOffset(),
				scanner.getTokenLength(),
				getTokenTextAttribute(token)
			);
		}
	}

	/**
	 * Returns a text attribute encoded in the given token. If the token's
	 * data is not <code>null</code> and a text attribute it is assumed that
	 * it is the encoded text attribute. It returns the default text attribute
	 * if there is no encoded text attribute found.
	 *
	 * @param token the token whose text attribute is to be determined
	 * @return the token's text attribute
	 */
	protected TextAttribute getTokenTextAttribute(IToken token) {
		Object data = token.getData();
		
		if (data instanceof TextAttribute) {
			return (TextAttribute) data;
		}
		
		return defaultTextAttribute;
	}

	/**
	 * Adds style information to the given text presentation.
	 *
	 * @param presentation the text presentation to be extended
	 * @param offset the offset of the range to be styled
	 * @param length the length of the range to be styled
	 * @param attr the attribute describing the style of the range to be styled
	 */
	protected void addRange(TextPresentation presentation, int offset, int length, TextAttribute attr) {
		if (attr != null) {
			int style = attr.getStyle();
			int fontStyle = style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
			
			StyleRange styleRange = new StyleRange(offset, length, attr.getForeground(), attr.getBackground(), fontStyle);
			styleRange.strikeout = (style & TextAttribute.STRIKETHROUGH) != 0;
			styleRange.underline = (style & TextAttribute.UNDERLINE) != 0;
			styleRange.font = attr.getFont();
			
			presentation.addStyleRange(styleRange);
		}
	}
}