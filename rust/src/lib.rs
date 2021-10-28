use crate::builder::Point3DBuilder;
use crate::static_optional::StaticNone;

pub mod static_optional;
pub mod builder;

#[derive(PartialEq, Debug)]
pub struct Point3D {
	x: f64,
	y: f64,
	z: f64,
}

pub fn point3d() -> Point3DBuilder<StaticNone<f64>, StaticNone<f64>, StaticNone<f64>> {
	Point3DBuilder::default()
}


#[cfg(test)]
mod tests {
	use super::*;

	#[test]
	fn it_should_build_a_point() {
		let point = point3d()
			.z(3.0)
			.y(2.0)
			.x(1.0)
			.build();

		let expected = Point3D { x: 1.0, y: 2.0, z: 3.0 };

		assert_eq!(expected, point);
	}
}
