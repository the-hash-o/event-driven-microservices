plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.appsdeveloperblog.estore"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

extra["springCloudVersion"] = "2024.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("com.google.guava:guava:33.4.5-jre")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.4")
    implementation("org.axonframework:axon-spring-boot-starter:4.11.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.4")
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.modelmapper:modelmapper:3.2.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("com.appsdeveloperblog.estore:estore-core:0.0.2-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.36")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
