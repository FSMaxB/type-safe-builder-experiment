// @ts-ignore
interface StaticOptional<Type> {}

class StaticSome<Type> implements StaticOptional<Type> {
	element: Type;

	constructor(element: Type) {
		this.element = element;
	}
}

class StaticNone<Type> implements StaticOptional<Type> {}

class Point3D {
	x: number;
	y: number;
	z: number;

	constructor(x: number, y: number, z: number) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Point3DBuilder<X extends StaticOptional<number>, Y extends StaticOptional<number>, Z extends StaticOptional<number>> {
	x: X;
	y: Y;
	z: Z;

	constructor(x: X, y: Y, z: Z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

function point3d(): Point3DBuilder<StaticNone<number>, StaticNone<number>, StaticNone<number>> {
	return new Point3DBuilder<StaticNone<number>, StaticNone<number>, StaticNone<number>>(new StaticNone<number>(), new StaticNone<number>(), new StaticNone<number>());
}

function x<Y extends StaticOptional<number>, Z extends StaticOptional<number>>(builder: Point3DBuilder<StaticNone<number>, Y, Z>, x: number): Point3DBuilder<StaticSome<number>, Y, Z> {
	return new Point3DBuilder<StaticSome<number>, Y, Z>(new StaticSome<number>(x), builder.y, builder.z);
}

function y<X extends StaticOptional<number>, Z extends StaticOptional<number>>(builder: Point3DBuilder<X, StaticNone<number>, Z>, y: number): Point3DBuilder<X, StaticSome<number>, Z> {
	return new Point3DBuilder<X, StaticSome<number>, Z>(builder.x, new StaticSome<number>(y), builder.z);
}

function z<X extends StaticOptional<number>, Y extends StaticOptional<number>>(builder: Point3DBuilder<X, Y, StaticNone<number>>, z: number): Point3DBuilder<X, Y, StaticSome<number>> {
	return new Point3DBuilder<X, Y, StaticSome<number>>(builder.x, builder.y, new StaticSome<number>(z));
}

function build(builder: Point3DBuilder<StaticSome<number>, StaticSome<number>, StaticSome<number>>): Point3D {
	return new Point3D(builder.x.element, builder.y.element, builder.z.element);
}

let point = build(x(y(z(point3d(), 3.0), 2.0), 1.0));
let expected = new Point3D(1.0, 2.0, 3.0);

console.assert(point.x === expected.x);
console.assert(point.y === expected.y);
console.assert(point.z === expected.z);
console.assert(point instanceof Point3D);
console.assert(expected instanceof Point3D);
