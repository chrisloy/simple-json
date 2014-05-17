package net.chrisloy.json

import org.scalatest.{MustMatchers, FlatSpec}

class JsonValueSpec extends FlatSpec with MustMatchers {

  """/ "value"""" should "find None for non-matching object" in {
    Json("b" -> 123) / "a" mustBe None
  }

  it should "find None for a JsonArray" in {
    Json("a" :: 123 :: "" :: Nil) / "a" mustBe None
  }

  it should "find None for a JsonString" in {
    Json("blah") / "a" mustBe None
  }

  it should "find None for a number" in {
    Json(123) / "a" mustBe None
  }

  it should "find None for JsonTrue" in {
    Json(in = true) / "a" mustBe None
  }

  it should "find None for JsonFalse" in {
    Json(in = false) / "a" mustBe None
  }

  it should "find Some(match) when only match" in {
    Json("k" -> "value") / "k" mustBe Some(Json("value"))
  }
}
