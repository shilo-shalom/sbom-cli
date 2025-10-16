# sbom-cli

A simple Kotlin CLI tool to ingest and query **Software Bill of Materials (SBOMs)**.  
Supports **CycloneDX 1.6** and **SPDX 3.0** formats.

---

## Features
- Automatically detects SBOM format  
- Parses, normalizes, and stores SBOMs in **SQLite**  
- Queries by component (with optional version) or license  
- Clean layered design (CLI → Business → Data)  
- Built with **Clikt**, **Exposed**, **Jackson**, and **JUnit5**

---

## Build

```bash
./gradlew shadowJar
```

Output:
```
build/libs/sbom-cli.jar
```

---

## Usage

### Ingest SBOM
```bash
java -jar build/libs/sbom-cli.jar ingest sample-sboms/cyclonedx-sample.json
java -jar build/libs/sbom-cli.jar ingest sample-sboms/spdx-sample.json
```

### Query by Component
```bash
java -jar build/libs/sbom-cli.jar query --component express
java -jar build/libs/sbom-cli.jar query --component express --version 4.18.2
```

### Query by License
```bash
java -jar build/libs/sbom-cli.jar query --license MIT
```

---

## Database
- Default: **SQLite** (`sbom.db` in current directory)  
- Override connection via environment variable:
  ```bash
  export JDBC_URL=jdbc:postgresql://localhost:5432/sbomdb
  ```

---

## Project Structure
```
sbom-cli/
├── cli/        # CLI commands
├── bl/         # Parsing and normalization
├── dal/        # Database and repositories
├── samples/    # Example SBOM files
└── build.gradle.kts
```

---

## Example Output
```
Found in 2 SBOMs:
- example-webapp (v2.5.1) — License: MIT
- sample-node-api (v1.0.0) — License: MIT
```
