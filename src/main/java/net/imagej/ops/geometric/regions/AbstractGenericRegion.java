
package net.imagej.ops.geometric.regions;

import java.util.Iterator;

import net.imglib2.AbstractInterval;
import net.imglib2.Cursor;
import net.imglib2.Interval;
import net.imglib2.Localizable;
import net.imglib2.RandomAccess;
import net.imglib2.type.logic.BoolType;

abstract class AbstractGenericRegion<L extends Localizable> extends
	AbstractInterval implements GenericRegion<L>
{

	AbstractGenericRegion(final int numDims) {
		super(numDims);
	}

	@Override
	public Cursor<BoolType> cursor() {
		return new UnsafeGenericRegionCursor<L>(this);
	}

	@Override
	public Cursor<BoolType> localizingCursor() {
		return cursor();
	}

	@Override
	public BoolType firstElement() {
		return cursor().next();
	}

	@Override
	public Object iterationOrder() {
		return this;
	}

	@Override
	public Iterator<BoolType> iterator() {
		return cursor();
	}

	@Override
	public RandomAccess<BoolType> randomAccess() {
		return new GenericRegionRandomAccess<L>(this);
	}

	@Override
	public RandomAccess<BoolType> randomAccess(Interval interval) {
		// FIXME: is this ok?
		return randomAccess();
	}

}
