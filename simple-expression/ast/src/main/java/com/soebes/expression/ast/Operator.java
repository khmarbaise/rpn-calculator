package com.soebes.expression.ast;

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
