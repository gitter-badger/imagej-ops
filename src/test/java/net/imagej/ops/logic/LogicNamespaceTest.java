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

package net.imagej.ops.logic;

import net.imagej.ops.AbstractNamespaceTest;
import net.imagej.ops.LogicOps.And;
import net.imagej.ops.LogicOps.Equal;
import net.imagej.ops.LogicOps.GreaterThan;
import net.imagej.ops.LogicOps.GreaterThanOrEqual;
import net.imagej.ops.LogicOps.LessThan;
import net.imagej.ops.LogicOps.LessThanOrEqual;

import org.junit.Test;

/**
 * Tests that the ops of the logic namespace have corresponding type-safe Java
 * method signatures declared in the {@link LogicNamespace} class.
 * 
 * @author Curtis Rueden
 */
public class LogicNamespaceTest extends AbstractNamespaceTest {

	/** Tests for {@link And} method convergence. */
	@Test
	public void testAnd() {
		assertComplete("logic", LogicNamespace.class, And.NAME);
	}

	/** Tests for {@link Equal} method convergence. */
	@Test
	public void testEqual() {
		assertComplete("logic", LogicNamespace.class, Equal.NAME);
	}

	/** Tests for {@link GreaterThan} method convergence. */
	@Test
	public void testGreaterThan() {
		assertComplete("logic", LogicNamespace.class, GreaterThan.NAME);
	}

	/** Tests for {@link GreaterThanOrEqual} method convergence. */
	@Test
	public void testGreaterThanOrEqual() {
		assertComplete("logic", LogicNamespace.class, GreaterThanOrEqual.NAME);
	}

	/** Tests for {@link LessThan} method convergence. */
	@Test
	public void testLessThan() {
		assertComplete("logic", LogicNamespace.class, LessThan.NAME);
	}

	/** Tests for {@link LessThanOrEqual} method convergence. */
	@Test
	public void testLessThanOrEqual() {
		assertComplete("logic", LogicNamespace.class, LessThanOrEqual.NAME);
	}
}
