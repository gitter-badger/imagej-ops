
package net.imagej.ops.geometric;

import java.util.List;

import net.imglib2.Localizable;

/**
 * Marker class for convex {@link Polygon}s
 * 
 * @author Christian Dietz, University of Konstanz
 */
public class ConvexPolygon extends Polygon {

	public ConvexPolygon() {
		super();
	}

	public ConvexPolygon(final List<Localizable> points) {
		super(points);
	}
}
