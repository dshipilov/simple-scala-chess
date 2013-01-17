package chess.commands

import chess.{Color, NoOp, Board}

object BoardCommand extends Command with Parser {
  def apply(board : Board) = {
    board.print()

    (board, NoOp)
  }

  def parse(expr : String, color: Color) = {
    if (matches(expr)) {
      Option(BoardCommand)
    } else None
  }

  def matches(expr: String) = expr.matches("""(?i)^b(?:oard)?$""")
}
