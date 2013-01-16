package chess.pieces

import chess.{Black, White, Board, Move}
import java.math.MathContext

case object Pawn extends PieceType {
  val mnemonic: Char = 'P'

  def validate(board: Board, move : Move) : Boolean = move.src match {
    case Some(src)
      if !board(src).isEmpty && board(src).get.pieceType == Pawn =>

      val offset = move.piece.color match {
        case White => 1
        case Black => -1
      }

      move.dst.diff(src) match {
        case Tuple2(0, y) if y == offset => true // TODO: fix this
        case Tuple2(x, y) if Math.abs(x) == 1 => true // TODO: fix this

        case _ => false
      }

    case _ => false
  }
}
