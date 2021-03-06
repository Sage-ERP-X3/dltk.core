/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.ui.IWorkbenchSite;


/**
 * Wraps a <code>ModelElementSearchActions</code> to find its results
 * in the specified working set.
 * <p>
 * The action is applicable to selections and Search view entries
 * representing a Script element.
 * 
 * <p>
 * Note: This class is for internal use only. Clients should not use this class.
 * </p>
 * 
	 *
 */
public class WorkingSetFindAction extends FindAction {

	private FindAction fAction;	
	IDLTKLanguageToolkit toolkit;

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 */
	public WorkingSetFindAction(IWorkbenchSite site, FindAction action, String workingSetName) {
		super(site);
		init(action, workingSetName);
		toolkit = action.getLanguageToolkit();
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 */
	public WorkingSetFindAction(ScriptEditor editor, FindAction action, String workingSetName) {
		super(editor);
		init(action, workingSetName);
	}

	Class[] getValidTypes() {
		return null; // ignore, we override canOperateOn
	}
	
	void init() {
		// ignore: do our own init in 'init(FindAction, String)'
	}
	
	private void init(FindAction action, String workingSetName) {
		Assert.isNotNull(action);
		fAction= action;
		setText(workingSetName);
		setImageDescriptor(action.getImageDescriptor());
		setToolTipText(action.getToolTipText());
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.WORKING_SET_FIND_ACTION);		
		if (DLTKCore.DEBUG) {
			System.out.println("TODO: Add help support here..."); //$NON-NLS-1$
		}
	}
	
	public void run(IModelElement element) {
		fAction.run(element);
	}

	boolean canOperateOn(IModelElement element) {
		return fAction.canOperateOn(element);
	}

	int getLimitTo() {
		return -1;
	}

	String getOperationUnavailableMessage() {
		return fAction.getOperationUnavailableMessage();
	}

	protected IDLTKLanguageToolkit getLanguageToolkit() {
		return toolkit;
	}	
}
