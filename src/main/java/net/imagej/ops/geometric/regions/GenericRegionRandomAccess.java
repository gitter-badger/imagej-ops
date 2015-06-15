
package net.imagej.ops.geometric.regions;

import net.imglib2.Localizable;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.Sampler;
import net.imglib2.type.logic.BoolType;

public class GenericRegionRandomAccess<L extends Localizable> implements
	RandomAccess<BoolType>
{

	private GenericRegion<L> region;

	private Point current;

	private BoolType type;

	public GenericRegionRandomAccess(final GenericRegion<L> region) {
		this.region = region;
		this.current = new Point(region.numDimensions());
	}

	private GenericRegionRandomAccess() {
		// copy constructor
	}

	@Override
	public void localize(int[] position) {
		current.localize(position);
	}

	@Override
	public void localize(long[] position) {
		current.localize(position);
	}

	@Override
	public int getIntPosition(int d) {
		return current.getIntPosition(d);
	}

	@Override
	public long getLongPosition(int d) {
		return current.getLongPosition(d);
	}

	@Override
	public void localize(float[] position) {
		current.localize(position);
	}

	@Override
	public void localize(double[] position) {
		current.localize(position);
	}

	@Override
	public float getFloatPosition(int d) {
		return current.getFloatPosition(d);
	}

	@Override
	public double getDoublePosition(int d) {
		return current.getDoublePosition(d);
	}

	@Override
	public int numDimensions() {
		return current.numDimensions();
	}

	@Override
	public void fwd(int d) {
		current.fwd(d);
	}

	@Override
	public void bck(int d) {
		current.bck(d);
	}

	@Override
	public void move(int distance, int d) {
		current.move(distance, d);
	}

	@Override
	public void move(long distance, int d) {
		current.move(distance, d);
	}

	@Override
	public void move(Localizable localizable) {
		current.move(localizable);
	}

	@Override
	public void move(int[] distance) {
		current.move(distance);
	}

	@Override
	public void move(long[] distance) {
		current.move(distance);
	}

	@Override
	public void setPosition(Localizable localizable) {
		current.setPosition(localizable);
	}

	@Override
	public void setPosition(int[] position) {
		current.setPosition(position);
	}

	@Override
	public void setPosition(long[] position) {
		current.setPosition(position);
	}

	@Override
	public void setPosition(int position, int d) {
		current.setPosition(position, d);
	}

	@Override
	public void setPosition(long position, int d) {
		current.setPosition(position, d);
	}

	@Override
	public BoolType get() {
		if (region.contains(current)) type.set(true);
		else type.set(false);

		return type;
	}

	@Override
	public Sampler<BoolType> copy() {
		return copy();
	}

	@Override
	public RandomAccess<BoolType> copyRandomAccess() {
		final GenericRegionRandomAccess<L> copy =
			new GenericRegionRandomAccess<L>();
		copy.current = new Point(current);
		copy.region = region;
		return copy;
	}

}
