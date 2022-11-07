package com.soebes.expression.ast;

public record BinaryExpression(Operator operator, Expression left, Expression right) implements Expression {
  public enum Operator {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");
    private final String value;

    Operator(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

}
