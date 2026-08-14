# paymenthub-ee-bulk-processor

A Payment Hub EE service that turns an uploaded bulk payment file into many individual payments and drives the whole batch through the workflow.

[![License](https://img.shields.io/badge/License-MPL--2.0-blue.svg)](LICENSE)

## What it does

- Takes a bulk payment file (for example a CSV batch) that was uploaded and starts processing it.
- Splits the batch into single payment instructions and can group them into smaller sub-batches.
- Looks up the parties (payees) for each instruction before the money moves.
- Runs each payment and then collects and aggregates the results back into a batch view.
- Sends progress and final results back through callbacks, and can store files in cloud storage (AWS S3 or Azure Blob).

## How it fits into Payment Hub EE

Payment Hub EE lets a client upload one file with many payments instead of sending them one by one. Another connector handles receiving and storing the uploaded file; this service is the engine that processes it. It reads the batch, breaks it into single payments, checks each party, and hands the payments to the payment connectors that actually move the money. As results come back it puts them together again so the batch can be tracked and reported as a whole. The step-by-step flow is driven by a Zeebe (Camunda) workflow, with Apache Camel routes and Zeebe workers doing the work at each step.

## Tech stack

- Java 21
- Spring Boot 3.4
- Apache Camel 4
- Zeebe (Camunda) workers
- Gradle
- Depends on `paymenthub-ee-bom` and `paymenthub-ee-core`

## Branches

- `dev` is the active development branch — all PRs should target `dev`.
- `main` holds released versions.

## Contributing

See [contributing.md](contributing.md), our [Code of Conduct](CODE_OF_CONDUCT.md) and the [security policy](security.md).
