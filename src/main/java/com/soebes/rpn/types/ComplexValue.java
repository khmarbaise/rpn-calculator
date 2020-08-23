package com.soebes.rpn.types;

import com.soebes.rpn.Element;

public class ComplexValue implements Element {
  private final Complex value;

  public ComplexValue(Complex value) {
    this.value = value;
  }

  public Types type() {
    return Types.Complex;
  }
}
