[![JDK Compatibility](https://img.shields.io/badge/JDK_-11+-blue.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Apache2 License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://github.com/adonix-org/postrise/blob/main/LICENSE)
[![Build Status](https://github.com/adonix-org/postrise/actions/workflows/build.yml/badge.svg)](https://github.com/adonix-org/postrise/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.adonix%3Apostrise&metric=alert_status)](https://sonarcloud.io/summary/overall?id=org.adonix%3Apostrise)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=org.adonix%3Apostrise&metric=security_rating)](https://sonarcloud.io/summary/overall?id=org.adonix%3Apostrise)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.adonix%3Apostrise&metric=coverage)](https://sonarcloud.io/summary/overall?id=org.adonix%3Apostrise)

<a href="https://postrise.adonix.org">
    <picture>
        <source srcset="./img/header-dark.png" media="(prefers-color-scheme: dark)">
        <img src="./img/header-light.png" alt="Postrise" height="auto" width="550px"></img>
    </picture>
</a>

Get connected *FAST* with **Postrise**, a thread-safe Java library for developers to acquire pooled JDBC connections from PostgreSQL. Postrise provides a simple, object-oriented solution for configuring data sources while encouraging safe database access. The event-based architecture also enables subscriptions to the data source lifecycle. Connection pooling is delegated to the exceptional [HikariCP](https://github.com/brettwooldridge/HikariCP) implementation.

![Code](./img/code.png)

## Install

💡 Find the latest **Postrise** version and additional installation snippets in the [Maven Central Repository](https://central.sonatype.com/artifact/org.adonix/postrise).

#### Maven

```xml
<properties>
    <version.postrise>1.0.0</version.postrise>
</properties>

<dependency>
    <groupId>org.adonix</groupId>
    <artifactId>postrise</artifactId>
    <version>${version.postrise}</version>
    <scope>compile</scope>
</dependency>
```

#### Gradle

```gradle
dependencies {
    implementation 'org.adonix:postrise:1.0.0'
}
```

## Quickstart
