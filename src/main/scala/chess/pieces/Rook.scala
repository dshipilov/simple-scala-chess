package main.scala.chess.pieces

import chess.Move
import chess.pieces.PieceType

case object Rook extends PieceType {
  val mnemonic: Char = 'R'

  def validate(move : Move) : Boolean = {
    true
  }
}
