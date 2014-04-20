package chrisloy.json

/**
 * Main entry point for parsing and creating JSON objects.
 */
object Json {

  private val parser = new JsonParser

  def parse(input: String): JsonValue = parser.parse(input)
}
