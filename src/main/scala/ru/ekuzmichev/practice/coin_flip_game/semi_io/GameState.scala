package ru.ekuzmichev.practice.coin_flip_game.semi_io

case class GameState(
    gameRound: GameRound,
    history: Seq[GameRound]
)