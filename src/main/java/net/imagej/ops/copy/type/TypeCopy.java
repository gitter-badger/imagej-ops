package net.imagej.ops.copy.type;

import net.imagej.ops.AbstractOutputFunction;
import net.imglib2.type.Type;

class TypeCopy<T extends Type<T>> extends AbstractOutputFunction<T, T> {

	@Override
	public T createOutput(T input) {
		return input.createVariable();
	}

	@Override
	protected T safeCompute(T input, T output) {
		output.set(input.copy());
		return output;
	}

}