package chess.pieces

import chess.{Black, White, Board, Move}

case object Pawn extends PieceType {
  val mnemonic: Char = 'P'

  override def validate(board: Board, move : Move) : Boolean = {
    val src = move.src.get
    val offset = move.piece.color match {
      case White => 1
      case Black => -1
    }

    val pawnStartRank = move.piece.color match {
      case White => Board.vertical.start + 1
      case Black => Board.vertical.end - 1
    }

    move.dst.diff(src) match {
      // Pawn regular movement rule
      case Tuple2(0, y) if y == offset => board(move.dst).isEmpty

      // Pawn capture rule
      case Tuple2(x, y) if Math.abs(x) == 1 && y == offset =>
        val piece = board(move.dst)
        !piece.isEmpty && piece.get.color.complement == move.piece.color

      // Pawn starting move rule
      case Tuple2(0, y) if y == 2 * offset =>
        src.rank == pawnStartRank

      case _ => false
    }
  }
}
