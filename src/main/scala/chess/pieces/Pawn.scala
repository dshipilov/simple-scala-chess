package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object Pawn extends PieceType {
  val mnemonic: Char = 'P'

  def validate(move : Move) : Boolean = {
    true
  }
}
