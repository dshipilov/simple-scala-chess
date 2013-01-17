package chess

import pieces._
import collection.mutable.{HashMap, Set}
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder

/**
 * Board represents current state of the chessboard
 * Class exposes only R/O functionality for analysing position
 *
 * Board state mutation are all in the object part of the board
 */
class Board extends Function[Position, Option[Piece]] {
  val horizontal = ('A' to 'H')
  val vertical = (1 to 8)

  protected var pos2piece = HashMap[Position, Piece]()
  protected var piece2pos = HashMap[Piece, Set[Position]]()

  def positions(piece: Piece) : Set[Position] = {
    piece2pos.get(piece) match {
      case Some(positions) => positions
      case _ => Set.empty
    }
  }

  def apply(pos: Position) = pos2piece.get(pos)

  /**
   * Find nearest piece from the given position on the given direction
   *
   * @param pos
   * @param dir
   * @return
   */
  def nearestPiece(pos: Position, dir: Direction) : Option[(Piece, Position)] =
    pos.traverse(dir).find((pos) => !this(pos).isEmpty) match {
      case Some(pos) => Option((this(pos).get, pos))
      case _ => None
    }

  /**
   * Prints current board state to the console in the ASCII mnemonic format
   */
  def print() {
    val filesRow = """    a   b   c   d   e   f   g   h"""
    val separator = """  +---+---+---+---+---+---+---+---+"""

    println(filesRow)
    println(separator)

    vertical.reverse.foreach((rank) => {
      val line =
        "%d |".format(rank) +
          ('A' to 'H').map((file) => " %s |".format(
            pos2piece.get(Position(file, rank)) match {
              case Some(piece) => piece.mnemonic
              case None => " "
            })).reduceLeft(_+_) +
          " %d".format(rank)

      println(line)
      println(separator)
    })

    println(filesRow)
    println()
  }

  def inCheckPosition(color: Color) : Boolean = {
    val theKing = Piece(King, color)
    val thePosition = piece2pos(theKing).head // the king should always be on the board

    /*
     * Look around from king's position checking to see there's some foe figures in attacking position
     */
    val foeKnight = Piece(Knight, color.complement)

    val attackingFoes = Directions.all.map((dir) => nearestPiece(thePosition, dir))
      .union(piece2pos(foeKnight).map((pos) => Option((foeKnight, pos)))) // also mixin foe knights currently on the board
      .filter((t) => t match { // only leave pieces that are able to attack the king
        case Some((piece, pos))
          if piece.color == color.complement &&
             piece.pieceType.validate(this, Move(piece, thePosition, Option(pos)))=>
          true
        case _ => false
      })

    attackingFoes.count((_) => true) > 0
  }
}

object Board extends Board {
  /**
   * Resets board to its initial state
   */
  def reset() {
    def piecesRank(color : Color) = {
      val rank = color match {
        case White => vertical.start
        case Black => vertical.end
      }

      List(
        (Piece(Rook, color),   Position('A', rank)),
        (Piece(Knight, color), Position('B', rank)),
        (Piece(Bishop, color), Position('C', rank)),
        (Piece(Queen, color),  Position('D', rank)),
        (Piece(King, color),   Position('E', rank)),
        (Piece(Bishop, color), Position('F', rank)),
        (Piece(Knight, color), Position('G', rank)),
        (Piece(Rook, color),   Position('H', rank))
      )
    }

    def pawnsRank(color : Color) = {
      val rank = color match {
        case White => vertical.start + 1
        case Black => vertical.end - 1
      }

      horizontal.map((c) => (Piece(Pawn, color), Position(c, rank)))
    }

    piece2pos.clear()
    pos2piece.clear()

    List(White, Black).foreach((color) => {
      piecesRank(color).foreach((t) => {
        addTo(t._2, t._1)
      })
      pawnsRank(color).foreach((t) => {
        addTo(t._2, t._1)
      })
    })
  }

  /**
   * Removes piece from specified board position
   *
   * @param pos
   */
  def removeAt(pos: Position) : Piece = {
    val piece = this(pos).get
    val set = positions(piece)

    piece2pos += (piece -> (set - pos))
    pos2piece.remove(pos)

    piece
  }

  /**
   * Adds piece at specified board position
   *
   * @param pos
   * @param piece
   */
  def addTo(pos: Position, piece: Piece) {
    val set = positions(piece)

    piece2pos += (piece -> (set + pos))
    pos2piece += (pos -> piece)
  }

  /**
   * Reverts board position back to the previous state
   */
  def undoMove(move: Move, captured: Option[Piece]) : Unit = {
    addTo(move.src.get, removeAt(move.dst)) // Undo move

    if (!captured.isEmpty)
      addTo(move.dst, captured.get) // Return captured piece back to the board
  }

  def lastMoveCausesCheck(last: Move, capture: Option[Piece]) : Boolean = {
    if(inCheckPosition(last.piece.color)) {
      undoMove(last, capture)
      true
    } else false
  }

  /**
   * Moves piece to the specified location
   * WARNING! All piece-specific move validation should be done before this call.
   *
   * @param move move definition
   * @return Result of the move: Moved, Captured or InvalidMove if source position does not contain piece
   */
  def apply(move: Move) : (Board, Result) =
    move.src match {
      case Some(pos) if !this(pos).isEmpty =>
        val sourcePiece = this(pos).get

        this(move.dst) match {
          case None =>
            addTo(move.dst, removeAt(pos))

            if (lastMoveCausesCheck(move, None))
              (this, MoveCausesCheck(move))
            else if (inCheckPosition(move.piece.color.complement))
              (this, Check(move))
            else
              (this, Moved(move))

          case Some(piece) if piece.color != sourcePiece.color =>
            val captured = removeAt(move.dst)
            removeAt(pos)
            addTo(move.dst, sourcePiece)

            if (lastMoveCausesCheck(move, Option(captured)))
              (this, MoveCausesCheck(move))
            else
              (this, Captured(captured, move))

          case _ => (this, InvalidMove(move))
        }
      case _ => (this, InvalidMove(move))
    }
}
