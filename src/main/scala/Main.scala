import main.scala.core.LexicalAnalyzerImpl

import scala.io.Source

object Main extends App {
  try {
    val SORTH_PATH = "test1.bas"
    val source = Source.fromFile(SORTH_PATH)
    LexicalAnalyzerImpl(source)
    source.foreach(c => print(c))
  } catch {
    case e: Exception => println("io error occured.")
  }
}
