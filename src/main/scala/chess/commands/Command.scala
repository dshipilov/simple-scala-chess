package chess.commands

import chess.{Board, Result}

trait Command extends Function[Board, (Board, Result)]

trait Parser {
  def matches(expr: String) : Boolean
  def parse(expr: String) : Option[Command]
}

object Commands extends Function[String, Option[Command]] {
  val parsers = List[Parser](
    MoveCommand,
    HelpCommand,
    QuitCommand
  )

  def apply(expr: String) =
    parsers.find((p) => p.matches(expr)) match {
      case Some(parser) => parser.parse(expr)
      case _ => None
    }
}