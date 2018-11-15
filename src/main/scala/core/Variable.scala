package main.scala.core

class Variable(val name: Option[String], var value: Option[Value]) extends Node {
  def this(name: String) = this(Some(name), None)
  def this(lexicalUnit: LexicalUnit) = this(None, Some(lexicalUnit.getValue.get))

  def isMatch(environment: Environment, lexicalUnit: LexicalUnit): Option[Node] = {
    if(lexicalUnit.getType == NAME) Some(new Variable(lexicalUnit.getValue.get.getString))
    else None
  }

  def setValue(value: Value): Unit = this.value = Some(value)

  def getValue(): Option[Value] = value
}
