package chess.pieces

import chess.{Board, Move}

case object Knight extends PieceType {
  val mnemonic: Char = 'N'

  override def validate(board: Board, move: Move) : Boolean = {
    val diff = move.dst.diff(move.src.get)

    (Math.abs(diff._1), Math.abs(diff._2)) match {
      case (2, 1) => true
      case (1, 2) => true
      case _ => false
    }
  }
}
