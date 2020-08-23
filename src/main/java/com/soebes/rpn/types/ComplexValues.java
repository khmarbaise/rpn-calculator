package com.soebes.rpn.types;

import com.soebes.rpn.Element;

public class ComplexValues implements Element {
  private final Complex value;

  public ComplexValues(Complex value) {
    this.value = value;
  }

  public Types type() {
    return Types.Complex;
  }
}
