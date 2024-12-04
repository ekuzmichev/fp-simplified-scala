package ru.ekuzmichev.practice.coin_flip_game

import ru.ekuzmichev.practice.coin_flip_game.CoinFlipGameUtils._

import scala.annotation.tailrec
import scala.util.Random

object CoinFlipGame extends App {
  private val initialGameState = GameState(0, 0)
  private val random           = new Random

  mainLoop(initialGameState, random)

  @tailrec
  private def mainLoop(gameState: GameState, random: Random): Unit = {
    showPrompt()

    val userInput: String = getUserInput()

    userInput match {
      case "H" | "T" =>
        val coinTossResult: String = flipCoin(random)

        val newNumFlips = gameState.numFlips + 1

        val newNumCorrectGuesses =
          if (userInput == coinTossResult)
            gameState.numCorrectGuesses + 1
          else
            gameState.numCorrectGuesses

        val newGameState =
          gameState.copy(
            numFlips = newNumFlips,
            numCorrectGuesses = newNumCorrectGuesses
          )

        printGameState(printFlipResult(coinTossResult), newGameState)

        mainLoop(newGameState, random)
      case "N" =>
        gameOver(gameState)
        printNewGameStarted()
        mainLoop(initialGameState, random)
      case _ =>
        gameOver(gameState)
    }
  }

  private def gameOver(gameState: GameState): Unit = {
    printGameOver()
    printGameState(gameState)
  }
}
