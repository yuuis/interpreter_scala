class LexicalUnit = {
  val type: LexicalType
  val value: Value
  val link: LexicalUnit

  def LexicalUnit(thisType: LexicalType) = type = thisType

  def LexicalUnit(thisType: LexicalType, thisValue: Value) = {
    type = thisType
    value = thisValue
  }
  def toString(): String = {
    type match {
      case LITERAL => "LITERAL:\t" + value.getString;
	    case NAME => "NAME:\t" + value.getString;
	    case DOUBLEVAL => "DOUBLEVAL:\t" + value.getString;
	    case INTVAL => "INTVAL:\t" + value.getString;
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
	    case WEND => "WEND"
	    case ELSEIF => "ELSEIF"
	    case NE => "NE"
	    case ENDIF => "ENDIF"
    }
  }
}