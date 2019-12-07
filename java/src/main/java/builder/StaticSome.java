package builder;

public final class StaticSome<Type> implements StaticOptional<Type> {
	private final Type element;

	public StaticSome(Type element) {
		this.element = element;
	}

	public Type getElement() {
		return this.element;
	}
}
