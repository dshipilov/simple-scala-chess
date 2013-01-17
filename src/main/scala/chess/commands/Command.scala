package chess.commands

import chess.{Color, Board, Result}

trait Command extends Function[Board, (Board, Result)]

trait Parser {
  def matches(expr: String) : Boolean
  def parse(expr: String, color: Color) : Option[Command]
}

object Commands {
  val parsers = List[Parser](
    MoveCommand,
    BoardCommand,
    HelpCommand,
    QuitCommand
  )

  def apply(expr: String, color : Color) : Option[Command] =
    parsers.find((p) => p.matches(expr)) match {
      case Some(parser) => parser.parse(expr, color)
      case _ => None
    }
}