package com.soebes.rpn.types;

import com.soebes.rpn.Element;

public class StrValue implements Element {
  private final String value;

  public StrValue(String value) {
    this.value = value;
  }

  @Override
  public Types type() {
    return Types.Str;
  }
}
