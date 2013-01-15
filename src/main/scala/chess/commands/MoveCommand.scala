package chess.commands

import chess.{Move, Board, NoOp}

object MoveCommand extends Parser {
  val pattern = """(?i)^([PRNBQK])?([A-H][1-8])?([A-H][1-8])"""

  def matches(expr : String) = expr.matches(pattern)

  def parse(expr: String): Option[Command] = {
    None
  }
}

class MoveCommand(move: Move) extends Command {
  def apply(board: Board) = {
    (board, NoOp)
  }
}