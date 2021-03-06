/*******************************************************************************
 * Copyright (c) 2004, 2008 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.dltk.internal.mylyn.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.internal.ui.views.launch.LaunchView;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.mylyn.context.ui.AbstractFocusViewAction;
import org.eclipse.mylyn.context.ui.InterestFilter;
import org.eclipse.ui.IViewPart;

/**
 * @author Mik Kersten
 */
public class FocusDebugViewAction extends AbstractFocusViewAction {

	public FocusDebugViewAction() {
		super(new InterestFilter(), true, true, false);
	}

	@Override
	public List<StructuredViewer> getViewers() {
		List<StructuredViewer> viewers = new ArrayList<StructuredViewer>();
		IViewPart view = super.getPartForAction();
		if (view instanceof LaunchView) {
			LaunchView launchView = (LaunchView) view;
			viewers.add((StructuredViewer) launchView.getViewer());
		}
		return viewers;
	}

}
