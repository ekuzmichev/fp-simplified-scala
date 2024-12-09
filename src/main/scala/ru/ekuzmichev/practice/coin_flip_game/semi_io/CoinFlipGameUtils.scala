package ru.ekuzmichev.practice.coin_flip_game.semi_io

import scala.io.StdIn.readLine
import scala.util.Random

//noinspection AccessorLikeMethodIsEmptyParen
object CoinFlipGameUtils {

  def showPrompt(): Unit = print("\n(h)eads, (t)ails, (n)ew game, or (q)uit: ")

  def getUserInput(): String = readLine.trim.toUpperCase

  def stringifyFlipResult(flip: String): String = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameRound(printableResult: String, gameRound: GameRound): Unit = {
    print(s"Flip was $printableResult. ")
    printGameRound(gameRound)
  }

  def printGameRound(gameRound: GameRound): Unit =
    println(stringifyGameRound(gameRound))

  def printGameState(gameState: GameState): Unit =
    println(
      s"\n#Round: ${stringifyGameRound(gameState.gameRound)}\n" +
        s"#History:\n${stringifyHistory(gameState.history)}"
    )

  private def stringifyGameRound(game: GameRound): String =
    s"#Flips: ${game.numFlips}, #Correct: ${game.numCorrectGuesses}"

  private def stringifyHistory(history: Seq[GameRound]): String = {
    if (history.isEmpty) "<<Empty>>"
    else
      s"${history.zipWithIndex.map { case (gameRound, idx) => s"${idx + 1}: ${stringifyGameRound(gameRound)}" }.mkString("\n")}"
  }

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def printNewGameRoundStarted(): Unit = println("\n=== NEW GAME ROUND STARTED ===")

  // returns "H" for heads, "T" for tails
  def flipCoin(r: Random): String =
    r.nextInt(2) match {
      case 0 => "H"
      case 1 => "T"
    }
}
