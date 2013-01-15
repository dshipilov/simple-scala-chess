package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object King extends PieceType {
  val mnemonic: Char = 'K'

  def validate(move : Move) : Boolean = {
    true
  }
}
