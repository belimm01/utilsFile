# utilsFile

A small Spring Boot application that reads a text file, de-duplicates its lines,
and exports them to an `.xlsx` spreadsheet using Apache POI.

## Prerequisites

- JDK 21 (Temurin recommended)
- Maven 3.9+ — or use the bundled `./mvnw` wrapper

## Build

```bash
./mvnw verify
```

This compiles the code, enforces formatting with Spotless (google-java-format),
and runs the test suite.

To auto-format sources before committing:

```bash
./mvnw spotless:apply
```

## Run

```bash
./mvnw spring-boot:run
```

Or run the packaged jar:

```bash
./mvnw package
java -jar target/utilsFile-0.0.1-SNAPSHOT.jar
```

The input/output paths are currently defined in `JavaUtils.main`. Point them at
your own `.txt` source; the matching `.xlsx` is written alongside it.

### Docker

A multi-stage, rootless (distroless) image is provided:

```bash
docker build -t utilsfile .
docker run --rm utilsfile
```

## What the utilities do

- `FileUtils.readTxt(path)` — reads a text file into a `LinkedHashSet<String>`,
  preserving order while dropping duplicate lines.
- `FileUtils.saveText(path, content)` — writes a UTF-8 text file, creating or
  truncating it.
- `FileUtils.analyzeData(dir, needle)` — walks a directory tree and prints the
  paths of files containing a given substring.
- `ExcelUtils.writeToExcel(lines, path)` — writes each line to the first column
  of a `Data` sheet in a new `.xlsx` workbook.

## Architecture

`JavaUtils` is the Spring Boot entry point and orchestrates a simple pipeline:
read a text file (`FileUtils`) then export it to Excel (`ExcelUtils`). The
utility classes are stateless and package-private with static methods.

## Tooling

- **Spotless** (google-java-format) — formatting, wired into `verify`.
- **GitHub Actions** (`.github/workflows/ci.yml`) — builds on Temurin 21, checks
  formatting, runs tests, and scans for secrets with gitleaks.
