/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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

package net.imagej.ops.map;

import java.util.Iterator;

import net.imagej.ops.Op;
import net.imagej.ops.Ops;

import org.scijava.Priority;
import org.scijava.plugin.Plugin;

/**
 * {@link Map} from {@link Iterable} to {@link Iterable}.
 * 
 * @author Martin Horn
 * @author Christian Dietz
 */
@Plugin(type = Op.class, name = Ops.Map.NAME, priority = Priority.LOW_PRIORITY - 1)
public class MapIterableToIterable<A, B> extends
	AbstractMapFunction<A, B, Iterable<A>, Iterable<B>>
{

	@Override
	public Iterable<B> compute(final Iterable<A> input, final Iterable<B> output)
	{
		final Iterator<A> inCursor = input.iterator();
		final Iterator<B> outCursor = output.iterator();

		while (inCursor.hasNext()) {
			func.compute(inCursor.next(), outCursor.next());
		}

		return output;
	}
}
