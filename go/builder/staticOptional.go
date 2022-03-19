package builder

type StaticSome[T any] struct {
	Value T
}

type StaticNone struct{}

type StaticOptional[T any] interface {
	StaticSome[T] | StaticNone
}
