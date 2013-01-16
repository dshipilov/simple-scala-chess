package chess.pieces

import chess.{Board, Move}

case object Knight extends PieceType {
  val mnemonic: Char = 'N'

  def validate(board: Board, move: Move) : Boolean = move.src match {
    case Some(src)
      if !board(src).isEmpty && board(src).get.pieceType == Knight =>

      val diff = move.dst.diff(src)

      (Math.abs(diff._1), Math.abs(diff._2)) match {
        case Tuple2(2, 1) => true
        case Tuple2(1, 2) => true
        case _ => false
      }

    case _ => false
  }
}
