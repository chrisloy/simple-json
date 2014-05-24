package net.chrisloy.json

object JsonValue {
  val q = "\""
}

sealed trait JsonValue {
  def render: String
  def / (path: String): JsonValue = JsonUndefined
  protected[this] def quot(s: String) = s"${JsonValue.q}$s${JsonValue.q}"
}

case class JsonArray(elems: Seq[JsonValue]) extends JsonValue {
  lazy val render = elems map (_.render) mkString ("[", ",", "]")
}

case class JsonObject(fields: Map[String, JsonValue]) extends JsonValue {
  lazy val render = fields map {
    case (key, value) => s"${quot(key)}:${value.render}"
  } mkString ("{", ",", "}")
  override def / (path: String) = fields.get(path) getOrElse JsonUndefined
}

case class JsonString(value: String) extends JsonValue {
  lazy val render = quot(value)
}

case class JsonNumber(value: Double) extends JsonValue {
  lazy val render = new java.text.DecimalFormat("#.######################").format(value)
}

object JsonBoolean {
  def apply(value: Boolean) = if (value) JsonTrue else JsonFalse
}

sealed abstract class JsonBoolean(value: Boolean) extends JsonValue

case object JsonFalse extends JsonBoolean(false) {
  val render = "false"
}

case object JsonTrue extends JsonBoolean(true) {
  val render = "true"
}

case object JsonNull extends JsonValue {
  val render = "null"
}

case object JsonUndefined extends JsonValue {
  def render = throw new Exception("Cannot render undefined values!")
}
