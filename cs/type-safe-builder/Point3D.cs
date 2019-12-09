namespace type_safe_builder
{
	public struct Point3D
	{
		public double X { get; }
		public double Y { get; }
		public double Z { get; }

		public Point3D(double x, double y, double z)
		{
			X = x;
			Y = y;
			Z = z;
		}
	}
}
