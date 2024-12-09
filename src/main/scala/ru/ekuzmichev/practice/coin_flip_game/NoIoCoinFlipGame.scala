package ru.ekuzmichev.practice.coin_flip_game

import ru.ekuzmichev.practice.coin_flip_game.CoinFlipGameUtils._

import scala.annotation.tailrec
import scala.util.Random

object NoIoCoinFlipGame extends App {
  private val initialGameRound = GameRound(0, 0)
  private val initialGameState = GameState(initialGameRound, Seq.empty)
  private val random           = new Random

  mainLoop(initialGameState, random)

  @tailrec
  private def mainLoop(gameState: GameState, random: Random): Unit = {
    val gameRound = gameState.gameRound
    val history   = gameState.history

    showPrompt()

    val userInput: String = getUserInput()

    userInput match {
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

        printGameRound(stringifyFlipResult(coinTossResult), newGameRound)

        val newGameState = gameState.copy(gameRound = newGameRound)

        mainLoop(newGameState, random)
      case "N" =>
        val newGameState = gameState.copy(gameRound = initialGameRound, history = history :+ gameRound)
        printNewGameRoundStarted()
        printGameState(newGameState)
        mainLoop(newGameState, random)
      case _ =>
        printGameOver()
        printGameState(gameState)
    }
  }

}
