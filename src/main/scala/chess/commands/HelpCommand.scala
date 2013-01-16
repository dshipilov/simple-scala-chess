package chess.commands

import chess.{Color, NoOp, Board}

object HelpCommand extends Command with Parser {
  def apply(board : Board) = {
    println("Here's some help: <text goes here>")

    (board, NoOp)
  }

  def parse(expr : String, color: Color) = {
    if (matches(expr)) {
      Option(HelpCommand)
    } else None
  }

  def matches(expr: String) = expr.matches("""(?i)^help$""")
}
