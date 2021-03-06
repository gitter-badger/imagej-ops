
package net.imagej.ops.create;

import net.imagej.ops.Op;
import net.imagej.ops.OpService;
import net.imagej.ops.OutputOp;
import net.imagej.ops.create.CreateOps.CreateImgFactory;
import net.imagej.ops.create.CreateOps.CreateNativeType;
import net.imglib2.Dimensions;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.type.NativeType;
import net.imglib2.util.Intervals;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Default implementation of the {@link CreateImgFactory} interface.
 *
 * @author Daniel Seebacher, University of Konstanz.
 * @author Tim-Oliver Buchholz, University of Konstanz.
 * @param <T>
 */
@Plugin(type = Op.class)
public class DefaultCreateImgFactory<T extends NativeType<T>> implements
	CreateImgFactory, OutputOp<ImgFactory<T>>
{

	@Parameter(type = ItemIO.OUTPUT)
	private ImgFactory<T> output;

	@Parameter
	private OpService ops;

	@Parameter(required = false)
	private Dimensions dims;

	@Parameter(required = false)
	private T outType;

	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		if (outType == null) {
			outType = (T) ops.run(CreateNativeType.class);
		}

		output =
			(dims == null || Intervals.numElements(dims) <= Integer.MAX_VALUE)
				? new ArrayImgFactory<T>() : new CellImgFactory<T>();
	}

	@Override
	public ImgFactory<T> getOutput() {
		return output;
	}

	@Override
	public void setOutput(final ImgFactory<T> output) {
		this.output = output;
	}
}
