package chess

import pieces.PieceType

sealed trait Color
case object Black extends Color
case object White extends Color

case class Piece(pieceType : PieceType, color : Color) extends PieceType {
  val mnemonic : Char = color match {
    case Black => pieceType.mnemonic.toLower
    case White => pieceType.mnemonic.toUpper
  }

  def validate(board: Board, move:Move) : Boolean = pieceType.validate(board, move)
}

