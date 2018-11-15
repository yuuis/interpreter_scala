package main.scala.core

class Node(val nodeType: Option[NodeType], val environment: Option[Environment]) {
  def this(nodeType: NodeType) = this(Some(nodeType), None)
  def this(environment: Environment) = this(None, Some(environment))

  def getType() = nodeType
  def Parse(): Boolean = true
  def toString = {
    nodeType match {
      case NodeType.END => "END"
      case _ => "Node"
    }
  }
}
