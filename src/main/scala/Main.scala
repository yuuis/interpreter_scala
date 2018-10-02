import scala.io.Source
import scala.util.control.Exception._ 

object Main extends App {
  try {
    val SORTH_PATH = "test1.bas"
    val source = Source.fromFile(SORTH_PATH)
    source.foreach(c => print(c))
  } catch {
    case e: Exception => println("io error occured.")
  }
}
