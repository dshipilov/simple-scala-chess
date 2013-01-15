package chess.commands

import chess.{Board, Finished}

object QuitCommand extends Command with Parser {
  def apply(board : Board) = {
    (board, Finished)
  }

  def parse(expr : String) = {
    if (matches(expr)) {
      Option(QuitCommand)
    } else None
  }

  def matches(expr: String) = expr.matches("""(?i)^(?:exit|quit)$""")
}
