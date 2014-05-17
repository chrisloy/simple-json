package net.chrisloy.json

import org.scalatest.{MustMatchers, FlatSpec}

class JsonValueSpec extends FlatSpec with MustMatchers {

  "/" should "find JsonUndefined for non-matching object" in {
    Json("b" -> 123) / "a" mustBe JsonUndefined
  }

  it should "find JsonUndefined for a JsonArray" in {
    Json("a" :: 123 :: "" :: Nil) / "a" mustBe JsonUndefined
  }

  it should "find JsonUndefined for a JsonString" in {
    Json("blah") / "a" mustBe JsonUndefined
  }

  it should "find JsonUndefined for a number" in {
    Json(123) / "a" mustBe JsonUndefined
  }

  it should "find JsonUndefined for JsonTrue" in {
    Json(in = true) / "a" mustBe JsonUndefined
  }

  it should "find JsonUndefined for JsonFalse" in {
    Json(in = false) / "a" mustBe JsonUndefined
  }

  it should "find the right value when matching" in {
    Json("k" -> "value") / "k" mustBe Json("value")
  }

  "Multiple /" should "find a deeper match" in {
    val obj = Json("a" -> Map("b" -> Map("c" -> 123)))
    obj / "a" / "b" / "c" mustBe Json(123)
  }
}
