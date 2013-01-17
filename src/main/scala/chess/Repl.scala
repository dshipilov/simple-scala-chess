package chess

import commands.Commands
import annotation.tailrec

object Repl extends App {
  @tailrec def loop(board : Board, color : Color) : Unit = {
    def comp(color: Color) = color match {
      case White => Black
      case Black => White
    }

    printf("%s> ", color)
    Console.flush()

    Commands(readLine(), color) match {
      case Some(cmd) => {
        val (updatedBoard, result) = cmd(board)

        result match {
          case InvalidMove(_) | AmbiguousMove(_) => {
            println(result)

            loop(board, color)
          }

          case Moved(_) | Captured(_,_) | Check(_,_) => {
            println(result)

            updatedBoard.print()
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


  Board.reset()

  println("\nSimple Scala Chess")
  println("New Game Session Started\n")
  println("Type \"quit\" to exit program, \"help\" for some help\n")

  Board.print()

  loop(Board, White)
}
