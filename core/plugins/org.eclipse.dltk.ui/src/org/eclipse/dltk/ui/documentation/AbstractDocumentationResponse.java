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
package org.eclipse.dltk.ui.documentation;

import java.io.IOException;
import java.net.URL;

/**
 * @since 2.0
 */
public abstract class AbstractDocumentationResponse implements
		IDocumentationResponse {

	private final Object object;

	public AbstractDocumentationResponse(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public URL getURL() throws IOException {
		return null;
	}

}
