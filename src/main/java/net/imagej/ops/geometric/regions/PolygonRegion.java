
package net.imagej.ops.geometric.regions;

import java.util.Iterator;

import net.imagej.ops.geometric.Polygon;
import net.imglib2.Localizable;

public class PolygonRegion extends AbstractGenericRegion<Localizable> {

	private Polygon poly;

	public PolygonRegion(final Polygon poly) {
		super(2);
		this.poly = poly;
	}

	@Override
	public boolean contains(final Localizable loc) {
		return poly.contains(loc);
	}

	@Override
	public long size() {
		return poly.region().size();
	}

	@Override
	public Iterator<Localizable> regionIterator() {
		return poly.region().iterator();
	}
}
