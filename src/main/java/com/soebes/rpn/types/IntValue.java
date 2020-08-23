package com.soebes.rpn.types;

import com.soebes.rpn.Element;

public class IntValue implements Element {
  private final Integer value;

  public IntValue(Integer value) {
    this.value = value;
  }

  public Types type() {
    return Types.Int;
  }
}
