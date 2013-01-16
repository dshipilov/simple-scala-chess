package chess.commands

import chess.Move
import chess.pieces.PieceType
import chess._

object MoveCommand extends Parser {
  val pattern = """(?i)^([PRNBQK])?([A-H][1-8])?([A-H][1-8])"""

  def matches(expr : String) = expr.matches(pattern)

  def parse(expr: String, color: Color) = {
    val matcher = pattern.r
    val matcher(p, s, d) = expr

    (PieceType(p), Position(s), Position(d)) match {
      case Tuple3(piece, Some(src), Some(dst)) =>
        Option(new MoveCommand(Move(Piece(piece, color), dst, Option(src))))
      case Tuple3(piece, None, Some(dst)) =>
        Option(new MoveCommand(Move(Piece(piece, color), dst, None)))

      case _ => None
    }
  }
}

class MoveCommand(move: Move) extends Command {
  def apply(board: Board) = {

    (board, Moved(move))
  }
}