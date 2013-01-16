package chess

case class Move(piece: Piece, dst: Position, src: Option[Position]) {
  override def toString = src match {
    case Some(pos) => String.format("%s, %s -> %s", piece.toString, pos.toString, dst.toString)
    case _ => String.format("%s, to %s", piece.toString, dst.toString)
  }
}
