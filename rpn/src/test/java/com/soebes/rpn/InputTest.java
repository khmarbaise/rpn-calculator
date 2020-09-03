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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class InputTest {

  @Nested
  @DisplayName("Real Matrix")
  class RealMatrix {

    private static final String X = """
        [[(a,b),(a,b)]
         [(a,b),(a,b)]
         [(a,b),(a,b)]]""";

    @Test
    void name() {
      System.out.println("--------------------------------------");
      System.out.println(X);
      System.out.println("--------------------------------------");
    }
  }

  @Nested
  @DisplayName("RealVector")
  class RealVector {
    private static final String X = "[1 2 3 4]";

    @Test
    void name() {
      System.out.println("--------------------------------------");
      System.out.println(X);
      System.out.println("--------------------------------------");
    }

  }

}
