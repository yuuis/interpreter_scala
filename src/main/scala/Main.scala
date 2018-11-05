import main.scala.core.{EOF, LexicalAnalyzerImpl}

import scala.io.Source
import util.control.Breaks._

object Main extends App {
  try {
    val SOURCE_PATH = "test1.bas"
    val source = Source.fromFile(SOURCE_PATH)
    val lexicalAnalyzer =  new LexicalAnalyzerImpl(SOURCE_PATH)

    breakable( for(i <- 1 to 100) {
      val lexicalUnit = lexicalAnalyzer.get()
      if(lexicalUnit.getType == EOF) break()
      else println(lexicalUnit)
    })
  } catch {
    case e: Exception => println("io error occured.")
  }
}
