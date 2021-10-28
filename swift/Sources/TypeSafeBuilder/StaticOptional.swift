public protocol StaticOptional {
	associatedtype Element
}

public struct StaticSome<Type>: StaticOptional {
	public typealias Element = Type

	let element: Type
}

public struct StaticNone<Type>: StaticOptional {
	public typealias Element = Type
}
