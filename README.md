[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

# A implementation of the JSON-LD Context

### Introduction
This project offers a Java class based implementation of JSON-LD context.
This implementation can be used as an addition to the Thing Description or BOT implementations.

### Notice
This project was created in the research project SENSE and contains experimental implementations and therefore does not claim to be complete 
or bug-free. The use is at your own risk. Any legal claim is excluded. 

### Basic usage example

First create a pre-configured GSON instance.

```java
 Gson gson = new Gson().newBuilder()
        .registerTypeAdapter(Context[].class, GsonUtils.getContextSerializer())
        .registerTypeAdapter(Context[].class, GsonUtils.getContextDeserializer()).create();
```

Than create a context array. The context elements are created with builders. Note: The default context URL is created with a null prefix.

```java
 Context[] contexts = new Context[] {
        Context.builder().prefix(null).namespace("https://w3id.org/bot#").build(), Context.builder()
            .prefix("rdf").namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#").build(),
        Context.builder().prefix("iot").namespace("http://iotschema.org/").build()};
```

The context now can be use to create Thing Descriptions or BOT descriptions.

```java
 Thing t = Thing.builder().contexts(contexts).id(thingId)...
```

## License
Apache License

_Version 2.0, January 2004_  
http://www.apache.org/licenses/