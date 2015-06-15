
package net.imagej.ops.geometric.ops;

import net.imagej.ops.Op;
import net.imagej.ops.OpService;
import net.imagej.ops.geometric.GeometricOps.CenterOfGravityOp;
import net.imglib2.RealLocalizable;
import net.imglib2.roi.labeling.LabelRegion;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Returns the center of gravity of a LabelRegion
 * 
 * @author Christian Dietz, University of Konstanz.
 */
@Plugin(type = Op.class, name = CenterOfGravityOp.NAME)
public class LabelRegionCenterOfGravityOp implements
	CenterOfGravityOp<RealLocalizable>
{

	@Parameter(type = ItemIO.OUTPUT)
	private RealLocalizable output;

	@Parameter(type = ItemIO.INPUT)
	private LabelRegion<?> input;

	@Parameter
	private OpService ops;

	@Override
	public RealLocalizable getOutput() {
		return output;
	}

	@Override
	public void setOutput(final RealLocalizable output) {
		this.output = output;
	}

	@Override
	public void run() {
		output = input.getCenterOfMass();
	}

}
