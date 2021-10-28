public struct Point3DBuilder<X, Y, Z>
	where
		X: StaticOptional,
		X.Element == Double,
		Y: StaticOptional,
		Y.Element == Double,
		Z: StaticOptional,
		Z.Element == Double
{
	let x: X
	let y: Y
	let z: Z
}

public func point3d() -> Point3DBuilder<StaticNone<Double>,StaticNone<Double>,StaticNone<Double>> {
	Point3DBuilder(x: StaticNone(), y: StaticNone(), z: StaticNone())
}

extension Point3DBuilder
		where X == StaticNone<Double>
{
	public func x(_ x: Double) -> Point3DBuilder<StaticSome<Double>, Y, Z> {
		Point3DBuilder<StaticSome<Double>, Y, Z>(x: StaticSome(element: x), y: y, z: z)
	}
}

extension Point3DBuilder
	where Y == StaticNone<Double>
{
	public func y(_ y: Double) -> Point3DBuilder<X, StaticSome<Double>, Z> {
		Point3DBuilder<X, StaticSome<Double>, Z>(x: x, y: StaticSome(element: y), z: z)
	}
}

extension Point3DBuilder
	where Z == StaticNone<Double>
{
	public func z(_ z: Double) -> Point3DBuilder<X, Y, StaticSome<Double>> {
		Point3DBuilder<X, Y, StaticSome<Double>>(x: x, y: y, z: StaticSome(element: z))
	}
}

extension Point3DBuilder
	where
		X == StaticSome<Double>,
		Y == StaticSome<Double>,
		Z == StaticSome<Double>
{
	public func build() -> Point3D {
		Point3D(x: x.element, y: y.element, z: z.element)
	}
}
