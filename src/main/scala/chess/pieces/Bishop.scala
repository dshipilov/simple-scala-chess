package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object Bishop extends PieceType {
  val mnemonic: Char = 'B'

  def validate(move : Move) : Boolean = {
    true
  }
}
