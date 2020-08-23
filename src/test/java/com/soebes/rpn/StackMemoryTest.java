package com.soebes.rpn;

import com.soebes.rpn.types.IntValue;
import com.soebes.rpn.types.StrValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackMemoryTest {

  @Test
  void push_one_element_and_size_should_be_one() {
    StackMemory stackMemory = new StackMemory();
    stackMemory.push(new IntValue(Integer.valueOf(200)));

    assertThat(stackMemory.size()).isEqualTo(1);
  }

  @Test
  void push_and_pop_once_size_should_be_zero() {
    StackMemory stackMemory = new StackMemory();
    stackMemory.push(new IntValue(Integer.valueOf(200)));

    Object pop = stackMemory.pop();
    assertThat(stackMemory.size()).isZero();
  }

  @Test
  void push_and_pop_text_size_should_be_zero() {
    StackMemory stackMemory = new StackMemory();
    stackMemory.push(new StrValue("This is Text"));

    Object pop = new StackMemory().pop();
    assertThat(new StackMemory().size()).isZero();
  }

}
