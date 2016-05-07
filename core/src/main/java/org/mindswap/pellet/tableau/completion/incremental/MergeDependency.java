// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.tableau.completion.incremental;

import aterm.ATermAppl;

/**
 * A dependency for a _node merge
 *
 * @author Christian Halaschek-Wiener
 */
public class MergeDependency implements Dependency
{

	/**
	 * The individual that ind is merged to
	 */
	private final ATermAppl mergedIntoInd;

	/**
	 * The individual that is merged into mergedIntoInd
	 */
	private final ATermAppl ind;

	/**
	 * Constructor
	 * 
	 * @param ind
	 * @param mergedIntoInd
	 */
	public MergeDependency(final ATermAppl ind, final ATermAppl mergedIntoInd)
	{
		this.mergedIntoInd = mergedIntoInd;
		this.ind = ind;
	}

	/**
	 * Get the individual that is merged into the other
	 *
	 * @return
	 */
	public ATermAppl getInd()
	{
		return ind;
	}

	/**
	 * Get the individual that has ind merged into it
	 *
	 * @return
	 */
	public ATermAppl getmergedIntoInd()
	{
		return mergedIntoInd;
	}

	/**
	 * ToString method
	 */
	@Override
	public String toString()
	{
		return "Merge [" + ind + "]  into [" + mergedIntoInd + "]";
	}

	/**
	 * Equals method
	 */
	@Override
	public boolean equals(final Object other)
	{
		if (other instanceof MergeDependency)
			return this.ind.equals(((MergeDependency) other).ind) && this.mergedIntoInd.equals(((MergeDependency) other).mergedIntoInd);
		else
			return false;
	}

	/**
	 * Hashcode method TODO: this may not be sufficient
	 */
	@Override
	public int hashCode()
	{
		return this.ind.hashCode() + this.mergedIntoInd.hashCode();
	}

}
