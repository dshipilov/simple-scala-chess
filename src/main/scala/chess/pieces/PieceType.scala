package chess.pieces

import chess._

trait PieceType {
  val mnemonic : Char

  val directions : Set[Direction] = Directions.all
  val maxMoveDistance : Option[Int] = None

  /**
   * Default move validation code.
   * Configured with possible directions Set and maximum move distance
   * Works for Rook, Bishop, Queen and King
   *
   * @param board chessboard instance
   * @param move move descriptor
   * @return true if the move is allowed
   */
  def validate(board: Board, move : Move) : Boolean = {
    val src = move.src.get
    val moveDistance = move.dst.distance(src)

    Position.direction(move.dst, src) match {
      case Some(dir) if directions.contains(dir) && !moveDistance.isEmpty =>
        board.nearestPiece(src, dir) match {
          case Some((piece, pos)) =>
            val limit =
              if (maxMoveDistance.isEmpty)
                src.distance(pos).get
              else
                Math.min(maxMoveDistance.get, src.distance(pos).get) // enforced move distance limit is enabled

            if (piece.color == move.piece.color)
              moveDistance.get < limit
            else
              moveDistance.get <= limit // because capture is possible in this case

          case _ =>
            if (!maxMoveDistance.isEmpty)
              moveDistance.get <= maxMoveDistance.get // enforce move distance limit
            else
              true
        }

      case _ => false
    }
  }
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
        case Some(s) if !s.isEmpty => pieces.get(mnemonic(0).toUpper) match {
          case Some(p) => p
          case _ => Pawn
        }
        case _ => Pawn
    }
}