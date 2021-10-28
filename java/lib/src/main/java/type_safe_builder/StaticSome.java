package type_safe_builder;

record StaticSome<Type>(Type element) implements StaticOptional<Type> {}
