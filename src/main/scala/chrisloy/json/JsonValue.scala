package chrisloy.json

sealed trait JsonValue {
  final override val toString = render
  def render: String
  val q = "\""
  def quot(s: String) = s"$q$s$q"
}

case class JsonArray(elems: Seq[JsonValue]) extends JsonValue {
  def render = elems map (_.render) mkString ("[", ",", "]")
}

case class JsonObject(fields: Seq[JsonField]) extends JsonValue {
  def render = fields map (_.render) mkString ("{", ",", "}")
}

case class JsonField(key: String, value: JsonValue) extends JsonValue {
  def render = s"$key:$value"
}

case class JsonString(value: String) extends JsonValue {
  def render = quot(value)
}

case class JsonNumber(value: Long) extends JsonValue {
  def render = String.valueOf(value)
}

case class JsonBoolean(value: Boolean) extends JsonValue {
  def render = if (value) "true" else "false"
}

object JsonFalse extends JsonBoolean(false)

object JsonTrue extends JsonBoolean(true)
