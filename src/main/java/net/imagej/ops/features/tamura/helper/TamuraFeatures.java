/*
 * #%L
 * SciJava OPS: a framework for reusable algorithms.
 * %%
 * Copyright (C) 2013 Board of Regents of the University of
 * Wisconsin-Madison, and University of Konstanz.
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
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */
package net.imagej.ops.features.tamura.helper;

/**
 * Class to hold three tamura features, calculated by {@Link
 * TamuraTextureComputer}
 * 
 * @author Andreas Graumann, University of Konstanz
 *
 */
public class TamuraFeatures {

	private double coarsness;
	private double contrast;
	private double[] directionality = new double[16];

	public double getCoarsness() {
		return coarsness;
	}

	public void setCoarsness(double coarsness) {
		this.coarsness = coarsness;
	}

	public double getContrast() {
		return contrast;
	}

	public void setContrast(double contrast) {
		this.contrast = contrast;
	}

	public double[] getDirectionality() {
		return directionality;
	}

	public void setDirectionality(double[] directionality) {
		this.directionality = directionality;
	}

}