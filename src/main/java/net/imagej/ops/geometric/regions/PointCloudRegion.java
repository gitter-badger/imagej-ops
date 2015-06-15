
package net.imagej.ops.geometric.regions;

import java.util.Collection;
import java.util.Iterator;

import net.imglib2.Localizable;
import net.imglib2.Point;

public class PointCloudRegion extends AbstractGenericRegion<Point> {

	private Collection<Point> points;

	public PointCloudRegion(final int n, final Collection<Point> points) {
		super(n);
		this.points = points;
	}

	@Override
	public boolean contains(Localizable loc) {
		return points.contains(loc);
	}

	@Override
	public long size() {
		return points.size();
	}

	@Override
	public Iterator<Point> regionIterator() {
		return points.iterator();
	}
}
