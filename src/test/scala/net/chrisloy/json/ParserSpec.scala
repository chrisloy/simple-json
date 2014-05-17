package net.chrisloy.json

import org.scalatest._

class ParserSpec extends FlatSpec with MustMatchers {

  val parser = new JsonParser

  ".parse" should "parse an empty array" in {
    parser.parse("[]") mustBe JsonArray(Nil)
  }

  it should "parse an empty object" in {
    parser.parse("{}") mustBe JsonObject(Map.empty)
  }

  it should "parse a string" in {
    parser.parse("\"foo\"") mustBe JsonString("foo")
  }

  it should "parse booleans" in {
    parser.parse("true") mustBe JsonTrue
    parser.parse("false") mustBe JsonFalse
  }

  it should "parse a floating point number" in {
    parser.parse("123.456") mustBe JsonNumber(123.456)
  }

  it should "parse a simple object" in {
    parser.parse("""{"one":1,"two":2}""") mustBe JsonObject(Map("one" -> JsonNumber(1), "two" -> JsonNumber(2)))
  }
}
