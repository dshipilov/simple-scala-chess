package chess

case class Move(piece: Piece, dst: Position, src: Option[Position])
