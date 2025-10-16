# sbom-cli

A minimal Kotlin CLI tool for ingesting and querying Software Bill of Materials (SBOMs).

## Build

```bash
./gradlew shadowJar
```


## Run

```
java -jar build/libs/sbom-cli.jar ingest samples/cdx.json
java -jar build/libs/sbom-cli.jar query --component express --version 4.18.2
java -jar build/libs/sbom-cli.jar query --license MIT
```