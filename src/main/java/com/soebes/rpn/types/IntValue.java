package com.soebes.rpn.types;

import com.soebes.rpn.Element;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
public class IntValue implements Element {
  private final Integer value;

  public IntValue(Integer value) {
    this.value = value;
  }

  public Types type() {
    return Types.Int;
  }
}
