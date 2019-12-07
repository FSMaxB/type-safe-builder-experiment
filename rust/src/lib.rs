use std::mem::MaybeUninit;

trait StaticOptional {
	type Element;

	fn some(element: Self::Element) -> StaticSome<Self::Element> {
		StaticSome(element)
	}

	fn none() -> StaticNone<Self::Element> {
		MaybeUninit::uninit()
	}
}

struct StaticSome<Type>(Type);

impl<Type> StaticOptional for StaticSome<Type> {
	type Element = Type;
}

impl<Type> StaticOptional for StaticNone<Type> {
	type Element = Type;
}

type StaticNone<Type> = MaybeUninit<Type>;

#[derive(PartialEq, Debug)]
pub struct Point3D {
	x: f64,
	y: f64,
	z: f64,
}

struct Point3DBuilder<X, Y, Z>
where
	X: StaticOptional<Element = f64>,
	Y: StaticOptional<Element = f64>,
	Z: StaticOptional<Element = f64>,
{
	x: X,
	y: Y,
	z: Z,
}

fn point3d() -> Point3DBuilder<StaticNone<f64>, StaticNone<f64>, StaticNone<f64>> {
	Point3DBuilder {
		x: StaticNone::uninit(),
		y: StaticNone::uninit(),
		z: StaticNone::uninit(),
	}
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

pub fn build() -> Point3D {
	point3d().z(3.0).x(1.0).y(2.0).build()
}

pub fn comparison() -> Point3D {
	Point3D { x: 1.0, y: 2.0, z: 3.0 }
}

#[cfg(test)]
mod tests {
	use crate::{point3d, Point3D};

	#[test]
	fn it_should_build_a_point() {
		let point = point3d().z(3.0).x(1.0).y(2.0).build();

		let expected = Point3D { x: 1.0, y: 2.0, z: 3.0 };

		assert_eq!(expected, point);
	}
}
