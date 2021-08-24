# TimeTraceReporter

Measure the execution time of each task.

## Features

- Output CSV file
- Print graph to Console (TODO)

## Usage

```gradle
plugins {
    id "com.k4zy.time-trace" version "0.1.1"
}
```

## Configure the plugin

```gradle
timeTraceReporter {
    csvReport = true
}
```
