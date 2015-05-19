package net.imagej.ops.copy;

import net.imagej.ops.AbstractOutputFunction;
import net.imagej.ops.OpService;
import net.imagej.ops.Ops.Copy;
import net.imagej.ops.Ops.Map;
import net.imagej.ops.create.CreateEmptyImgCopy;
import net.imglib2.IterableInterval;
import net.imglib2.type.Type;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Copy.class, priority = 1.0)
public class CopyIIToII<T extends Type<T>> extends AbstractOutputFunction<IterableInterval<T>, IterableInterval<T>> {

	@Parameter
	protected OpService ops;
	
	@Override
	public IterableInterval<T> createOutput(IterableInterval<T> input) {
		// TODO replace with ops.run(Create.class, IterableInterval.class,
		// input) asap
		return (IterableInterval<T>) ops.run(CreateEmptyImgCopy.class, input);
	}

	@Override
	protected IterableInterval<T> safeCompute(IterableInterval<T> input,
			IterableInterval<T> output) {

		ops.run(Map.class, output, input, ops.op(Copy.class, output
				.firstElement().createVariable(), input.firstElement()
				.createVariable()));

		return output;
	}
}
