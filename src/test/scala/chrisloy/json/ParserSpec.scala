package chrisloy.json

import org.scalatest._

class ParserSpec extends FlatSpec with MustMatchers {

  val parser = new Parser

  ".tokens" should "find all tokens" in {
    parser.tokens("[1, 2, 3]") mustBe List("[", "1", ",", "2", ",", "3", "]")
  }

  ".parse" should "parse an empty array" in {
    parser.parse("[]") mustBe JsonArray(Nil)
  }

  it should "parse an empty object" in {
    parser.parse("{}") mustBe JsonObject(Nil)
  }
}
