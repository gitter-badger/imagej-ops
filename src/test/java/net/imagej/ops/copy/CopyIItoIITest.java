package net.imagej.ops.copy;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import net.imagej.ops.AbstractOpTest;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.numeric.real.DoubleType;

import org.junit.Before;
import org.junit.Test;

public class CopyIItoIITest extends AbstractOpTest {

	private Img<DoubleType> input;

	@Before
	public void createData() {
		input = new ArrayImgFactory<DoubleType>().create(
				new int[] { 120, 100 }, new DoubleType());
		
		final Random r = new Random(System.currentTimeMillis());

		final Cursor<DoubleType> inc = input.cursor();

		while (inc.hasNext()) {
			inc.next().set(r.nextDouble());
		}
	}

	@Test
	public void copyRAINoOutputTest() {
		@SuppressWarnings("unchecked")
		RandomAccessibleInterval<DoubleType> output = (RandomAccessibleInterval<DoubleType>) ops
				.run(CopyIIToII.class, input);

		Cursor<DoubleType> inc = input.localizingCursor();
		RandomAccess<DoubleType> outRA = output.randomAccess();

		while (inc.hasNext()) {
			inc.fwd();
			outRA.setPosition(inc);
			assertTrue(outRA.get().get() == inc.get().get());
		}
	}

	@Test
	public void copyRAIWithOutputTest() {
		Img<DoubleType> output = input.factory().create(input,
				input.firstElement());

		ops.run(CopyIIToII.class, output, input);

		final Cursor<DoubleType> inc = input.cursor();
		final Cursor<DoubleType> outc = output.cursor();

		while (inc.hasNext()) {
			assertTrue(outc.next().equals(inc.next()));
		}
	}
}
