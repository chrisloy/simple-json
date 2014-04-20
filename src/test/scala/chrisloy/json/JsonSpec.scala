package chrisloy.json

import org.scalatest.{FlatSpec, MustMatchers}

class JsonSpec extends FlatSpec with MustMatchers {

  "Json.render" should "render an array with a single string" in {
    JsonArray(JsonString("blah") :: Nil).render mustBe "[\"blah\"]"
  }

  it should "render a map with some entries" in {
    JsonObject(Map("x" -> JsonNumber(4), "y" -> JsonNumber(5))).render mustBe """{"x":4,"y":5}"""
  }

  "Json.apply" should "create a JsonString" in {
    Json("things") mustBe JsonString("things")
  }

  it should "create a JsonNumber" in {
    Json(123) mustBe JsonNumber(123.0)
    Json(123.0F) mustBe JsonNumber(123.0)
    Json(123L) mustBe JsonNumber(123.0)
    Json(123.0D) mustBe JsonNumber(123.0)
  }

  it should "create a JsonObject" in {
    Json(
      "a" -> Nil,
      "b" -> 123,
      "c" -> "things"
    ) mustBe JsonObject(Map("a" -> JsonArray(Nil), "b" -> JsonNumber(123), "c" -> JsonString("things")))
  }

  "Json.parse & Json.render" should "parse and render an empty array" in {
    mirror("""[]""")
  }

  it should "parse and render an empty object" in {
    mirror("""{}""")
  }

  it should "parse and render a String" in {
    mirror(""""blah"""")
  }

  it should "parse and render a number" in {
    mirror("12345678")
  }

  it should "parse and render false" in {
    mirror("false")
  }

  it should "parse and render true" in {
    mirror("true")
  }

  it should "parse and render null" in {
    mirror("null")
  }

  it should "parse and render whole numbers" in {
    mirror("123")
  }

  it should "parse and render negative numbers" in {
    mirror("-123.523")
  }

  it should "parse and render decimal numbers" in {
    mirror("null")
  }

  it should "parse and render very large numbers" in {
    mirror("234567123829.9876")
  }

  it should "parse and render very small numbers" in {
    mirror("0.000000000000124364123")
  }

  it should "parse and render an array with a single number" in {
    mirror("[1234]")
  }

  it should "parse and render a complex object" in {
    mirror("""{"arr":[1,2,3,{"g":-23.87},[[],[]]],"blah":["a",123,true,false,null,{"1":1}]}""")
  }

  private def mirror(inOut: String) = {
    Json.parse(inOut).render mustBe inOut
  }
}
