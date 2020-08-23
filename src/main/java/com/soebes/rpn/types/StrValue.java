package com.soebes.rpn.types;

import com.soebes.rpn.Element;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
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
