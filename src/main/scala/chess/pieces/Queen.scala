package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object Queen extends PieceType {
  val mnemonic: Char = 'Q'

  def validate(move : Move) : Boolean = {
    true
  }
}
