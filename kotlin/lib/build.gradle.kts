plugins {
	// Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
	id("org.jetbrains.kotlin.jvm") version "1.5.0"

	// Apply the java-library plugin for API and implementation separation.
	`java-library`
}

repositories {
	// Use Maven Central for resolving dependencies.
	mavenCentral()
}

dependencies {
	// Align version of all Kotlin components
	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

	// Use the Kotlin JDK 8 standard library.
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Use the Kotlin test library.
	testImplementation("org.jetbrains.kotlin:kotlin-test")
}
