package main.scala.core

import scala.collection.mutable

class Environment(val lexicalAnalyzer: LexicalAnalyzer, val varTable: mutable.HashTable[?]) {
  def this(lexicalAnalyzer: LexicalAnalyzer) = this(lexicalAnalyzer, new mutable.HashTable[?])
  def getLexicalAnalyzer() = lexicalAnalyzer
}
