Type Safe Builder Pattern
=========================

This is an experiment to explore the idea of a type safe builder pattern.

Essentially the idea is to have a builder that checks if all the necessary properties have been provided at compile time.
If any are missing, the program should not compile.

Bonus: Try to make the implementation as efficient (runtime + memory) as possible.

## Languages

A list of all the languages in which this pattern has been implemented here, how the usage looks like and the error message.

### C++ 17

Usage:
```cpp
constexpr auto point = point3d()
		.z(3.0)
		.y(2.0)
		.x(1.0)
		.build();
```

Error message:
```cpp
constexpr auto point = point3d()
		.z(3.0)
		.y(2.0)
		.build();
```

```
type-safe-builder/cpp17/main.cpp: In instantiation of ‘constexpr Point3D Point3DBuilder<X, Y, Z>::build() const [with A = std::enable_if<false, void>; B = std::enable_if<true, void>; C = std::enable_if<true, void>; X = StaticNone<double>; Y = StaticSome<double>; Z = StaticSome<double>]’:
type-safe-builder/cpp17/main.cpp:75:10:   required from here
type-safe-builder/cpp17/main.cpp:63:43: error: ‘const struct StaticNone<double>’ has no member named ‘element’
   63 |                 return Point3D(this->ourX.element, this->ourY.element, this->ourZ.element);
      |                                ~~~~~~~~~~~^~~~~~~
type-safe-builder/cpp17/main.cpp: In function ‘int main()’:
type-safe-builder/cpp17/main.cpp:75:10:   in ‘constexpr’ expansion of ‘point3d().Point3DBuilder<StaticNone<double>, StaticNone<double>, StaticNone<double> >::z<>(3.0e+0).Point3DBuilder<StaticNone<double>, StaticNone<double>, StaticSome<double> >::y<>(2.0e+0).Point3DBuilder<StaticNone<double>, StaticSome<double>, StaticSome<double> >::build<>()’
type-safe-builder/cpp17/main.cpp:75:32: error: ‘constexpr’ call flows off the end of the function
   75 |                         .build();
      |                                ^
```

Notes:
* Fully `constexpr`, so the builder can be used to build values at compile time.

### C#

Usage:
```cs
var point = Point3DBuilderExtensions.Point3D
	.Z(3.0)
	.Y(2.0)
	.X(1.0)
	.Build();
```

Error message:
```cs
var point = Point3DBuilderExtensions.Point3D
	.Z(3.0)
	.Y(2.0)
	.Build();
```

```
type-safe-builder/cs/type-safe-builder-test/BuilderTest.cs(11,16): error CS1929: 'Point3DBuilder<StaticNone<double>, StaticSome<double>, StaticSome<double>>' does not contain a definition for 'Build' and the best extension method overload 'Point3DBuilderExtensions.Build(Point3DBuilder<StaticSome<double>, StaticSome<double>, StaticSome<double>>)' requires a receiver of type 'Point3DBuilder<StaticSome<double>, StaticSome<double>, StaticSome<double>>' [type-safe-builder/cs/type-safe-builder-test/type-safe-builder-test.csproj]
```

### Java 17

Usage:
```java
var point = build(x(y(z(point3d(), 3.0), 2.0), 1.0));
```

Error message:
```java
var point = build(y(z(point3d(), 3.0), 2.0));
```

```
type-safe-builder/java/lib/src/test/java/builder/BuilderTest.java:10: error: incompatible types: inference variable X#1 has incompatible equality constraints StaticSome<Double>,StaticNone<Double>,X#2
		var point = build(y(z(point3d(), 3.0), 2.0));
		            ^
  where X#1,Z,X#2,Y are type-variables:
    X#1 extends StaticOptional<Double> declared in method <X#1,Z>y(Point3DBuilder<X#1,StaticNone<Double>,Z>,double)
    Z extends StaticOptional<Double> declared in method <X#1,Z>y(Point3DBuilder<X#1,StaticNone<Double>,Z>,double)
    X#2 extends StaticOptional<Double> declared in method <X#2,Y>z(Point3DBuilder<X#2,Y,StaticNone<Double>>,double)
    Y extends StaticOptional<Double> declared in method <X#2,Y>z(Point3DBuilder<X#2,Y,StaticNone<Double>>,double)
```

### Kotlin

Usage:
```kotlin
val point = point3d()
	.z(3.0)
	.y(2.0)
	.x(1.0)
	.build();
```

Error message:
```kotlin
val point = point3d()
	.z(3.0)
	.y(2.0)
	.build();
```

```
type-safe-builder/kotlin/lib/src/test/kotlin/builder/BuilderTests.kt: (12, 5): Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
public fun Point3DBuilder<StaticSome<Double>, StaticSome<Double>, StaticSome<Double>>.build(): Point3D defined in builder
```

### Rust

Usage:
```rust
let point = point3d()
	.z(3.0)
	.y(2.0)
	.x(1.0)
	.build();
```

Error message:
```rust
let point = point3d()
	.z(3.0)
	.y(2.0)
	.build();
```

```
error[E0599]: no method named `build` found for struct `Point3DBuilder<StaticNone<f64>, StaticSome<f64>, StaticSome<f64>>` in the current scope
  --> src/lib.rs:28:5
   |
28 |               .build();
   |                ^^^^^ method not found in `Point3DBuilder<StaticNone<f64>, StaticSome<f64>, StaticSome<f64>>`
   |
  ::: src/builder.rs:5:1
   |
5  | / pub struct Point3DBuilder<X, Y, Z>
6  | |     where
7  | |         X: StaticOptional<Element = f64>,
8  | |         Y: StaticOptional<Element = f64>,
...  |
13 | |     z: Z,
14 | | }
   | |_- method `build` not found for this
   |
   = note: the method was found for
           - `Point3DBuilder<StaticSome<f64>, StaticSome<f64>, StaticSome<f64>>`
```

### Swift

Usage:
```swift
let point = point3d()
	.z(3.0)
	.y(2.0)
	.x(1.0)
	.build()
```

Error Message:
```swift
let point = point3d()
	.z(3.0)
	.y(2.0)
	.build()
```

```
type-safe-builder/swift/Tests/TypeSafeBuilderTests/TypeSafeBuilderTests.swift:8:5: error: referencing instance method 'build()' on 'Point3DBuilder' requires the types 'StaticNone<Double>' and 'StaticSome<Double>' be equivalent
                        .y(2.0)
                         ^
type-safe-builder/swift/Sources/TypeSafeBuilder/Point3DBuilder.swift:43:1: note: where 'X' = 'StaticNone<Double>'
extension Point3DBuilder
^
```


### TypeScript

Usage:
```typescript
const point = build(x(y(z(point3d(), 3.0), 2.0), 1.0));
```

Error message:
```typescript
const point = build(y(z(point3d(), 3.0), 2.0));
```

```
src/index.ts:58:21 - error TS2345: Argument of type 'Point3DBuilder<StaticNone<number>, StaticSome<number>, StaticSome<number>>' is not assignable to parameter of type 'Point3DBuilder<StaticSome<number>, StaticSome<number>, StaticSome<number>>'.
  Property 'element' is missing in type 'StaticNone<number>' but required in type 'StaticSome<number>'.

58 const point = build(y(z(point3d(), 3.0), 2.0));
                       ~~~~~~~~~~~~~~~~~~~~~~~~~

  src/index.ts:5:2
    5  element: Type;
       ~~~~~~~
    'element' is declared here.
```

