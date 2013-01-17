package chess

import pieces.PieceType

sealed trait Color {
  val complement : Color
}
case object Black extends Color { val complement = White }
case object White extends Color { val complement = Black }

case class Piece(pieceType : PieceType, color : Color) extends PieceType {
  val mnemonic : Char = color match {
    case Black => pieceType.mnemonic.toLower
    case White => pieceType.mnemonic.toUpper
  }

  override def validate(board: Board, move:Move) : Boolean = pieceType.validate(board, move)

  override def toString = color.toString + " " + pieceType.toString
}

