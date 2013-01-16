package chess.pieces

import chess.{Board, Move}

case object Bishop extends PieceType {
  val mnemonic: Char = 'B'

  def validate(board: Board, move: Move) : Boolean = {
    true
  }
}
