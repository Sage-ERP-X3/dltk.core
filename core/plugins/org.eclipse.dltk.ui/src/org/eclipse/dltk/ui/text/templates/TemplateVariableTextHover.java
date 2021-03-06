/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.ui.text.templates;

import java.util.Iterator;

import org.eclipse.dltk.internal.ui.text.ScriptWordFinder;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class TemplateVariableTextHover implements ITextHover {

	private TemplateVariableProcessor fProcessor;

	/**
	 * @param processor
	 *            the template variable processor
	 */
	public TemplateVariableTextHover(TemplateVariableProcessor processor) {
		fProcessor = processor;
	}

	/*
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
	public String getHoverInfo(ITextViewer textViewer, IRegion subject) {
		try {
			IDocument doc = textViewer.getDocument();
			int offset = subject.getOffset();
			if (offset >= 2 && "${".equals(doc.get(offset - 2, 2))) { //$NON-NLS-1$
				String varName = doc.get(offset, subject.getLength());
				TemplateContextType contextType = fProcessor.getContextType();
				if (contextType != null) {
					Iterator iter = contextType.resolvers();
					while (iter.hasNext()) {
						TemplateVariableResolver var = (TemplateVariableResolver) iter
								.next();
						if (varName.equals(var.getType())) {
							return var.getDescription();
						}
					}
				}
			}
		} catch (BadLocationException e) {
		}
		return null;
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		if (textViewer != null) {
			return ScriptWordFinder.findWord(textViewer.getDocument(), offset);
		}
		return null;
	}

}