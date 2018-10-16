package main.scala.core

trait Value {
  def getString: String
  def getInt: Int
  def getBoolean: Boolean
  def getType:ValueType
}
