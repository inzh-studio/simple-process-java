# Simple Process

A tool for simplify and extend the process usage with the observable value method.

[![Minimum Java Version](https://img.shields.io/badge/java-%3E%3D%2014-orange.svg?logo=OpenJDK)](https://openjdk.org/)

## Installation with Maven

Add this dependency in your Maven project:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>fr.inzh.lang</groupId>
    <artifactId>inzh-simple-process</artifactId>
    <version>1.2</version>
  </dependency>
  ...
</dependencies>
```

## Installation without Maven

For non-Maven use cases, you download jars from [Central Maven repository](https://repo1.maven.org/maven2/fr/inzh/lang/inzh-simple-process/).
 
  

-----


## Exemple:

- Basic usage, launch command and wait finish

```java

String[] cmd = ... ;
ProcessBuilder processBuilder = new ProcessBuilder(cmd);

SimpleProcess sp = new SimpleProcessBuilder()
  .processBuilder(processBuilder)
  .outputRowListener(new OutChangeListener<>())
  .errorRowListener(new ErrChangeListener<>())
  .build();

sp.start();

```

- Output basic usage, launch command and get all output rows in one string

```java

String[] cmd = ... ;
ProcessBuilder processBuilder = new ProcessBuilder(cmd);

StringAggregatorChangeListener aggregator = new StringAggregatorChangeListener();
SimpleProcess sp = new SimpleProcessBuilder()
  .processBuilder(processBuilder)
  .outputRowListener(aggregator)
  .errorRowListener(new ErrChangeListener<>())
  .build();

sp.start();

String output = aggregator.getValue();

```

- Input basic usage, launch command and send value to process

```java

String[] cmd = ... ;
ProcessBuilder processBuilder = new ProcessBuilder(cmd);

ObservableValue<String> inputValue = new ObservableValue<>();
SimpleProcess sp = new SimpleProcessBuilder()
  .processBuilder(processBuilder)
  .inputValue(inputValue)
  .outputRowListener(new OutChangeListener<>())
  .errorRowListener(new ErrChangeListener<>())
  .build();

sp.startAsync();

TimeUnit.SECONDS.sleep(2);
inputValue.setValue("ping www.google.fr");  

while(true){
    TimeUnit.SECONDS.sleep(5);
    inputValue.setValue("exit");
    break;
}

```
#

[© 2011-2022 [InZH] Studio.](https://www.inzh.fr/)