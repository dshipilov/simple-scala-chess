package chess

import main.scala.chess.pieces._

class Board {
  val matrix = Array.fill[Option[Piece]](8,8)(None)
}

object Board {
  def initial : Board = {
    def piecesRank(color : Color) = Array(
        Option(Piece(Rook, color)),
        Option(Piece(Knight, color)),
        Option(Piece(Bishop, color)),
        Option(Piece(Queen, color)),
        Option(Piece(King, color)),
        Option(Piece(Bishop, color)),
        Option(Piece(Knight, color)),
        Option(Piece(Rook, color))
      )

    def pawnsRank(color : Color) = Array.fill(8)(Option(Piece(Pawn, color)))

    val board = new Board

    board.matrix(7) = piecesRank(Black)
    board.matrix(6) = pawnsRank(Black)

    board.matrix(1) = pawnsRank(White)
    board.matrix(0) = piecesRank(White)

    board
  }

  def print(board : Board) : Unit = {
    val filesRow = """    a   b   c   d   e   f   g   h"""
    val separator = """  +---+---+---+---+---+---+---+---+"""

    println(filesRow)
    println(separator)

    for (rank <- board.matrix.length to 1 by -1) {
      val line =
        "%d |".format(rank) +
        board.matrix(rank - 1).map((cell) => " %s |".format(
          cell match {
            case None => " "
            case Some(piece) => piece.mnemonic
          })).reduceLeft(_+_) +
        " %d".format(rank)

      println(line)
      println(separator)
    }

    println(filesRow)
    println
  }
}