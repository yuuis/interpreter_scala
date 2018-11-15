package main.scala.core

abstract class NodeType

case object PROGRAM extends NodeType
case object STMT_LIST extends NodeType
case object STMT extends NodeType
case object FOR_STMT extends NodeType
case object SUBST_STMT extends NodeType
case object BLOCK extends NodeType
case object IF_BLOCK extends NodeType
case object LOOP_BLOCK extends NodeType
case object COND extends NodeType
case object EXPR_LIST extends NodeType
case object EXPR extends NodeType
case object FUNCTION_CALL extends NodeType
case object STRING_CONSTANT extends NodeType
case object INT_CONSTANT extends NodeType
case object DOUBLE_CONSTANT extends NodeType
case object BOOL_CONSTANT extends NodeType
case object END extends NodeType
