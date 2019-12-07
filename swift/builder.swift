class StaticOptional<Type> {}

class StaticNone<Type> : StaticOptional<Type> {}
class StaticSome<Type>: StaticOptional<Type> {
	let element: Type

	init(element: Type) {
		self.element = element
	}
}


struct Point3D: Equatable {
	let x: Double
	let y: Double
	let z: Double
}

struct Point3DBuilder<X, Y, Z>
	where
		X: StaticOptional<Double>,
		Y: StaticOptional<Double>,
		Z: StaticOptional<Double>
{
	let x: X
	let y: Y
	let z: Z
}

func point3d() -> Point3DBuilder<StaticNone<Double>, StaticNone<Double>, StaticNone<Double>> {
	return Point3DBuilder(x: StaticNone(), y: StaticNone(), z: StaticNone())
}

extension Point3DBuilder
	where
		X: StaticNone<Double>,
		Y: StaticOptional<Double>,
		Z: StaticOptional<Double>
{
	func x(_ x: Double) -> Point3DBuilder<StaticSome<Double>, Y, Z> {
		return Point3DBuilder<StaticSome<Double>, Y, Z>(x: StaticSome(element: x), y: self.y, z: self.z)
	}
}

extension Point3DBuilder
	where
		X: StaticOptional<Double>,
		Y: StaticNone<Double>,
		Z: StaticOptional<Double>
{
	func y(_ y: Double) -> Point3DBuilder<X, StaticSome<Double>, Z> {
		return Point3DBuilder<X, StaticSome<Double>, Z>(x: self.x, y: StaticSome(element: y), z: self.z)
	}
}

extension Point3DBuilder
	where
		X: StaticOptional<Double>,
		Y: StaticOptional<Double>,
		Z: StaticNone<Double>
{
	func z(_ z: Double) -> Point3DBuilder<X, Y, StaticSome<Double>> {
		return Point3DBuilder<X, Y, StaticSome<Double>>(x: self.x, y: self.y, z: StaticSome(element: z))
	}
}

extension Point3DBuilder
	where
		X: StaticSome<Double>,
		Y: StaticSome<Double>,
		Z: StaticSome<Double>
{
	func build() -> Point3D {
		return Point3D(x: self.x.element, y: self.y.element, z: self.z.element)
	}
}

let point = point3d()
	.z(3.0)
	.y(2.0)
	.x(1.0)
	.build()

let expected = Point3D(x: 1.0, y: 2.0, z: 3.0)

assert(point == expected)