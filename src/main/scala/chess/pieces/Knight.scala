package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object Knight extends PieceType {
  val mnemonic: Char = 'N'

  def validate(move : Move) : Boolean = {
    true
  }
}
