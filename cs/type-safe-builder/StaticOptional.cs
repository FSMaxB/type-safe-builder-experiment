namespace type_safe_builder
{
	public interface StaticOptional<Type> {}

	public struct StaticSome<Type> : StaticOptional<Type>
	{
		public Type Element { get;  }

		public StaticSome(Type element)
		{
			Element = element;
		}
	}

	public struct StaticNone<Type> : StaticOptional<Type> {}
}
