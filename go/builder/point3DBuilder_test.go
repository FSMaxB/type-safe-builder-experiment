package builder

import (
	"testing"
	"type-safe-builder/point"
)

func TestPoint3DBuilder(t *testing.T) {
	actualPoint := Build(X(Y(Z(Point3D(), 3.0), 2.0), 1.0))
	expected := point.Point3D{
		X: 1.0,
		Y: 2.0,
		Z: 3.0,
	}

	if actualPoint != expected {
		t.Fail()
	}
}
