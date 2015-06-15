
package net.imagej.ops.geometric.regions;

import java.util.Iterator;

import net.imglib2.Cursor;
import net.imglib2.Localizable;
import net.imglib2.Sampler;
import net.imglib2.type.logic.BoolType;

class UnsafeGenericRegionCursor<L extends Localizable> implements
	Cursor<BoolType>
{

	private Iterator<L> it;

	private Localizable current;

	private BoolType type;

	private GenericRegion<L> access;

	public UnsafeGenericRegionCursor(final GenericRegion<L> access) {
		this.access = access;
		this.type = new BoolType(true);

		reset();
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
	public BoolType get() {
		return type;
	}

	@Override
	public void jumpFwd(long steps) {
		for (int k = 0; k < steps; k++) {
			fwd();
		}
	}

	@Override
	public void fwd() {
		current = it.next();
	}

	@Override
	public void reset() {
		this.it = access.regionIterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public BoolType next() {
		fwd();
		return get();
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
	public Cursor<BoolType> copyCursor() {
		return this;
	}

	@Override
	public Sampler<BoolType> copy() {
		return copy();
	}

}
