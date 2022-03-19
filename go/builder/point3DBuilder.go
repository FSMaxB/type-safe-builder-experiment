package builder

import "type-safe-builder/point"

type Point3DBuilder[XType StaticOptional[float64], YType StaticOptional[float64], ZType StaticOptional[float64]] struct {
	x XType
	y YType
	z ZType
}

func Point3D() Point3DBuilder[StaticNone, StaticNone, StaticNone] {
	return Point3DBuilder[StaticNone, StaticNone, StaticNone]{
		x: StaticNone{},
		y: StaticNone{},
		z: StaticNone{},
	}
}

func X[YType StaticOptional[float64], ZType StaticOptional[float64]](builder Point3DBuilder[StaticNone, YType, ZType], x float64) Point3DBuilder[StaticSome[float64], YType, ZType] {
	return Point3DBuilder[StaticSome[float64], YType, ZType]{
		x: StaticSome[float64]{Value: x},
		y: builder.y,
		z: builder.z,
	}
}

func Y[XType StaticOptional[float64], ZType StaticOptional[float64]](builder Point3DBuilder[XType, StaticNone, ZType], y float64) Point3DBuilder[XType, StaticSome[float64], ZType] {
	return Point3DBuilder[XType, StaticSome[float64], ZType]{
		x: builder.x,
		y: StaticSome[float64]{Value: y},
		z: builder.z,
	}
}

func Z[XType StaticOptional[float64], YType StaticOptional[float64]](builder Point3DBuilder[XType, YType, StaticNone], z float64) Point3DBuilder[XType, YType, StaticSome[float64]] {
	return Point3DBuilder[XType, YType, StaticSome[float64]]{
		x: builder.x,
		y: builder.y,
		z: StaticSome[float64]{Value: z},
	}
}

func Build(builder Point3DBuilder[StaticSome[float64], StaticSome[float64], StaticSome[float64]]) point.Point3D {
	return point.Point3D{
		X: builder.x.Value,
		Y: builder.y.Value,
		Z: builder.z.Value,
	}
}
