
package net.imagej.ops.geometric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import net.imagej.ops.Op;
import net.imglib2.AbstractRealInterval;
import net.imglib2.Localizable;
import net.imglib2.Point;
import net.imglib2.util.Intervals;

public class Polygon extends AbstractRealInterval implements
	Iterable<Localizable>
{

	protected final List<Localizable> points;
	private LinkedHashSet<Localizable> res;
	private ArrayList<Localizable> outline;

	public Polygon(final List<Localizable> points) {
		super(2);
		this.points = points;
	}

	public Polygon() {
		this(new ArrayList<Localizable>());
	}

	/**
	 * Return true if the given point is contained inside the boundary. See:
	 * http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
	 */
	public boolean contains(final Localizable localizable) {
		if (localizable.numDimensions() == 2 &&
			Intervals.contains(this, localizable))
		{
			int i;
			int j;
			boolean result = false;
			for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
				if ((points.get(i).getDoublePosition(1) > localizable
					.getDoublePosition(1)) != (points.get(j).getDoublePosition(1) > localizable
					.getDoublePosition(1)) &&
					(localizable.getDoublePosition(0) < (points.get(j).getDoublePosition(
						0) - points.get(i).getDoublePosition(0)) *
						(localizable.getDoublePosition(1) - points.get(i)
							.getDoublePosition(1)) /
						(points.get(j).getDoublePosition(1) - points.get(i)
							.getDoublePosition(1)) + points.get(i).getDoublePosition(0)))
				{
					result = !result;
				}
			}

			return result;
		}
		return false;
	}

	public void add(final Localizable point) {
		if (point.numDimensions() != 2) throw new IllegalArgumentException(
			"Localizableolygon supports only two dimensions");
		points.add(point);

		update(point);
	}

	/**
	 * Updates min/max of the interval
	 * 
	 * @param Localizable used to update min/max
	 */
	private void update(final Localizable localizable) {
		{
			for (int d = 0; d < min.length; d++) {
				final double p = localizable.getDoublePosition(d);
				if (p < min[d]) min[d] = p;
				if (p > max[d]) max[d] = p;
			}
		}

		res = null;
		outline = null;
	}

	@Override
	public Iterator<Localizable> iterator() {
		return points.iterator();
	}

	public List<Localizable> outline() {
		// TODO use cache service
		if (outline == null) {
			outline = new ArrayList<Localizable>();

			// collect all possible points
			for (int i = 1; i < points.size(); i++) {
				final Localizable p1 = points.get(i - 1);
				final Localizable p2 = points.get(i);
				final Point[] tmp = rasterizeLine(p1, p2);
				for (final Point p : tmp) {
					outline.add(p);
				}
			}

			final Localizable p1 = points.get(points.size() - 1);
			final Localizable p2 = points.get(0);
			final Point[] tmp = rasterizeLine(p1, p2);
			for (final Point p : tmp) {
				outline.add(p);
			}
		}
		return outline;
	}

	public List<Localizable> polygon() {
		return points;
	}

	public Collection<Localizable> region() {
		// TODO use cache service
		if (res == null) {
			// this is a very stupid implementation, but for testing its ok.
			res = new LinkedHashSet<Localizable>();
			final Point tmp = new Point(2);
			for (int x = (int) Math.floor(min[0]); x < Math.ceil(max[0] + 1); x++) {
				tmp.setPosition(x, 0);
				for (int y = (int) Math.floor(min[1]); y < Math.ceil(max[1] + 1); y++) {
					tmp.setPosition(y, 1);
					if (contains(tmp)) {
						res.add(new Point(tmp));
					};
				}
			}
		}
		return res;
	}

	/**
	 * TODO: Make this an {@link Op}!! Rasterizes a line from Point P1 to Point P2
	 * using the Bresenham-Algorithm.
	 * 
	 * @return the rasterized line between the two given points, beginning with
	 *         point p1 including p1 put without p2!!
	 */
	private Point[] rasterizeLine(final Localizable point1,
		final Localizable point2)
	{

		final int l =
			(int) Math.max(Math.abs(point1.getDoublePosition(0) -
				point2.getDoublePosition(0)), Math.abs(point1.getDoublePosition(0) -
				point2.getDoublePosition(1)));

		final Point[] res = new Point[l];
		int count = 0;

		double xtmp, ytmp, error, delta, step, dx, dy, incx, incy;

		xtmp = point1.getDoublePosition(0);
		ytmp = point1.getDoublePosition(1);

		dy = point2.getDoublePosition(1) - point1.getDoublePosition(1);
		dx = point2.getDoublePosition(0) - point1.getDoublePosition(0);

		if (dx > 0) {
			incx = 1;
		}
		else {
			incx = -1;
		}

		if (dy > 0) {
			incy = 1;
		}
		else {
			incy = -1;
		}

		if (Math.abs(dy) < Math.abs(dx)) {
			error = -Math.abs(dx);
			delta = 2 * Math.abs(dy);
			step = 2 * error;
			while (xtmp != point2.getDoublePosition(0)) {
				res[count] = new Point((int) xtmp, (int) ytmp);
				count++;
				xtmp += incx;
				error = error + delta;
				if (error > 0) {
					ytmp += incy;
					error += step;
				}
			}
		}
		else {
			error = -Math.abs(dy);
			delta = 2 * Math.abs(dx);
			step = 2 * error;
			while (ytmp != point2.getDoublePosition(1)) {
				res[count] = new Point((int) xtmp, (int) ytmp);
				count++;
				ytmp += incy;
				error = error + delta;
				if (error > 0) {
					xtmp += incx;
					error += step;
				}
			}
		}
		return res;
	}
}
