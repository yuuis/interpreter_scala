import main.scala.core.{EOF, LexicalAnalyzerImpl}

import scala.io.Source

object Main extends App {
  try {
    val SORTH_PATH = "test1.bas"
    val source = Source.fromFile(SORTH_PATH)
    val lexicalAnalyzer =  new LexicalAnalyzerImpl(SORTH_PATH)

    source.map { c =>
      var lexicalUnit = lexicalAnalyzer.get()
      if(lexicalUnit.getType == EOF) return
      println(lexicalUnit)
    }
    source.foreach(c => print(c))
  } catch {
    case e: Exception => println("io error occured.")
  }
}
