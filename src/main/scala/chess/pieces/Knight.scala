package chess.pieces

import chess.{Board, Move}

case object Knight extends PieceType {
  val mnemonic: Char = 'N'

  def validate(board: Board, move: Move) : Boolean = {

    true
  }
}
