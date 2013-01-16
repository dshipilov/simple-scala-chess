package chess

sealed trait Direction {
  val shift : (Int, Int)

  // Adjust direction for particular piece color (need 180 degrees rotate for black pieces)
  def apply(color: Color) : (Int, Int) = color match {
    case Black => (shift._1 * (-1), shift._2 * (-1))
    case White => shift
  }
}

object Directions {
  case object N  extends Direction { val shift = (0, 1) }
  case object NE extends Direction { val shift = (1, 1) }
  case object E  extends Direction { val shift = (1, 0) }
  case object SE extends Direction { val shift = (1, -1) }
  case object S  extends Direction { val shift = (0, -1) }
  case object SW extends Direction { val shift = (-1, -1) }
  case object W  extends Direction { val shift = (-1, 0) }
  case object NW extends Direction { val shift = (-1, 1) }
}

case class Position(file : Char, rank : Int) {
  val coord : (Int, Int) = (file.toUpper - 'A', rank - 1)

  def diff(pos : Position) = {
    val c1 = coord
    val c2 = pos.coord

    (c2._1 - c1._1, c2._2 - c2._2)
  }

  def horizontal(pos: Position) = {
    pos.coord._2 == coord._2
  }

  def vertical(pos: Position) = {
    pos.coord._1 == coord._1
  }

  def diagonal(pos: Position) = {
    val delta = diff(pos)

    Math.abs(delta._1) == Math.abs(delta._2)
  }

  def shift(diff: (Int, Int)) : Option[Position] =
    Position((coord._1 + diff._1, coord._2 + diff._2))

  def shift(dir: Direction) : Option[Position] =
    shift(dir.shift)

  override def toString = file + rank.toString
}

object Position {
  val pattern = """^([a-hA-H])([1-8])$""".r

  def apply(expr : String) : Option[Position] =
    Option(expr) match {
      case Some(str) if !str.isEmpty =>
        val pattern(fileStr, rankStr) = str

        (Option(fileStr), Option(rankStr)) match {
          case Tuple2(Some(f), Some(r)) if !f.isEmpty && !r.isEmpty =>
            Option(Position(f(0).toUpper, Integer.parseInt(r)))

          case _ => None
        }

      case _ => None
    }

  def apply(coord: (Int, Int)) : Option[Position] = coord match {
    case Tuple2(x, y)
      if x >= Board.horizontal.start - 1 && x < Board.horizontal.end &&
        y >= Board.vertical.start - 1 && y < Board.vertical.end =>
      Option(Position(('A' + x).asInstanceOf[Char], y + 1))

    case _ => None
  }
}
