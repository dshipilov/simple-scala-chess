package chess.pieces

import chess.{Position, Move, Board}

case object King extends PieceType {
  val mnemonic: Char = 'K'
  override val maxMoveDistance = Option(1)

  // For kings we also need to verfy king-to-king movement cases
  override def validate(board: Board, move : Move) : Boolean = {
    val validated = super.validate(board, move)

    if (validated) {
      val src = move.src.get
      val dir = Position.direction(move.dst, src).get

      board.nearestPiece(src, dir) match {
        case Some((piece, pos)) if piece.pieceType == King =>
          pos.distance(src) match {
            case Some(d) => (d > 1)
            case _ => true
          }
        case _ => true
      }
    } else validated
  }
}
