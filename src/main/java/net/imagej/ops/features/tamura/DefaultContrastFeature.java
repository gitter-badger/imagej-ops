/*
 * #%L
 * ImageJ OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2014 Board of Regents of the University of
 * Wisconsin-Madison and University of Konstanz.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package net.imagej.ops.features.tamura;

import net.imagej.ops.Op;
import net.imagej.ops.features.firstorder.FirstOrderFeatures.MeanFeature;
import net.imagej.ops.features.firstorder.FirstOrderFeatures.Moment2AboutMeanFeature;
import net.imagej.ops.features.firstorder.FirstOrderFeatures.Moment4AboutMeanFeature;
import net.imagej.ops.features.firstorder.FirstOrderFeatures.VarianceFeature;
import net.imagej.ops.features.tamura.TamuraFeatures.ContrastFeature;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * 
 * Default implementation of tamura's texture contrast feature.
 * No difference between 2D and 3D case.
 * 
 * @author Andreas Graumann, University of Konstanz
 * @author Christian Dietz, Univesity of Konstanz
 *
 */
@Plugin(type = Op.class, name = "Tamura: Contrast")
public class DefaultContrastFeature implements ContrastFeature<DoubleType> {

	@Parameter
	private MeanFeature<? extends RealType<?>> my;

	@Parameter
	private Moment2AboutMeanFeature<? extends RealType<?>> var;

	@Parameter
	private Moment4AboutMeanFeature<? extends RealType<?>> m4;

	@Parameter(type = ItemIO.OUTPUT)
	private DoubleType out;

	@Override
	public DoubleType getOutput() {
		return out;
	}

	@Override
	public void setOutput(DoubleType output) {
		out = output;
	}

	@Override
	public void run() {
		if (out == null) {
			out = new DoubleType();
		}
		
		System.out.println("Andi: Mean " + my.getOutput().getRealDouble());
		System.out.println("Andi: Var " + Math.sqrt(var.getOutput().getRealDouble()));
		System.out.println("Andi: m4 " + m4.getOutput().getRealDouble());

		final double l4 = m4.getOutput().getRealDouble()
				/ (Math.pow(var.getOutput().getRealDouble(), 2));
		double res = Math.sqrt(var.getOutput().getRealDouble())
				/ Math.pow(l4, 0.25);

		setOutput(new DoubleType(res));
	}

}
