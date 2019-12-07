#include <type_traits>

template <typename Type>
struct StaticSome {
	Type element;

	constexpr StaticSome(Type element) : element{element} {}
};

template <typename Type>
struct StaticNone {
	constexpr StaticNone() = default;
};

struct Point3D {
	double x;
	double y;
	double z;

	constexpr Point3D(double x, double y, double z) : x{x}, y{y}, z{z} {}

	constexpr auto operator==(const Point3D& other) const -> bool {
		return (this->x == other.x)
				and (this->y == other.y)
				and (this->z == other.z);
	}
};

template <typename X, typename Y, typename Z>
struct Point3DBuilder {
private:
	X ourX;
	Y ourY;
	Z ourZ;

public:
	constexpr Point3DBuilder(X x, Y y, Z z) : ourX{x}, ourY{y}, ourZ{z} {}

	template <typename A =  std::enable_if<std::is_same_v<X, StaticNone<double>>>>
	constexpr auto x(double x) const -> Point3DBuilder<StaticSome<double>, Y, Z> {
		return Point3DBuilder<StaticSome<double>, Y, Z>(StaticSome(x), this->ourY, this->ourZ);
	}

	template <typename A =  std::enable_if<std::is_same_v<Y, StaticNone<double>>>>
	constexpr auto y(double y) const -> Point3DBuilder<X, StaticSome<double>, Z> {
		return Point3DBuilder<X, StaticSome<double>, Z>(this->ourX, StaticSome(y), this->ourZ);
	}

	template <typename A =  std::enable_if<std::is_same_v<Y, StaticNone<double>>>>
	constexpr auto z(double z) const -> Point3DBuilder<X, Y, StaticSome<double>> {
		return Point3DBuilder<X, Y, StaticSome<double>>(this->ourX, this->ourY, StaticSome(z));
	}

	template <
			typename A = std::enable_if<std::is_same_v<X, StaticSome<double>>>,
			typename B = std::enable_if<std::is_same_v<Y, StaticSome<double>>>,
			typename C = std::enable_if<std::is_same_v<Z, StaticSome<double>>>
			>
	constexpr auto build() const -> Point3D {
		return Point3D(this->ourX.element, this->ourY.element, this->ourZ.element);
	}
};

constexpr auto point3d() -> Point3DBuilder<StaticNone<double>, StaticNone<double>, StaticNone<double>> {
	return {{}, {}, {}};
}

int main() {
	constexpr auto point = point3d()
			.z(3.0)
			.y(2.0)
			.x(1.0)
			.build();

	constexpr auto expected = Point3D(1.0, 2.0, 3.0);

	static_assert(point == expected);

	return 0;
}
