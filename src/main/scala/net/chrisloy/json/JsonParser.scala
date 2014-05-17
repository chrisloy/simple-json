package net.chrisloy.json

import scala.util.parsing.combinator._

class JsonParser {

  val parser = new JavaTokenParsers {

    def obj: Parser[JsonObject] =
      "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _) ^^ (x => JsonObject(x))

    def arr: Parser[JsonArray] =
      "[" ~> repsep(value, ",") <~ "]" ^^ (x => JsonArray(x))

    def member: Parser[(String, JsonValue)] =
      stringLiteral ~ ":" ~ value ^^
        { case name ~ ":" ~ value => (stripQuotes(name), value) }

    def str: Parser[JsonString] =
      stringLiteral ^^ stripQuotes ^^ (x => JsonString(x))

    def num: Parser[JsonNumber] =
      floatingPointNumber ^^ (x => JsonNumber(x.toDouble))

    def value: Parser[JsonValue] = (
      obj
        | arr
        | str
        | num
        | "null"  ^^ (x => JsonNull)
        | "true"  ^^ (x => JsonTrue)
        | "false" ^^ (x => JsonFalse)
      )

    def stripQuotes(x: String) = x.substring(1, x.length - 1)
  }

  def parse(input: String): JsonValue = parser.parseAll(parser.value, input).get
}