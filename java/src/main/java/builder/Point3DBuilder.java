package builder;

public class Point3DBuilder<X extends StaticOptional<Double>, Y extends StaticOptional<Double>, Z extends StaticOptional<Double>> {
	private final X x;
	private final Y y;
	private final Z z;

	public Point3DBuilder(X x, Y y, Z z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public X getX() {
		return this.x;
	}

	public Y getY() {
		return this.y;
	}

	public Z getZ() {
		return this.z;
	}
}
