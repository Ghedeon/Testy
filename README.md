# Testy [Deprecated]
Gradle plugin that allows to run android tests on specified devices

## Usage
Testy is available on [jcenter][1]

In your root **build.gradle**, update the `buildscript` section by adding corresponding repository and classpath
```gradle
buildscript {
    repositories {
        jcenter()
        maven {
            url 'http://dl.bintray.com/ghedeon/gradle'
        }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.0.1'
        classpath 'com.vv.testy:testy:0.1'
    }
}
```
apply <em>testy</em> plugin in your module **build.gradle** file:
```gradle
apply plugin: 'com.android.application'
apply plugin: 'testy'
```
enjoy running tests:
```gradle
./gradlew connectedAndroidTest -Pdevices=<serial_number1>,<serial_number2> 
```
## License
   <http://www.apache.org/licenses/LICENSE-2.0>
   
[1]:https://bintray.com/ghedeon/gradle/testy/0.1/  
