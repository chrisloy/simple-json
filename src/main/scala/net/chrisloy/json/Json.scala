package net.chrisloy.json

/**
 * Main entry point for parsing and creating JSON objects.
 */
object Json {

  private val parser = new JsonParser

  def parse(input: String): JsonValue = parser.parse(input)

  def obj(in: (String, JsonValue)*): JsonObject = JsonObject(Map(in: _*))
  def arr(in: JsonValue*): JsonArray = JsonArray(in)

  def apply(in: String): JsonString = JsonString(in)
  def apply(in: Boolean): JsonBoolean = JsonBoolean(in)
  def apply(in: Null) = JsonNull
  def apply(in: Seq[Any]): JsonArray = JsonArray(in map Json.apply)
  def apply(in: Int): JsonNumber = JsonNumber(in)
  def apply(in: Float): JsonNumber = JsonNumber(in)
  def apply(in: Long): JsonNumber = JsonNumber(in)
  def apply(in: Double): JsonNumber = JsonNumber(in)
  def apply(in: Map[String, JsonValue]): JsonObject = JsonObject(in)

  def apply(in: (String, Any)*): JsonObject = JsonObject(in map transformElems toMap)

  private def apply(in: Any): JsonValue = in match {
    case null => JsonNull
    case x: String => JsonString(x)
    case x: Int => JsonNumber(x)
    case x: Float => JsonNumber(x)
    case x: Long => JsonNumber(x)
    case x: Double => JsonNumber(x)
    case x: Boolean => JsonBoolean(x)
    case x: Map[_, _] => JsonObject(x map transformElems toMap)
    case x: Seq[_] => JsonArray(x map (Json(_)))
    case other => throw new Exception(s"Not a valid JSON value: $other")
  }
  
  private val transformElems: PartialFunction[(Any, Any), (String, JsonValue)] = {
    case (k: String, v) => (k, Json(v))
    case (k, v) => throw new Exception(s"Only Strings may be used as keys, found $k")
  }

  object Implicits {
    implicit def string(in: String): JsonString = JsonString(in)
    implicit def array(in: Seq[JsonValue]): JsonArray = JsonArray(in)
    implicit def boolean(in: Boolean): JsonBoolean = JsonBoolean(in)
    implicit def nul(in: Null) = JsonNull
    implicit def num(in: Int): JsonNumber = JsonNumber(in)
    implicit def num(in: Float): JsonNumber = JsonNumber(in)
    implicit def num(in: Long): JsonNumber = JsonNumber(in)
    implicit def num(in: Double): JsonNumber = JsonNumber(in)
    implicit def obj(in: Map[String, JsonValue]): JsonObject = JsonObject(in)
    implicit def any(in: Any): JsonValue = Json(in)
  }
}
