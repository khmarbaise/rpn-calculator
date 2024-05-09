package com.soebes.expression.ast;

public record BinaryExpression(Operator operator, Expression left, Expression right) implements Expression {

}
