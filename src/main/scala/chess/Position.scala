package chess

case class Position(file : Char, rank : Int) {
  def coord : (Int, Int) = (file.toUpper - 'A', rank - 1)

  def diff(pos : Position) = coord
}

object Position {
  val pattern = """^([a-hA-H])([1-8])$""".r("file", "rank")

  def apply(str : String) : Position = {
    Position('A', 1)
  }
}