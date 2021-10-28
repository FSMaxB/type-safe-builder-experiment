package builder

import kotlin.test.Test
import kotlin.test.assertEquals

class BuilderTests {
	@Test
	fun itShouldBuildAPoint() {
		val point = point3d()
			.z(3.0)
			.y(2.0)
			.x(1.0)
			.build();

		val expected = Point3D(x=1.0, y=2.0, z=3.0)

		assertEquals(expected, point)
	}
}

