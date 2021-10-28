package builder

sealed class StaticOptional<Type> {}

class StaticNone<Type>: StaticOptional<Type>() {}
data class StaticSome<Type>(val element: Type): StaticOptional<Type>()
