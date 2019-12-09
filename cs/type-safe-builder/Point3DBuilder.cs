namespace type_safe_builder
{
	public struct Point3DBuilder<XType, YType, ZType>
		where XType: StaticOptional<double>
		where YType: StaticOptional<double>
		where ZType: StaticOptional<double>
	{
		public XType XComponent { get; }
		public YType YComponent { get; }
		public ZType ZComponent { get; }

		public Point3DBuilder(XType xComponent, YType yComponent, ZType zComponent)
		{
			XComponent = xComponent;
			YComponent = yComponent;
			ZComponent = zComponent;
		}
	}
}
