# dynamiccompiler

Compile java code at runtime dynamically.

[![maven][maven-image]][maven-url]
[![travis][travis-image]][travis-url]

[maven-image]: https://img.shields.io/maven-central/v/com.github.henryhuang/dynamiccompiler.svg?style=flat-square
[maven-url]: https://github.com/henryhuang/dynamiccompiler
[travis-image]: https://img.shields.io/travis/henryhuang/dynamiccompiler.svg?style=flat-square
[travis-url]: https://travis-ci.org/henryhuang/dynamiccompiler

## Dependency

``` xml

<dependency>
    <groupId>com.github.henryhuang</groupId>
    <artifactId>dynamiccompiler</artifactId>
    <version>0.1.0</version>
</dependency>

```

## Usage

``` java

ClassGenerator builder = new ClassGenerator(".");
Class<?> testClass = builder.generate("Test",
		"public class Test{public static void main(String[] args){System.out.println(\"Test!\");}}");
String[] params = null;
testClass.getMethod("main", String[].class).invoke(null, (Object) params);
	
```

## License

[MIT](LICENSE)