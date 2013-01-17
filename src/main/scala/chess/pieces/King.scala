package chess.pieces

case object King extends PieceType {
  val mnemonic: Char = 'K'
  override val maxMoveDistance = Option(1)
}
