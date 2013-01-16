package chess

trait Result

case object NoOp extends Result
case object Finished extends Result

case class AmbiguousMove(move: Move) extends Result {
  override def toString = String.format("Ambiguous move: %s", move.toString)
}
case class InvalidMove(move: Move) extends Result {
  override def toString = String.format("Invalid move: %s", move.toString)
}

case class Moved(move: Move) extends Result {
  override def toString = move.toString
}

case class Captured(piece: Piece, move: Move) extends Result {
  override def toString = String.format("%s: captured %s", move.toString, piece.toString)
}

case class Check(color: Color, piece: Piece) extends Result
case class CheckMate(color: Color, piece: Piece) extends Result
