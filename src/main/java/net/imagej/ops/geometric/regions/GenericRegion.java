
package net.imagej.ops.geometric.regions;

import java.util.Iterator;

import net.imglib2.Interval;
import net.imglib2.Localizable;
import net.imglib2.roi.IterableRegion;
import net.imglib2.type.logic.BoolType;

public interface GenericRegion<L extends Localizable> extends Interval,
	IterableRegion<BoolType>
{

	boolean contains(final Localizable localizable);

	Iterator<L> regionIterator();
}
