package main.scala.core

import java.io.{File, FileReader, PushbackReader}

trait LexicalAnalyzer {
  def get(): LexicalUnit
  def expect(lexicalType: LexicalType): Boolean
  def unget(token: LexicalUnit): Unit
}

class LexicalAnalyzerImpl(val reader: PushbackReader) extends LexicalAnalyzer {
  def this(sorcePath: String) {
    this(new PushbackReader(new FileReader(new File(sorcePath))))
  }

  def get(): LexicalUnit = ???
  def expect(lexicaltype: LexicalType): Boolean = ???
  def unget(token: LexicalUnit): Unit = ???
}
