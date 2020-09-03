package com.soebes.rpn;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
