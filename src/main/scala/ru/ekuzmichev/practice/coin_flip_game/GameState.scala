package ru.ekuzmichev.practice.coin_flip_game

case class GameState(
    gameRound: GameRound,
    history: Seq[GameRound]
)
