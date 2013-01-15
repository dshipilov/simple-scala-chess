package chess

import commands.Commands

object Repl extends App {
  def loop(board : Board, color : Color) : Unit = {
    def comp(color: Color) = color match {
      case White => Black
      case Black => White
    }

    printf("%s> ", color)
    Console.flush()

    Commands(readLine()) match {
      case Some(cmd) => {
        val (updatedBoard, result) = cmd(board)

        result match {
          case InvalidMove(_) => {
            println(result)
            loop(board, color)
          }

          case Moved(_) | Captured(_, _) | Check(_, _) => {
            println(result)
            loop(updatedBoard, comp(color))
          }

          case CheckMate(_, _) => {
            println(result)
          }

          case Finished => Unit

          case _ => loop(board, color)
        }
      }

      case None => loop(board, color)
    }
  }

  val board = Board.initial

  println("\nSimple Scala Chess v0.1")
  println("New Game Session Started\n")
  println("Type \"quit\" to exit program, \"help\" for some help\n")

  Board.print(board)

  loop(board, White)
}
