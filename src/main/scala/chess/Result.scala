package chess

import pieces.King

trait Result

case object NoOp extends Result
case object Finished extends Result

case class InvalidMove(move: Move) extends Result {
  override def toString = String.format("Invalid move: %s", move.toString)
}

case class Moved(move: Move) extends Result {
  override def toString = move.toString
}

case class AmbiguousMove(move: Move) extends Result {
  override def toString = String.format("Ambiguous move: %s", move.toString)
}

case class MoveCausesCheck(move: Move) extends Result {
  override def toString = String.format("Invalid move: %s leaves %s in check position!", move.toString, Piece(King, move.piece.color))
}

case class Captured(piece: Piece, move: Move) extends Result {
  override def toString = String.format("%s: captured %s", move.toString, piece.toString)
}

case class Check(move: Move) extends Result {
  override def toString = String.format("%s: check for %s!", move.toString, Piece(King, move.piece.color))
}

case class CheckMate(move: Move) extends Result {
  override def toString = String.format("%s: check and MATE for %s!", move.toString, Piece(King, move.piece.color))
}
