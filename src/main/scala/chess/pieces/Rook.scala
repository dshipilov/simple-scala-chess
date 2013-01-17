package chess.pieces

import chess.{Direction, Directions}

case object Rook extends PieceType {
  val mnemonic: Char = 'R'
  override val directions = Set[Direction](Directions.N, Directions.S, Directions.W, Directions.E)
}
