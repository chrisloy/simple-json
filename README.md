Simple JSON
===========

Build status:
[![Build Status](https://travis-ci.org/chrisloy/simple-json.png?branch=master)](https://travis-ci.org/chrisloy/simple-json)

You what?
---------

Scala has lots of libraries for parsing, rendering, searching and transforming JSON. While many of these are fully-featured and flexible, they are on occasion all rather complex to master. Simple JSON attempts to live up to its name - by providing a simple library for interacting with JSON from Scala.

How do I get it?
----------------

Simple JSON isn't available in any public repository yet. So just download the source and build it that way.

Show me some code!
------------------

Okay, okay, you're being quite pushy. Here we go:

```scala
import chrisloy.json._

val json1 = Json.parse("""[1, 2, {"three" : 3}]""")
// returns JsonArray(List(JsonNumber(1.0), JsonNumber(2.0), JsonObject(Map(three -> JsonNumber(3.0)))))

val json2 = Json("a" -> 1, "b" -> "things", c -> -123.456)
// returns JsonObject(Map(a -> JsonNumber(1.0), b -> JsonString(things), c -> JsonNumber(-123.456)))

json1.render
// returns a String: [1,2,{"three":3}]

json2.render
// returns a String: {"a":1,"b":"things","c":-123.456}
```

If you *really* want them, there are some implicits lying around to do this for you:

```scala
import chrisloy.json._
import chrisloy.json.Json.Implicits._

val magicks: JsonValue = "a" :: 2 :: "finally" :: Nil
// returns JsonArray(List(JsonString(a), JsonNumber(2.0), JsonString(finally)))
```

Hopefully that'll keep you quiet for now.

What next?
----------

Upcoming features:
 - searching JSON
 - transformation of JSON
 - all that other good stuff
