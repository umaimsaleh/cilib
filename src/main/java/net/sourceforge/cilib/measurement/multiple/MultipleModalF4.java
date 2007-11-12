/*
 * MultipleModalF4.java
 *
 * Created on 13 October, 2006, 11:57 AM
 *
 * Copyright (C) 2006
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
package net.sourceforge.cilib.measurement.multiple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.OptimisationSolution;
import net.sourceforge.cilib.type.types.StringType;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * 
 * @author Edrich van Loggerenberg
 */
public class MultipleModalF4 implements Measurement {
	private static final long serialVersionUID = -3032551342277813084L;

	public MultipleModalF4() {
	}

	public MultipleModalF4(MultipleModalF4 copy) {
	}

	public MultipleModalF4 clone() {
		return new MultipleModalF4(this);
	}

	public String getDomain() {
		return "T";
	}

	public Type getValue() {
		Vector v = new Vector();
		Collection<OptimisationSolution> p = (Algorithm.get()).getSolutions();

		Hashtable<Double, Vector> solutionsFound = new Hashtable<Double, Vector>();
		ArrayList<Double> opt = new ArrayList<Double>();
		opt.add(0.08);
		opt.add(0.25);
		opt.add(0.45);
		opt.add(0.68);
		opt.add(0.93);

		for (Iterator<OptimisationSolution> i = p.iterator(); i.hasNext();) {
			Vector solution = (Vector) i.next().getPosition();
			for (int count = 0; count < opt.size(); count++) {
				double sol = (Double) opt.get(count);
				if (TestNear(solution.getReal(0), sol)) {
					if (!solutionsFound.containsKey(sol))
						solutionsFound.put(sol, solution);
					break;
				}
			}
		}

		Enumeration<Double> sols = solutionsFound.keys();
		while (sols.hasMoreElements()) {
			// double k = (Double)sols.nextElement();
			Vector s = solutionsFound.get(sols.nextElement());

			v.append(s);

			StringType t = new StringType();
			t.setString(ComputeDerivative(s.getReal(0)) + "");
			v.append(t);
		}

		return v;
	}

	private boolean TestNear(double solution, double val) {
		if (val >= (solution - 0.05) && val <= (solution + 0.05))
			return true;

		return false;
	}

	private double ComputeDerivative(double x) {
		double k = Math.log(0.25) / Math.pow(0.854, 2.0);
		double g2 = k * Math.pow((x - 0.08), 2.0);
		double dg2 = 2.0 * k * (x - 0.08);
		double df3 = 6.0 * Math.pow(Math.sin(5.0 * Math.PI * (Math.pow(x, 0.75) - 0.05)), 5.0) * Math.cos(5.0 * Math.PI * (Math.pow(x, 0.75) - 0.05)) * ((15.0 * Math.PI * Math.pow(x, -0.25)) / 4.0);
		double df4 = dg2 * Math.exp(g2) * Math.pow(Math.sin(5.0 * Math.PI * (Math.pow(x, 0.75) - 0.05)), 6.0) + Math.exp(g2) * df3;
		return df4;
	}
}