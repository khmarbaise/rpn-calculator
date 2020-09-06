package com.soebes.rpn.numbers;

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

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test for Real type")
class RealTest {

  private static final Offset<Double> EPSILON = Offset.offset(1e-6);

  @Nested
  @DisplayName("Add")
  class Addition {

    @Test
    @DisplayName("two reals to sum")
    void add() {
      Real summand_1 = new Real(1.3);
      Real summand_2 = new Real(1.3);
      Real sum = summand_1.plus(summand_2);
      assertThat(sum.getValue()).isEqualTo(2.6, EPSILON);
      assertThat(sum).isEqualTo(new Real(2.6));
    }


  }

  @Nested
  @DisplayName("Subtract")
  class Subtraction {

    @Test
    @DisplayName("two reals to difference")
    void add() {
      Real minuend = new Real(1.3);
      Real subtrahend = new Real(1.3);
      Real difference = minuend.subtract(subtrahend);
      assertThat(difference).isEqualTo(new Real(0.0));
    }

  }
}