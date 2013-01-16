package chess

sealed trait Direction {
  val shift : (Int, Int)
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

    (c1._1 - c2._1, c1._2 - c2._2)
  }

  def shift(diff: (Int, Int)) : Option[Position] =
    Position((coord._1 + diff._1, coord._2 + diff._2))

  def shift(dir: Direction) : Option[Position] =
    shift(dir.shift)

  def traverse(dir: Direction) = new PositionIterator(this, dir)

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
      if Board.horizontal.contains((x + 'A').asInstanceOf[Char]) &&
         Board.vertical.contains(y + 1) =>
      Option(Position(('A' + x).asInstanceOf[Char], y + 1))

    case _ => None
  }

  def direction(dst: Position, src: Position) : Option[Direction] = {
    val diff = dst.diff(src)

    diff match {
      case Tuple2(x, y) if Math.abs(x) == Math.abs(y) =>
        (x / Math.abs(x), y / Math.abs(y)) match {
          case Directions.NE.shift => Option(Directions.NE)
          case Directions.SE.shift => Option(Directions.SE)
          case Directions.SW.shift => Option(Directions.SW)
          case Directions.NW.shift => Option(Directions.NW)
          case _ => None
        }

      case Tuple2(0, y) if y != 0 =>
        if (y > 0) Option(Directions.N) else Option(Directions.S)

      case Tuple2(x, 0) if x != 0 =>
        if (x > 0) Option(Directions.E) else Option(Directions.W)

      case _ => None
    }
  }
}

class PositionIterator(pos: Position, dir: Direction) extends Iterator[Position] {
  var current = pos

  def hasNext: Boolean = !current.shift(dir).isEmpty
  def next(): Position = {
    current = current.shift(dir).get
    current
  }
}
