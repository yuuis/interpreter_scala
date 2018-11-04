package main.scala.core

abstract class LexicalType

case object LITERAL extends LexicalType
case object INTVAL extends LexicalType
case object DOUBLEVAL extends LexicalType
case object NAME extends LexicalType
case object IF extends LexicalType
case object THEN extends LexicalType
case object ELSE extends LexicalType
case object ELSEIF extends LexicalType
case object ENDIF extends LexicalType
case object FOR extends LexicalType
case object FORALL extends LexicalType
case object NEXT extends LexicalType
case object EQ extends LexicalType
case object LT extends LexicalType
case object GT extends LexicalType
case object LE extends LexicalType
case object GE extends LexicalType
case object NE extends LexicalType
case object FUNC extends LexicalType
case object DIM extends LexicalType
case object AS extends LexicalType
case object END extends LexicalType
case object NL extends LexicalType
case object DOT extends LexicalType
case object WHILE extends LexicalType
case object DO extends LexicalType
case object UNTIL extends LexicalType
case object ADD extends LexicalType
case object SUB extends LexicalType
case object MUL extends LexicalType
case object DIV extends LexicalType
case object LP extends LexicalType
case object RP extends LexicalType
case object COMMA extends LexicalType
case object LOOP extends LexicalType
case object TO extends LexicalType
case object WEND extends LexicalType
case object EOF extends LexicalType
