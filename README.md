Simple Scala Chess
==================

Simple interactive console chess game written in Scala.

Task Outline
============

Write simple interactive console chess game for two players.
Program should be ideomatic scala language code, thus:
* enagage functional programming style
* use case classes (aka ADT) to model the problem domain
* prefer patern matching for expressing program control logic
* avoid using imperative constructs
* prefer HOFs with lambda functions to explicit iteration and looping
* prefer scala library entities (especially collections) to the
java SDK-provided classes

Sample Game Session
===================

Interaction is done in a REPL (read/evaluate/print loop) style.

Here is sample session outline:

    -- New game started --

        a   b   c   d   e   f   g   h
      +---+---+---+---+---+---+---+---+
    8 | r | n | b | q | k | b | n | r | 8
      +---+---+---+---+---+---+---+---+
    7 | p | p | p | p | p | p | p | p | 7
      +---+---+---+---+---+---+---+---+
    6 |   |   |   |   |   |   |   |   | 6
      +---+---+---+---+---+---+---+---+
    5 |   |   |   |   |   |   |   |   | 5
      +---+---+---+---+---+---+---+---+
    4 |   |   |   |   |   |   |   |   | 4
      +---+---+---+---+---+---+---+---+
    3 |   |   |   |   |   |   |   |   | 3
      +---+---+---+---+---+---+---+---+
    2 | P | P | P | P | P | P | P | P | 2
      +---+---+---+---+---+---+---+---+
    1 | R | N | B | Q | K | B | N | R | 1
      +---+---+---+---+---+---+---+---+
        a   b   c   d   e   f   g   h

    white> e4

        a   b   c   d   e   f   g   h
      +---+---+---+---+---+---+---+---+
    8 | r | n | b | q | k | b | n | r | 8
      +---+---+---+---+---+---+---+---+
    7 | p | p | p | p | p | p | p | p | 7
      +---+---+---+---+---+---+---+---+
    6 |   |   |   |   |   |   |   |   | 6
      +---+---+---+---+---+---+---+---+
    5 |   |   |   |   |   |   |   |   | 5
      +---+---+---+---+---+---+---+---+
    4 |   |   |   |   | P |   |   |   | 4
      +---+---+---+---+---+---+---+---+
    3 |   |   |   |   |   |   |   |   | 3
      +---+---+---+---+---+---+---+---+
    2 | P | P | P | P |   | P | P | P | 2
      +---+---+---+---+---+---+---+---+
    1 | R | N | B | Q | K | B | N | R | 1
      +---+---+---+---+---+---+---+---+
        a   b   c   d   e   f   g   h

    black> e5

        a   b   c   d   e   f   g   h
      +---+---+---+---+---+---+---+---+
    8 | r | n | b | q | k | b | n | r | 8
      +---+---+---+---+---+---+---+---+
    7 | p | p | p | p |   | p | p | p | 7
      +---+---+---+---+---+---+---+---+
    6 |   |   |   |   |   |   |   |   | 6
      +---+---+---+---+---+---+---+---+
    5 |   |   |   |   | p |   |   |   | 5
      +---+---+---+---+---+---+---+---+
    4 |   |   |   |   | P |   |   |   | 4
      +---+---+---+---+---+---+---+---+
    3 |   |   |   |   |   |   |   |   | 3
      +---+---+---+---+---+---+---+---+
    2 | P | P | P | P |   | P | P | P | 2
      +---+---+---+---+---+---+---+---+
    1 | R | N | B | Q | K | B | N | R | 1
      +---+---+---+---+---+---+---+---+
        a   b   c   d   e   f   g   h

* Game session starts with initial chess position displayed
* Board is displayed as ASCII-styled table
* Ranks numbered from bottom to top
* Pieces displayed in standard FIDE alphabetic abbreviations (see below)
* Black position is on top of the board; whites are at the bottom
* Program shows prompt with indication of expected piece color move
* Players enter moves in algebraic notation (see details below)
* Program should check piece move correctness and allow to retry move in case of error
* Program should detect piece captures and display it in separate message (en passant captures detection is optional)
* Program at minimum should detect check and checkmate game conditions and autimatically end the game

Piece Abbreviations
===================

* P or p - pawn
* R or r - rook
* N or n - knight
* B or b - bishop
* Q or q - queen
* K or k - king

Capital letters denote white pieces, lowcase letters are black pieces.

SAN
===

Stands for Chess [Standard Algebraic Notation](http://en.wikipedia.org/wiki/Algebraic_notation_(chess)). Used to describe piece moves.

Each move is prefixed with a letter (could be omited for pawns) which identify piece to move (see abbreviations section above). Target position is defined next in a standard file/rank format (e.g. e5). When target position is ambigous (e.g. both knights could be moved there) player should identify source position (just rank or just file or both of them) _before_ the target position.

Move command samples:

    white> e4
    black> e5
    white> Kf3
    black> Bc5

* white move king's pawn into postion e4
* black respond with king's pawn on e5
* white move king-side knight onto f3
* black move king-side bishop onto c5

Captures and check/checkmate should be automatically detected by the program and shouldn't be specified with the move.

Castling moves and pawn promotions support is optional in the program implementation.

Implementation Details
======================

Program should be build with sbt tool.

Subtasks include:
* Console REPL implementation
* Pieces and other chess entities definitions (as case classes); each piece type should encapsulate move correctness check behaviour
* Position abstraction implementation (with support of position difference calculation)
* Chessboard implementation (internal representation and ASCII-style print)
* Error and program state reporting support: ambigous/incorrect moves, check, checkmate, piece captures
* Check and checkmate detection algorithm
* [optional] En passant captures detection
* [optional] Store/load/display games in the [FEN](http://en.wikipedia.org/wiki/FEN) notation
* [optional] Castling moves.
