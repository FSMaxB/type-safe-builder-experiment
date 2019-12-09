using System.Runtime.CompilerServices;

namespace type_safe_builder
{
	public static class Point3DBuilderExtensions
	{
		public static Point3DBuilder<StaticNone<double>, StaticNone<double>, StaticNone<double>> Point3D
			=> new Point3DBuilder<StaticNone<double>, StaticNone<double>, StaticNone<double>>();

		public static Point3DBuilder<StaticSome<double>, YType, ZType> X<YType, ZType>(this Point3DBuilder<StaticNone<double>, YType, ZType> builder,  double x)
			where YType: StaticOptional<double>
			where ZType: StaticOptional<double>
		{
			return new Point3DBuilder<StaticSome<double>, YType, ZType>(new StaticSome<double>(x), builder.YComponent, builder.ZComponent);
		}
		
		public static Point3DBuilder<XType, StaticSome<double>, ZType> Y<XType, ZType>(this Point3DBuilder<XType, StaticNone<double>, ZType> builder,  double y)
			where XType: StaticOptional<double>
			where ZType: StaticOptional<double>
		{
			return new Point3DBuilder<XType, StaticSome<double>, ZType>(builder.XComponent, new StaticSome<double>(y), builder.ZComponent);
		}

		public static Point3DBuilder<XType, YType, StaticSome<double>> Z<XType, YType>(this Point3DBuilder<XType, YType, StaticNone<double>> builder,  double z)
			where XType: StaticOptional<double>
			where YType: StaticOptional<double>
		{
			return new Point3DBuilder<XType, YType, StaticSome<double>>(builder.XComponent, builder.YComponent, new StaticSome<double>(z));
		}

		public static Point3D Build(
			this Point3DBuilder<StaticSome<double>, StaticSome<double>, StaticSome<double>> builder)
		{
			return new Point3D(builder.XComponent.Element, builder.YComponent.Element, builder.ZComponent.Element);
		}
	}
}
