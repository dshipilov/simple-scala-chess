package chess.pieces

import chess.{Board, Move}

case object Rook extends PieceType {
  val mnemonic: Char = 'R'

  def validate(board:Board, move : Move) : Boolean = {
    true
  }
}
