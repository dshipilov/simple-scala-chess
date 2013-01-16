package chess.pieces

import chess.{Board, Move}

case object King extends PieceType {
  val mnemonic: Char = 'K'

  def validate(board: Board, move: Move) : Boolean = {
    true
  }
}
