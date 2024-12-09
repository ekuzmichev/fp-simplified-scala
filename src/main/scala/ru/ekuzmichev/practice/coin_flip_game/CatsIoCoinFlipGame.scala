package ru.ekuzmichev.practice.coin_flip_game

import cats.effect.{IO, IOApp}
import ru.ekuzmichev.practice.coin_flip_game.CoinFlipGameUtils._

import scala.util.Random

object CatsIoCoinFlipGame extends IOApp.Simple {
  private val initialGameRound = GameRound(0, 0)
  private val initialGameState = GameState(initialGameRound, Seq.empty)
  private val random           = new Random

  override def run: IO[Unit] = mainLoop(initialGameState, random)

  private def mainLoop(gameState: GameState, random: Random): IO[Unit] = {
    val gameRound = gameState.gameRound
    val history   = gameState.history

    for {
      _         <- IO { showPrompt() }
      userInput <- IO { getUserInput() }
      _ <- userInput match {
        case "H" | "T" =>
          val coinTossResult: String = flipCoin(random)

          val newNumFlips = gameRound.numFlips + 1

          val newNumCorrectGuesses =
            if (userInput == coinTossResult)
              gameRound.numCorrectGuesses + 1
            else
              gameRound.numCorrectGuesses

          val newGameRound =
            gameRound.copy(
              numFlips = newNumFlips,
              numCorrectGuesses = newNumCorrectGuesses
            )

          val newGameState = gameState.copy(gameRound = newGameRound)

          for {
            _ <- IO { printGameRound(stringifyFlipResult(coinTossResult), newGameRound) }
            _ <- mainLoop(newGameState, random)
          } yield ()

        case "N" =>
          val newGameState = gameState.copy(gameRound = initialGameRound, history = history :+ gameRound)
          for {
            _ <- IO { printNewGameRoundStarted() }
            _ <- IO { printGameState(newGameState) }
            _ <- IO { mainLoop(newGameState, random) }
          } yield ()
        case _ =>
          for {
            _ <- IO { printGameOver() }
            _ <- IO { printGameState(gameState) }
          } yield ()
      }
    } yield ()

  }
}
