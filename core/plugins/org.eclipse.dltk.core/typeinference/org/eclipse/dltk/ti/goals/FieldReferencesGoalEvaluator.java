/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchPattern;

public class FieldReferencesGoalEvaluator extends SearchBasedGoalEvaluator {

	public FieldReferencesGoalEvaluator(IGoal goal) {
		super(goal);
	}

	@Override
	protected SearchPattern createSearchPattern(IDLTKLanguageToolkit toolkit) {
		FieldReferencesGoal goal = (FieldReferencesGoal) getGoal();
		String name = goal.getName();
		return SearchPattern.createPattern(name, IDLTKSearchConstants.FIELD,
				IDLTKSearchConstants.REFERENCES, SearchPattern.R_EXACT_MATCH,
				toolkit);
	}

	@Override
	protected IGoal createVerificationGoal(PossiblePosition pos) {
		return new FieldPositionVerificationGoal(this.getGoal().getContext(),
				(FieldReferencesGoal) this.getGoal(), pos);
	}

}
