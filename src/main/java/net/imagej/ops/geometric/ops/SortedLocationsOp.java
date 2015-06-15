
package net.imagej.ops.geometric.ops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.imagej.ops.Computer;
import net.imagej.ops.InputOp;
import net.imagej.ops.OutputOp;
import net.imagej.ops.geometric.Polygon;
import net.imglib2.Localizable;

import org.scijava.plugin.Parameter;

public class SortedLocationsOp implements OutputOp<List<Localizable>>,
	InputOp<Polygon>, Computer<Polygon, List<Localizable>>
{

	@Parameter
	private Polygon input;

	@Parameter
	private List<Localizable> sortedInput;

	@Override
	public List<Localizable> compute(final Polygon input) {
		final List<Localizable> sorted = new ArrayList<Localizable>(input.polygon());
		Collections.sort(sorted, new LocalizableComparator());
		return sorted;
	}

	@Override
	public Polygon getInput() {
		return input;
	}

	@Override
	public void setInput(final Polygon input) {
		this.input = input;
	}

	@Override
	public List<Localizable> getOutput() {
		return sortedInput;
	}

	@Override
	public void setOutput(List<Localizable> sortedInput) {
		this.sortedInput = sortedInput;
	}

	@Override
	public void run() {
		sortedInput = compute(input);
	}

	class LocalizableComparator implements Comparator<Localizable> {

		@Override
		public int compare(final Localizable o1, final Localizable o2) {

			int res = 0;
			int d = o1.numDimensions();
			while (res == 0 && d < o1.numDimensions()) {
				res = Double.compare(o1.getDoublePosition(d), o2.getDoublePosition(d));
				d++;
			}

			return res;
		}
	}
}
