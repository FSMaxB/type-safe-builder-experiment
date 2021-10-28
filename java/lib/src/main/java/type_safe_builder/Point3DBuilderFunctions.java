package type_safe_builder;

abstract public class Point3DBuilderFunctions {
	public static <Y extends StaticOptional<Double>, Z extends StaticOptional<Double>> Point3DBuilder<StaticSome<Double>, Y, Z> x(Point3DBuilder<StaticNone<Double>, Y, Z> builder, double x) {
		return new Point3DBuilder<>(new StaticSome<>(x), builder.y(), builder.z());
	}

	public static <X extends StaticOptional<Double>, Z extends StaticOptional<Double>> Point3DBuilder<X, StaticSome<Double>, Z> y(Point3DBuilder<X, StaticNone<Double>, Z> builder, double y) {
		return new Point3DBuilder<>(builder.x(), new StaticSome<>(y), builder.z());
	}

	public static <X extends StaticOptional<Double>, Y extends StaticOptional<Double>> Point3DBuilder<X, Y, StaticSome<Double>> z(Point3DBuilder<X, Y, StaticNone<Double>> builder, double z) {
		return new Point3DBuilder<>(builder.x(), builder.y(), new StaticSome<>(z));
	}

	public static Point3DBuilder<StaticNone<Double>, StaticNone<Double>, StaticNone<Double>> point3d() {
		return new Point3DBuilder<>(new StaticNone<>(), new StaticNone<>(), new StaticNone<>());
	}

	public static Point3D build(Point3DBuilder<StaticSome<Double>, StaticSome<Double>, StaticSome<Double>> builder) {
		return new Point3D(builder.x().element(), builder.y().element(), builder.z().element());
	}
}
