package com.soebes.rpn.types;

import com.soebes.rpn.Element;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
public class RealValue implements Element {
  private final double value;

  public RealValue(double value) {
    this.value = value;
  }

  public Types type() {
    return Types.Real;
  }
}
