package chess.pieces

import chess.{Board, Move}

case object Queen extends PieceType {
  val mnemonic: Char = 'Q'

  def validate(board:Board, move: Move) : Boolean = {
    true
  }
}
