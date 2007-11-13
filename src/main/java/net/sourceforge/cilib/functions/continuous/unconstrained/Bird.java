/*
 * Bird.java
 *
 * Created on November 8, 2007, 06:15 PM
 *
 * 
 * Copyright (C) 2003 - 2007 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA 
 *   
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * <p><b>Bird Function</b></p>
 * 
 * <p><b>Reference:</b> S.K. Mishra, <i>Some New Test Functions 
 * for Global Optimization and Performance of Repulsive Particle Swarm Methods</i>
 * North-Eastern Hill University, India, 2002</p>
 * 
 * <p>Minimum:
 * <ul>
 * <li> f(<b>x</b>*) = -106.764537 (approx) </li>
 * <li> <b>x</b>* = (??,??)</li>
 * <li> for x_1, x_2 in [-2*pi, 2*pi]</li>
 * </ul>
 * </p>
 * 
 * <p>Characteristics:
 * <ul>
 * <li>Only defined for 2 dimensions</li>
 * <li>Unimodal</li>
 * <li>Seperable</li>
 * <li>Regular</li>
 * </ul>
 * </p>
 * 
 * @author Andries Engelbrecht
 *
 */
public class Bird extends ContinuousFunction {
	private static final long serialVersionUID = -7803711986955989075L;

	public Bird() {
        setDomain("R(-6.285714286,6.285714286)^2");
    }
	
	public Object getMinimum() {
        return new Double(-106.764537);
    }
	
	/* (non-Javadoc)
	 * @see net.sourceforge.cilib.functions.redux.ContinuousFunction#evaluate(net.sourceforge.cilib.type.types.container.Vector)
	 */
	@Override
	public double evaluate(Vector x) {
		double x1 = x.getReal(0);
		double x2 = x.getReal(1);
		
		return Math.sin(x1)*Math.exp((1-Math.cos(x2))*(1-Math.cos(x2)))+Math.cos(x2)*Math.exp((1-Math.sin(x1))*(1-Math.sin(x1)))+(x1-x2)*(x1-x2);
	}
}


