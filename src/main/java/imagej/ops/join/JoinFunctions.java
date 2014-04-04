
package imagej.ops.join;

import imagej.ops.Function;
import imagej.ops.OutputFactory;

import java.util.List;

/**
 * A join operation which joins a list of {@link Function}s.
 * 
 * @author Christian Dietz
 * @author Curtis Rueden
 */
public interface JoinFunctions<A, F extends Function<A, A>> extends
	Function<A, A>, Join
{

	OutputFactory<A, A> getBufferFactory();

	void setBufferFactory(OutputFactory<A, A> buffer);

	List<? extends F> getFunctions();

	void setFunctions(List<? extends F> functions);

}