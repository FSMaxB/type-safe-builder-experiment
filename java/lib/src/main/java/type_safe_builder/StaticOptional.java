package type_safe_builder;

public sealed interface StaticOptional<Type> permits StaticNone, StaticSome {}
