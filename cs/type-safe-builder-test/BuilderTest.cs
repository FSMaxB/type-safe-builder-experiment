using NUnit.Framework;
using type_safe_builder;

namespace Tests
{
	public class Tests
	{
		[Test]
		public void TestItShouldBuildAPoint()
		{
			var point = Point3DBuilderExtensions.Point3D
				.Z(3.0)
				.Y(2.0)
				.X(1.0)
				.Build();

			var expected = new Point3D(1.0, 2.0, 3.0);

			Assert.AreEqual(expected, point);
		}
	}
}
