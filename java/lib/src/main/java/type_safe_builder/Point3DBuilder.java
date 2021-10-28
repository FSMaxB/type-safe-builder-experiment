package type_safe_builder;

record Point3DBuilder<
		X extends StaticOptional<Double>,
		Y extends StaticOptional<Double>,
		Z extends StaticOptional<Double>
	>(X x, Y y, Z z) {}
