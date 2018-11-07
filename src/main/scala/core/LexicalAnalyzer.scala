package main.scala.core

import java.io.{File, FileReader, PushbackReader}
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
    val ci = reader.read()
    reader.unread(ci)
    if(ci < 0 || ci == 65535) new LexicalUnit(EOF, None)
    else {
      val c = ci.toChar
      c match {
        case u if(c == ' ' || c == '\t') => {
          reader.read()
          get()
        }
        case a if((c >= 'a' && c<= 'z') || (c >= 'A' && c <= 'Z')) => return getString()
        case n if(c >= '0' && c <= '9') => return getNumber()
        case l if(c == '"') => return getLiteral()
        case s if(Symbol.map.get(c.toString).nonEmpty) => return getSymbol()
      }
    }
  }

  private def getString(): LexicalUnit = {
    var target = ""

    breakable( for(i <- 1 to 100) {
      val ci = reader.read()
      val c = ci.toChar

      if(ci < 0) break()

      c match {
        case a if((c >= 'a' && c<= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0'  && c <= '9')) => target += c
        case _ =>
          reader.unread(ci)
          break()
      }
    })
    ReservedWord.map.get(target).fold(new LexicalUnit(NAME, Some(new ValueImpl(target))))( word =>
      word
    )
  }

  private def getNumber(): LexicalUnit = {
    var target = ""
    var decimalFlag = false

    breakable( for(i <- 1 to 100) {
      val ci = reader.read()
      val c = ci.toChar

      if(ci < 0) break()

      c match {
        case n if(c >= '0' && c <= '9') => target += c
        case '.' if(!decimalFlag) =>
          decimalFlag = true
          target += c
        case '.' if(decimalFlag) => throw new Exception("found too many dots")
        case _ => break()
      }
    })
    if(decimalFlag) new LexicalUnit(DOUBLEVAL, Some(new ValueImpl(target.toDouble)))
    else new LexicalUnit(INTVAL, Some(new ValueImpl((target.toInt))))
  }

  private def getLiteral(): LexicalUnit = {
    var target = ""
    reader.read()

    breakable( for(i <- 1 to 100) {
      val ci = reader.read

      if(ci < 0) break()
      val c = ci.toChar

      if(ci < 0) throw new Exception("cant find closing double quote")

      c match {
        case '"' => break()
        case _ => target += c
      }
    })
    new LexicalUnit(LITERAL, Some(new ValueImpl(target)))
  }

  private def getSymbol(): LexicalUnit = {
    var target = ""

    breakable( for(i <- 1 to 100) {
      val ci = reader.read()

      if(ci < 0) return Symbol.map.get(target).get
      val c = ci.toChar

      if(Symbol.map.get(target + c).nonEmpty) target += c
      else {
        reader.unread(ci)
        break()
      }
    })
    return Symbol.map.get(target).get
  }

  private object ReservedWord {
    val map = Map(
      "IF" -> new LexicalUnit(IF, None),
      "THEN" -> new LexicalUnit(THEN, None),
      "ELSE" -> new LexicalUnit(ELSE, None),
      "ELSEIF" -> new LexicalUnit(ELSEIF, None),
      "FOR" -> new LexicalUnit(FOR, None),
      "FORALL" -> new LexicalUnit(FORALL, None),
      "NEXT" -> new LexicalUnit(NEXT, None),
      "SUB" -> new LexicalUnit(FUNC, None),
      "DIM" -> new LexicalUnit(DIM, None),
      "AS" -> new LexicalUnit(AS, None),
      "END" -> new LexicalUnit(END, None),
      "WHILE" -> new LexicalUnit(WHILE, None),
      "DO" -> new LexicalUnit(DO, None),
      "UNTIL" -> new LexicalUnit(UNTIL, None),
      "LOOP" -> new LexicalUnit(LOOP, None),
      "TO" -> new LexicalUnit(TO, None),
      "PRINT" -> new LexicalUnit(PRINT, None),
      "WEND" -> new LexicalUnit(WEND, None)
    )
  }

  private object Symbol {
    val map = Map(
      "=" -> new LexicalUnit(EQ, None),
      "<" -> new LexicalUnit(LT, None),
      ">" -> new LexicalUnit(GT, None),
      "<=" -> new LexicalUnit(LE, None),
      "=<" -> new LexicalUnit(LE, None),
      ">=" -> new LexicalUnit(GE, None),
      "=>" -> new LexicalUnit(GE, None),
      "<>" -> new LexicalUnit(NE, None),
      "." -> new LexicalUnit(DOT, None),
      "+" -> new LexicalUnit(ADD, None),
      "-" -> new LexicalUnit(SUB, None),
      "*" -> new LexicalUnit(MUL, None),
      "/" -> new LexicalUnit(DIV, None),
      ")" -> new LexicalUnit(LP, None),
      "(" -> new LexicalUnit(RP, None),
      "," -> new LexicalUnit(COMMA, None),
      "\n" -> new LexicalUnit(NL, None)
    )
  }


  def expect(lexicaltype: LexicalType): Boolean = ???
  def unget(token: LexicalUnit): Unit = ???
}
