/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 Board of Regents of the University of
 * Wisconsin-Madison and University of Konstanz.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.ops.geometric.ops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.imagej.ops.Op;
import net.imagej.ops.geometric.ConvexPolygon;
import net.imagej.ops.geometric.GeometricOps.ConvexHullOp;
import net.imglib2.Localizable;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Uses Andrew's monotone chain algorithm to calculate the Convex Hull of a
 * Polygon.
 *
 * @author Daniel Seebacher, University of Konstanz.
 */
@Plugin(type = Op.class, name = ConvexHullOp.NAME)
public class DefaultConvexHull2DOp implements ConvexHullOp<ConvexPolygon> {

	@Parameter(type = ItemIO.OUTPUT)
	private ConvexPolygon output;

	@Parameter(type = ItemIO.INPUT)
	private SortedLocationsOp input;

	/**
	 * Andrew's monotone chain convex hull algorithm constructs the convex hull of
	 * a set of 2-dimensional Localizable in O(n log n) time. It does so by first
	 * sorting the Localizable lexicographically (first by x-coordinate, and in
	 * case of a tie, by y-coordinate), and then constructing upper and lower
	 * hulls of the Localizable in O(n) time. An upper hull is the part of the
	 * convex hull, which is visible from the above. It runs from its rightmost
	 * Localizable to the leftmost Localizable in counterclockwise order. Lower
	 * hull is the remaining part of the convex hull.
	 */
	@Override
	public void run() {

		final List<Localizable> sortedInput = input.getOutput();

		// Initialize U and L as empty lists.
		// The lists will hold the vertices of upper and lower hulls
		// respectively.
		final List<Localizable> u = new ArrayList<Localizable>();
		final List<Localizable> l = new ArrayList<Localizable>();
		// build lower hull
		for (final Localizable p : sortedInput) {
			// while L contains at least two Localizable and the sequence of last
			// two
			// Localizable of L and the Localizable P[i] does not make a
			// counter-clockwise
			// turn: remove the last Localizable from L
			while (l.size() >= 2 &&
				ccw(l.get(l.size() - 2), l.get(l.size() - 1), p) <= 0)
			{
				l.remove(l.size() - 1);
			}
			l.add(p);
		}
		// build upper hull
		Collections.reverse(sortedInput);
		for (final Localizable p : sortedInput) {
			// while U contains at least two Localizable and the sequence of last
			// two
			// Localizable of U and the Localizable P[i] does not make a
			// counter-clockwise
			// turn: remove the last Localizable from U
			while (u.size() >= 2 &&
				ccw(u.get(u.size() - 2), u.get(u.size() - 1), p) <= 0)
			{
				u.remove(u.size() - 1);
			}
			u.add(p);
		}
		// Remove the last Localizable of each list (it's the same as the first
		// Localizable
		// of the other list).
		l.remove(l.size() - 1);
		u.remove(u.size() - 1);
		// concatenate L and U
		l.addAll(u);

		output = new ConvexPolygon(l);
	}

	/**
	 * 2D cross product of OA and OB vectors, i.e. z-component of their 3D cross
	 * product. Returns a positive value, if OAB makes a counter-clockwise turn,
	 * negative for clockwise turn, and zero if the Localizable are co-linear.
	 *
	 * @param o first Localizable
	 * @param a second Localizable
	 * @param b third Localizable
	 * @return Returns a positive value, if OAB makes a counter-clockwise turn,
	 *         negative for clockwise turn, and zero if the Localizable are
	 *         co-linear.
	 */
	private double ccw(final Localizable o, final Localizable a,
		final Localizable b)
	{
		return (a.getDoublePosition(0) - o.getDoublePosition(0)) *
			(b.getDoublePosition(1) - o.getDoublePosition(1)) -
			(a.getDoublePosition(1) - o.getDoublePosition(1)) *
			(b.getDoublePosition(0) - o.getDoublePosition(0));
	}

	@Override
	public ConvexPolygon getOutput() {
		return output;
	}

	@Override
	public void setOutput(final ConvexPolygon output) {
		this.output = output;
	}
}
