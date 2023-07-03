# Dynamic selection of library version

## Step 0 - Creating two versions

```
# v1.0
lib$ git checkout v1.0
lib$ mvn clean install

# v2.0
lib$ git checkout v2.0
lib$ mvn clean install
```

## Step 1 - Testing with static compilation


```
cli-app$ mvn clean package exec:java -Dlib.version=1.0
[INFO] --- exec-maven-plugin:3.0.0:java (default-cli) @ cli-app ---
Howdy, asamolov!

cli-app$ mvn clean package exec:java -Dlib.version=2.0
[INFO] --- exec-maven-plugin:3.0.0:java (default-cli) @ cli-app ---
Greetings, asamolov!
```

## Step 2 - Shading versions

```
jar-shader$ mvn clean install -Dlib.version=1.0 -Dvariant=a
[INFO] --- maven-install-plugin:2.4:install (default-install) @ lib-a ---
[INFO] Installing C:\work\jshader\jar-shader\target\lib-a-1.0-a.jar to C:\Users\asamolov\.m2\repository\com\github\asamolov\jshader\lib-a\1.0-a\lib-a-1.0-a.jar

jar-shader$ mvn clean install -Dlib.version=2.0 -Dvariant=b
[INFO] --- maven-install-plugin:2.4:install (default-install) @ lib-b ---
[INFO] Installing C:\work\jshader\jar-shader\target\lib-b-2.0-b.jar to C:\Users\asamolov\.m2\repository\com\github\asamolov\jshader\lib-b\2.0-b\lib-b-2.0-b.jar
```

## Step 3 - building wrapper

The wrapper itself is hand-crafted in [lib-wrapper\src\main\java\com\github\asamolov\jshader\lib\Greeter.java](lib-wrapper/src/main/java/com/github/asamolov/jshader/lib/Greeter.java)

```
lib-wrapper$ mvn clean install
[INFO] --- maven-install-plugin:2.4:install (default-install) @ lib ---
[INFO] Installing C:\work\jshader\lib-wrapper\target\lib-wrapped-ab.jar to C:\Users\asamolov\.m2\repository\com\github\asamolov\jshader\lib\wrapped-ab\lib-wrapped-ab.jar
```

## Step 4 - using the wrapper

```
cli-app$ mvn clean package -Dlib.version=wrapped-ab

# default version
cli-app$ java -jar target/cli-app-1.0-SNAPSHOT.jar 
Howdy, asamolov!

# variant a (v1.0)
cli-app$ java -Dlib.variant=a -jar target/cli-app-1.0-SNAPSHOT.jar 
Howdy, asamolov!

# variant b (v2.0)
cli-app$ java -Dlib.variant=b -jar target/cli-app-1.0-SNAPSHOT.jar 
Greetings, asamolov!
```
