package builder;

import java.util.Objects;

public final class Point3D {
	private final double x;
	private final double y;
	private final double z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if ((other == null) || (other.getClass() != Point3D.class)) {
			return false;
		}

		Point3D otherPoint = (Point3D) other;
		return (this.x == otherPoint.x) && (this.y == otherPoint.y) && (this.z == otherPoint.z);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y, this.z);
	}
}
