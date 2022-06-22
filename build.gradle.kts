import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

val javaVersion by extra { 17 }

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.7.0"
	kotlin("plugin.spring") version "1.7.0"
	id("org.jetbrains.dokka") version "1.7.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = javaVersion.toString()
		allWarningsAsErrors = true
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks {
	dokkaHtml.configure {
		outputDirectory.set(buildDir.resolve("doc"))

		dokkaSourceSets {
			configureEach {
				// Set jdk version to the version used outside
				jdkVersion.set(javaVersion)

				// Use to include or exclude non public members.
				includeNonPublic.set(false)

				// Do not output deprecated members. Applies globally, can be overridden by packageOptions
				skipDeprecated.set(true)

				// Emit warnings about not documented members. Applies globally, also can be overridden by packageOptions
				reportUndocumented.set(true)

				// Do not create index pages for empty packages
				skipEmptyPackages.set(true)

				// Create or omit external links, java main docs are defined below
				noStdlibLink.set(false)
				noJdkLink.set(false)

				externalDocumentationLink {
					url.set(URL("https://docs.oracle.com/en/java/javase/17/docs/api/"))
					packageListUrl.set(URL("https://docs.oracle.com/en/java/javase/17/docs/api/allpackages-index.html"))
				}

				// Suppress a package
				perPackageOption {
					matchingRegex.set(".*\\.model.*") // will match all .internal packages and sub-packages
					suppress.set(true)
				}
			}
		}
	}
}
