package builder

interface StaticOptional<Type> {}

class StaticNone<Type>: StaticOptional<Type> {}
data class StaticSome<Type>(val element: Type): StaticOptional<Type>
