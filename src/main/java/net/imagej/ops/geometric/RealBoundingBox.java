
package net.imagej.ops.geometric;

import net.imglib2.AbstractRealInterval;
import net.imglib2.RealLocalizable;

public class RealBoundingBox extends AbstractRealInterval {

	public RealBoundingBox(int n) {
		super(n);
	}

	/**
	 * update the minimum and maximum extents with the given coordinates.
	 *
	 * @param position
	 */
	public void update(final RealLocalizable position) {
		for (int d = 0; d < min.length; d++) {
			final double p = position.getDoublePosition(d);
			if (p < min[d]) min[d] = p;
			if (p > max[d]) max[d] = p;
		}
	}

	/**
	 * update the minimum and maximum extents with the given coordinates.
	 *
	 * @param position
	 */
	public void update(final double[] position) {
		for (int d = 0; d < min.length; d++) {
			if (position[d] < min[d]) min[d] = position[d];
			if (position[d] > max[d]) max[d] = position[d];
		}
	}

}
