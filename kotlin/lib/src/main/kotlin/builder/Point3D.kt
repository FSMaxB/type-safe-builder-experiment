package builder

data class Point3D(val x: Double, val y: Double, val z: Double)

data class Point3DBuilder<
		X: StaticOptional<Double>,
		Y: StaticOptional<Double>,
		Z: StaticOptional<Double>
	>(val x: X, val y: Y, val z: Z)

fun <
		Y: StaticOptional<Double>,
		Z: StaticOptional<Double>>
	Point3DBuilder<StaticNone<Double>,Y,Z>.x(x: Double): Point3DBuilder<StaticSome<Double>, Y, Z>
		= Point3DBuilder(x=StaticSome(element=x), y=this.y, z=this.z)

fun <
		X: StaticOptional<Double>,
		Z: StaticOptional<Double>>
	Point3DBuilder<X, StaticNone<Double>,Z>.y(y: Double): Point3DBuilder<X, StaticSome<Double>, Z>
		= Point3DBuilder(x=this.x, y=StaticSome(element=y), z=this.z)

fun <
		X: StaticOptional<Double>,
		Y: StaticOptional<Double>>
	Point3DBuilder<X, Y, StaticNone<Double>>.z(z: Double): Point3DBuilder<X, Y, StaticSome<Double>>
		= Point3DBuilder(x=this.x, y=this.y, z=StaticSome(element=z))

fun Point3DBuilder<StaticSome<Double>, StaticSome<Double>, StaticSome<Double>>.build(): Point3D
		= Point3D(x=this.x.element, y=this.y.element, z=this.z.element)

fun point3d(): Point3DBuilder<StaticNone<Double>, StaticNone<Double>, StaticNone<Double>>
		= Point3DBuilder(x=StaticNone(), y=StaticNone(), z=StaticNone())
