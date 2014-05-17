Simple JSON
===========

[![Build Status](https://travis-ci.org/chrisloy/simple-json.png?branch=master)](https://travis-ci.org/chrisloy/simple-json)

You what?
---------

Scala has lots of libraries for parsing, rendering, searching and transforming JSON. While many of these are fully-featured and flexible, they are on occasion all rather complex to master. Simple JSON attempts to live up to its name - by providing a simple library for interacting with JSON from Scala.

How do I get it?
----------------

Simple JSON isn't available in any public repository yet. So just download the source and build it using SBT.

Show me some code!
------------------

Okay, okay, you're being quite pushy. Here we go...

Parsing and rendering
---------------------

All parsing and rendering is String-based:

```scala
import net.chrisloy.json._

val json1 = Json.parse("""[1, 2, {"three" : 3}]""")
// JsonArray(List(JsonNumber(1.0), JsonNumber(2.0), JsonObject(Map(three -> JsonNumber(3.0)))))

val json2 = Json("a" -> 1, "b" -> "things", "c" -> -123.456)
// JsonObject(Map(a -> JsonNumber(1.0), b -> JsonString(things), c -> JsonNumber(-123.456)))

json1.render
// String = [1,2,{"three":3}]

json2.render
// String = {"a":1,"b":"things","c":-123.456}
```

If you *really* want them, there are some implicits lying around to do this for you:

```scala
import net.chrisloy.json._
import net.chrisloy.json.Json.Implicits._

val magicks: JsonValue = "a" :: 2 :: "finally" :: Nil
// JsonArray(List(JsonString(a), JsonNumber(2.0), JsonString(finally)))
```

Searching
---------

Searching is support via an XPath-like syntax, which works on objects:

```scala
import net.chrisloy.json._

val obj = Json("a" -> "oranges", "b" -> ("lemon" :: "lemon" :: Nil))
// JsonObject(Map(a -> JsonString(oranges), b -> JsonArray(List(JsonString(lemon), JsonString(lemon)))))

obj / "a"
// JsonString(oranges)

obj / "b"
// JsonArray(List(JsonString(lemon), JsonString(lemon)))

obj / "c"
// JsonUndefined
```

This also works at arbitrary depth:

```scala
import net.chrisloy.json._

val obj = Json("a" -> Map("b" -> Map("c" -> Map("d" -> "xyz"))))
obj / "a" / "b" / "c" / "d"
// JsonString(xyz)
```

Hopefully that'll keep you quiet for now.

What next?
----------

Upcoming features:
 - transformation of JSON
 - all that other good stuff
