package main.scala.core

trait Value {
  def getString: String
  def getInt: Int
  def getBoolean: Boolean
  def getType:ValueType
}

class ValueImpl(value: String, valueType: ValueType) extends Value {

  def this(s: String) = this(s, STRING)
  def this(i: Int) = this(i.toString, INTEGER)
  def this(d: Double) = this(d.toString, DOUBLE)
  def this(b: Boolean) = this(b.toString, BOOL)


  def getString: String = value
  def getInt: Int = value.toInt
  def getDouble: Double = value.toDouble
  def getBoolean: Boolean = value.toBoolean

  def getType: ValueType = valueType
}
