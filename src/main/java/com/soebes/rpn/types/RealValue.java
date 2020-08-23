package com.soebes.rpn.types;

import com.soebes.rpn.Element;

public class RealValue implements Element {
  private final double value;

  public RealValue(double value) {
    this.value = value;
  }

  public Types type() {
    return Types.Real;
  }
}
