trait LexicalAnalyzer(val source: String) = {
  def get(): LexicalUnit
  def expect(val type LexicalType): Boolean
  def unget(val token LexicalUnit): Unit
}

object LexicalAnalyzerImpl(val source: String) = {
  def get(): LexicalUnit = ???
  def expect(val type LexicalType): Boolean = ???
  def unget(val token LexicalUnit): Unit = ???
}