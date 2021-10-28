package type_safe_builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static type_safe_builder.Point3DBuilderFunctions.*;

class BuilderTest {
	@Test
	public void testItShouldBuildAPoint() {
		var point = build(x(y(z(point3d(), 3.0), 2.0), 1.0));
		var expected = new Point3D(1.0, 2.0, 3.0);

		assertEquals(expected, point);
	}
}
