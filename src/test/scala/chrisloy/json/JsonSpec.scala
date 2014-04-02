package chrisloy.json

import org.scalatest.{FlatSpec, MustMatchers}

class JsonSpec extends FlatSpec with MustMatchers {

  "Json" should "parse and render an empty array" in {
    mirror("""[]""")
  }

  it should "parse and render an empty object" in {
    mirror("""{}""")
  }

  it should "parse and render a String" in {
    mirror(""""blah"""")
  }

  it should "parse and render a number" in {
    mirror("1234567898765")
  }

  it should "parse and render false" in {
    mirror("false")
  }

  it should "parse and render true" in {
    mirror("true")
  }

  it should "parse and render an array with a single number" in {
    mirror("[1234]")
  }

  private def mirror(inOut: String) = {
    Json.parse(inOut).render mustBe inOut
  }
}
