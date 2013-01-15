package chess

trait Result

case object NoOp extends Result
case object Finished extends Result

case class AmbigousMove(move: Move) extends Result
case class InvalidMove(move: Move) extends Result

case class Moved(move: Move) extends Result
case class Captured(piece: Piece, move: Move) extends Result

case class Check(color: Color, piece: Piece) extends Result
case class CheckMate(color: Color, piece: Piece) extends Result
