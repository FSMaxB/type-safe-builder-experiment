package builder;

import junit.framework.TestCase;
import org.junit.Test;

import static builder.Point3DBuilderFunctions.*;

public class BuilderTest extends TestCase {
	@Test
	public void testItShouldBuildAPoint() {
		Point3D point = build(x(y(z(point3d(), 3.0), 2.0), 1.0));
		Point3D expected = new Point3D(1.0, 2.0, 3.0);

		assertEquals(expected, point);


	}
}
