use crate::Point3D;
use crate::static_optional::{StaticNone, StaticOptional, StaticSome};

#[derive(Default)]
pub struct Point3DBuilder<X, Y, Z>
	where
		X: StaticOptional<Element = f64>,
		Y: StaticOptional<Element = f64>,
		Z: StaticOptional<Element = f64>,
{
	x: X,
	y: Y,
	z: Z,
}

impl<Y, Z> Point3DBuilder<StaticNone<f64>, Y, Z>
	where
		Y: StaticOptional<Element = f64>,
		Z: StaticOptional<Element = f64>,
{
	pub fn x(self, x: f64) -> Point3DBuilder<StaticSome<f64>, Y, Z> {
		Point3DBuilder {
			x: StaticSome(x),
			y: self.y,
			z: self.z,
		}
	}
}

impl<X, Z> Point3DBuilder<X, StaticNone<f64>, Z>
	where
		X: StaticOptional<Element = f64>,
		Z: StaticOptional<Element = f64>,
{
	pub fn y(self, y: f64) -> Point3DBuilder<X, StaticSome<f64>, Z> {
		Point3DBuilder {
			x: self.x,
			y: StaticSome(y),
			z: self.z,
		}
	}
}

impl<X, Y> Point3DBuilder<X, Y, StaticNone<f64>>
	where
		X: StaticOptional<Element = f64>,
		Y: StaticOptional<Element = f64>,
{
	pub fn z(self, z: f64) -> Point3DBuilder<X, Y, StaticSome<f64>> {
		Point3DBuilder {
			x: self.x,
			y: self.y,
			z: StaticSome(z),
		}
	}
}

impl Point3DBuilder<StaticSome<f64>, StaticSome<f64>, StaticSome<f64>> {
	pub fn build(self) -> Point3D {
		Point3D {
			x: self.x.0,
			y: self.y.0,
			z: self.z.0,
		}
	}
}
