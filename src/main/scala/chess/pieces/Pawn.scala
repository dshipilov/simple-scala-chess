package chess.pieces

import chess.{Board, Move}

case object Pawn extends PieceType {
  val mnemonic: Char = 'P'

  def validate(board: Board, move : Move) : Boolean = {
    true
  }
}
