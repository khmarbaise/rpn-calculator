package com.soebes.rpn;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackMemoryTest {

  @Test
  void first() {
    StackMemory stackMemory = new StackMemory();

    stackMemory.push(Integer.valueOf(200));

    assertThat(stackMemory.size()).isEqualTo(1);
  }

  @Test
  void second() {
    StackMemory stackMemory = new StackMemory();

    stackMemory.push(Integer.valueOf(200));

    Object pop = stackMemory.pop();
    assertThat(stackMemory.size()).isZero();
  }

  @Test
  void third() {
    StackMemory stackMemory = new StackMemory();

    stackMemory.push("This is Text");

    Object pop = stackMemory.pop();
    assertThat(stackMemory.size()).isZero();
  }

}
