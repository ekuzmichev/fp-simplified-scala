package ru.ekuzmichev.practice.coin_flip_game

import scala.io.StdIn.readLine
import scala.util.Random

object CoinFlipGameUtils {

  def showPrompt(): Unit = print("\n(h)eads, (t)ails, or (q)uit: ")

  def getUserInput(): String = readLine.trim.toUpperCase

  def printFlipResult(flip: String): String = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameState(printableResult: String, gameState: GameState): Unit = {
    print(s"Flip was $printableResult. ")
    printGameState(gameState)
  }

  def printGameState(gameState: GameState): Unit =
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrectGuesses}")

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  // returns "H" for heads, "T" for tails
  def flipCoin(r: Random): String =
    r.nextInt(2) match {
      case 0 => "H"
      case 1 => "T"
    }
}
