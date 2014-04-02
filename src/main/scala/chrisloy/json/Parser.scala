package chrisloy.json

class Parser {

  def parse(input: String): JsonValue = {
    readSingle(tokens(input))
  }

  def tokens(input: String): List[String] = {
    val fns = Seq(",", "[", "]", "{", "}", ":") map (char => pad(_: String, char))
    val fn = fns.reduce(_ compose _)
    val padded = fn(input)
    padded.split(" +").filterNot(_.isEmpty).toList
  }

  private def pad(input: String, char: String) = {
    input.replace(char, s" $char ")
  }

  private def readSingle(tokens: List[String]): JsonValue = tokens match {
    case Nil => throw new Exception("Unexpected EOF")
    case "{" :: tail if tail.last == "}" => JsonObject(readPairs(tail dropRight 1))
    case "[" :: tail if tail.last == "]" => JsonArray(readValues(tail dropRight 1))
    case "true" :: tail => JsonTrue
    case "false" :: tail => JsonFalse
    case atom :: tail if atom.head == '"' => JsonString(unquote(atom))
    case atom :: tail if atom.forall(_.isDigit) => JsonNumber(atom.toLong)
  }

  private def readPairs(elems: List[String]): List[JsonField] = Nil

  private def readValues(elems: List[String]): List[JsonField] = Nil

  private def unquote(in: String) = in filter (_ != '"')
}
