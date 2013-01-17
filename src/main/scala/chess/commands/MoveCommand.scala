package chess.commands

import chess.pieces.PieceType
import chess._

object MoveCommand extends Parser {
  val pattern = """^(?i)([PRNBQK])?([A-H][1-8])?([A-H][1-8])"""

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
  def apply(board: Board) = move.src match {
    case Some(src) if !board(src).isEmpty && board(src).get == move.piece =>
      if (move.piece.pieceType.validate(board, move))
        Board(move)
      else
        (board, InvalidMove(move))

    case _ =>
      val suitablePos = board.positions(move.piece).filter((pos) =>
        move.piece.pieceType.validate(board, Move(move.piece, move.dst, Option(pos))))

      if (!suitablePos.isEmpty)
        if(suitablePos.count((_) => true) == 1) {
          Board(Move(move.piece, move.dst, suitablePos.headOption))
        } else {
          (board, AmbiguousMove(move))
        }
      else
        (board, InvalidMove(move))
  }
}