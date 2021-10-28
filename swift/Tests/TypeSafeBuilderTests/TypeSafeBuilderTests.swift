import XCTest
@testable import TypeSafeBuilder

final class TypeSafeBuilderTests: XCTestCase {
    func testItShouldBuildPoints() throws {
		let point = point3d()
			.z(3.0)
			.y(2.0)
			.x(1.0)
			.build()

		let expected = Point3D(x: 1.0, y: 2.0, z: 3.0)
		XCTAssertEqual(point, expected)
	}
}
