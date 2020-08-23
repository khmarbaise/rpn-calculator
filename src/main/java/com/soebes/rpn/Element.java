package com.soebes.rpn;

import com.soebes.rpn.types.Types;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
public interface Element {

  Types type();
}
