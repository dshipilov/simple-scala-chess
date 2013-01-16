package chess.pieces

import chess.{Board, Move}
import scala.Some

trait PieceType {
  val mnemonic : Char

  def validate(board: Board, move : Move) : Boolean
}

object PieceType {
  private val pieces = Map(
    'P' -> Pawn,
    'R' -> Rook,
    'N' -> Knight,
    'B' -> Bishop,
    'Q' -> Queen,
    'K' -> King
  )

  def apply(mnemonic : String) : PieceType =
    Option(mnemonic) match {
        case Some(s) if !s.isEmpty => pieces.get(mnemonic(0)) match {
          case Some(p) => p
          case _ => Pawn
        }
        case _ => Pawn
    }
}