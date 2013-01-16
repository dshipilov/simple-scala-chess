package chess.commands

import chess.{Color, Board, Finished}

object QuitCommand extends Command with Parser {
  def apply(board : Board) = {
    (board, Finished)
  }

  def parse(expr : String, color: Color) = {
    if (matches(expr)) {
      Option(QuitCommand)
    } else None
  }

  def matches(expr: String) = expr.matches("""(?i)^(?:exit|quit)$""")
}
