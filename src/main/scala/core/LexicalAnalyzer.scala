package main.scala.core


trait LexicalAnalyzer {
  def get(): LexicalUnit
  def expect(lexicalType: LexicalType): Boolean
  def unget(token: LexicalUnit): Unit
}

object LexicalAnalyzerImpl extends LexicalAnalyzer {
  def get(): LexicalUnit = ???
  def expect(lexicaltype: LexicalType): Boolean = ???
  def unget(token: LexicalUnit): Unit = ???
}
