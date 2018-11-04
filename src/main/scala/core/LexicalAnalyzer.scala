package main.scala.core

import java.io.{File, FileReader, PushbackReader}

import scala.collection.immutable
import util.control.Breaks._

trait LexicalAnalyzer {
  def get(): LexicalUnit
  def expect(lexicalType: LexicalType): Boolean
  def unget(token: LexicalUnit): Unit
}

class LexicalAnalyzerImpl(val reader: PushbackReader) extends LexicalAnalyzer {
  def this(sourcePath: String) {
    this(new PushbackReader(new FileReader(new File(sourcePath))))
  }

  def get(): LexicalUnit = {
    val hoge = for (i <- 1 to 100) yield {
      val ci = reader.read()

      if(ci < 0) new LexicalUnit(EOF, None)
      else {
        val c = ci.toChar
        c match {
          case u if(c == ' ' || c == '\t') =>
          case a if((c >= 'a' && c<= 'z') || (c >= 'A' && c <= 'Z')) =>
            reader.unread(ci)
            return getString()
          case n if(c >= '0' && c <= '9') =>
            reader.unread(ci)
            return getNumber()
          case l if(c == '"') => return getLiteral()
          case s if(Symbol.map.get(c.toString).nonEmpty) => return getSymbol(s)
        }
      }
    }
  }

  private def getString(): LexicalUnit = {
    var target = ""

    for(i <- 1 to 100) {
      val ci = reader.read()
      val c = ci.toChar

      c match {
        case a if((c >= 'a' && c<= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0'  && c <= '9')) => target += c
        case _ =>
          reader.unread(ci)
          break()
      }
    }
    ReservedWord.map.get(target).fold(new LexicalUnit(NAME, Some(new ValueImpl(target))))( word =>
      new LexicalUnit(word, None)
    )
  }

  private def getNumber(): LexicalUnit = {
    var target = ""
    var decimalFlag = false

    for(i <- 1 to 100) {
      val ci = reader.read()
      val c = ci.toChar

      c match {
        case n if(c >= '0' && c <= '9') => target += c
        case '.' if(!decimalFlag) =>
          decimalFlag = true
          target += c
        case '.' if(decimalFlag) => throw new Exception("syntax error")
        case _ => break()
      }
    }
    if(decimalFlag) new LexicalUnit(DOUBLEVAL, Some(new ValueImpl(target.toDouble)))
    else new LexicalUnit(INTVAL, Some(new ValueImpl((target.toInt))))
  }

  private def getLiteral(): LexicalUnit = {
    var target = ""

    for(i <- 1 to 100) yield {
      val ci = reader.read
      val c = ci.toChar

      c match {
        case '"' => break()
        case _ => target += c
      }
    }
    new LexicalUnit(LITERAL, Some(new ValueImpl(target)))
  }

  private def getSymbol(c: Char): LexicalUnit = {
    var target = c.toString
    val ci = reader.read()
    val next = ci.toChar

    c match {
      case '<' =>
        if(next == '=' || next == '>') target += next
        else reader.unread(ci)
        new LexicalUnit(Symbol.map.get(target))
      case '>' =>
        if(next == '=') target += next
        else reader.unread(ci)
        new LexicalUnit(Symbol.map.get(target))
      case '=' =>
        if (next == '<' || next == '>') target += next
        else reader.unread(ci)
        new LexicalUnit(Symbol.map.get(target))
      case _ =>
        reader.unread(ci)
        new LexicalUnit(Symbol.map.get(target))
    }
  }

  private object ReservedWord {
    val map = Map(
      "IF" -> IF,
      "THEN" -> THEN,
      "ELSE" -> ELSE,
      "ELSEIF" -> ELSEIF,
      "FOR" -> FOR,
      "FORALL" -> FORALL,
      "NEXT" -> NEXT,
      "SUB" -> FUNC,
      "DIM" -> DIM,
      "AS" -> AS,
      "END" -> END,
      "WHILE" -> WHILE,
      "DO" -> DO,
      "UNTIL" -> UNTIL,
      "LOOP" -> LOOP,
      "TO" -> TO,
      "WEND" -> WEND
    )
  }

  private object Symbol {
    val map = Map(
      "=" -> EQ,
      "<" -> LT,
      ">" -> GT,
      "<=" -> LE,
      "=<" -> LE,
      ">=" -> GE,
      "=>" -> GE,
      "<>" -> NE,
      "." -> DOT,
      "+" -> ADD,
      "-" -> SUB,
      "*" -> MUL,
      "/" -> DIV,
      ")" -> LP,
      "(" -> RP,
      "," -> COMMA,
      "\n" -> NL
    )
  }


  def expect(lexicaltype: LexicalType): Boolean = ???
  def unget(token: LexicalUnit): Unit = ???
}
