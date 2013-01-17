package chess.pieces

import chess.{Direction, Directions}

case object Bishop extends PieceType {
  val mnemonic: Char = 'B'

  override val directions = Set[Direction](Directions.NE, Directions.SE, Directions.SW, Directions.NW)
}
