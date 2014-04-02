package chrisloy.json

/**
 * Main entry point for parsing and creating JSON objects.
 */
object Json {

  private val parser = new Parser

  def parse(input: String): JsonValue = parser.parse(input)
}
