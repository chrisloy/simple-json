package chrisloy.json

import org.scalatest.{MustMatchers, FlatSpec}

class JsonImplicitSpec extends FlatSpec with MustMatchers {

  import Json.Implicits._

  "Json.Implicits" should "create a string" in {
    val json: JsonValue = "blah"
    json mustBe JsonString("blah")
  }

  it should "create an arbitrarily complex object" in {
    val json: JsonValue = Map(
      "a" -> Seq(1, 2, 3, Map("g" -> 123.456)),
      "b" -> 1234567890,
      "c" -> false,
      "d" -> true,
      "e" -> null,
      "f" -> "things"
    )
    json.getClass mustBe classOf[JsonObject]
    json mustBe JsonObject(Map(
      "a" -> JsonArray(Seq(
        JsonNumber(1),
        JsonNumber(2),
        JsonNumber(3),
        JsonObject(Map("g" -> JsonNumber(123.456))))),
      "b" -> JsonNumber(1234567890),
      "c" -> JsonFalse,
      "d" -> JsonTrue,
      "e" -> JsonNull,
      "f" -> JsonString("things")
    ))
  }
}
