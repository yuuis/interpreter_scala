package main.scala.core

class LexicalUnit(val lexicalType: LexicalType, val value: Option[Value]) {

  def this(lexicalType: Option[LexicalType]) {
    this(lexicalType.get, None)
  }
  def getType: LexicalType = lexicalType
  def getValue: Option[Value] = value

  override def toString(): String = {
    lexicalType match {
      case LITERAL => "LITERAL:\t" + value.map(_.getString);
      case NAME => "NAME:\t" + value.map(_.getString);
      case DOUBLEVAL => "DOUBLEVAL:\t" + value.map(_.getString);
      case INTVAL => "INTVAL:\t" + value.map(_.getString);
      case IF => "IF"
      case THEN => "THEN"
      case ELSE => "ELSE"
      case FOR => "FOR"
      case FORALL => "FORALL"
      case NEXT => "NEXT"
      case SUB => "SUB"
      case DIM => "DIM"
      case AS => "AS"
      case END => "END"
      case EOF => "EOF"
      case NL => "NL"
      case EQ => "EQ"
      case LT => "LT"
      case GT => "GT"
      case LE => "LE"
      case GE => "GE"
      case DO => "DO"
      case DOT => "DOT"
      case WHILE => "WHILE"
      case UNTIL => "UNTIL"
      case ADD => "ADD"
      case MUL => "MUL"
      case DIV => "DIV"
      case LP => "LP"
      case RP => "RP"
      case COMMA => "COMMA"
      case LOOP => "LOOP"
      case TO => "TO"
      case PRINT => "PRINT"
      case WEND => "WEND"
      case ELSEIF => "ELSEIF"
      case NE => "NE"
      case ENDIF => "ENDIF"
    }
  }
}
