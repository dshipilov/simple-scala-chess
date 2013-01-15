package chess.pieces

import chess.Move
import main.scala.chess.pieces._
import scala.Some

trait PieceType {
  val mnemonic : Char

  def validate(move : Move) : Boolean
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

  def apply(mnemonic : String) = mnemonic.length match {
    case 0 => Pawn
    case _ => pieces.get(mnemonic(0))
  }
}