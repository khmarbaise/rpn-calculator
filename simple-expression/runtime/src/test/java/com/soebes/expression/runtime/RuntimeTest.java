package com.soebes.expression.runtime;

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

import com.soebes.expression.ast.BinaryExpression;
import com.soebes.expression.ast.DecimalLiteral;
import com.soebes.expression.ast.Expression;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.soebes.expression.ast.BinaryExpression.Operator.ADD;
import static com.soebes.expression.ast.BinaryExpression.Operator.DIV;
import static com.soebes.expression.ast.BinaryExpression.Operator.MUL;
import static com.soebes.expression.ast.BinaryExpression.Operator.SUB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RuntimeTest {

  private static final DecimalLiteral LITERAL_1 = new DecimalLiteral(1.0d);
  private static final DecimalLiteral LITERAL_2 = new DecimalLiteral(2.0d);
  private static final DecimalLiteral LITERAL_3 = new DecimalLiteral(3.0d);
  private static final DecimalLiteral LITERAL_4 = new DecimalLiteral(4.0d);


  static Stream<Arguments> runtimeExpression() {
    return Stream.of(
        arguments("simple: 1+2", new BinaryExpression(ADD, LITERAL_1, LITERAL_2), 3.0),
        arguments("simple: 1-2", new BinaryExpression(SUB, LITERAL_1, LITERAL_2), -1.0),
        arguments("Change of precedence (1+2)*3",
            new BinaryExpression(MUL,
                  new BinaryExpression(ADD,
                      LITERAL_1,
                      LITERAL_2),
                  LITERAL_3),
            9.0 ),
        arguments("Keep precedence 1+2*3",
          new BinaryExpression(ADD,
              LITERAL_1,
                new BinaryExpression(MUL,
                    LITERAL_2,
                    LITERAL_3)
                ),
              7.0),
        arguments("1+2*(2+4)/(3+2)",
            new BinaryExpression(
                ADD,
                LITERAL_1,
                new BinaryExpression(
                  DIV,
                  new BinaryExpression(
                      MUL,
                      LITERAL_2,
                      new BinaryExpression(
                          ADD,
                          LITERAL_2,
                          LITERAL_4
                      )
                  ),
                  new BinaryExpression(ADD,
                      LITERAL_3,
                      LITERAL_2
                  )
                )
              ), 3.4)
    );
  }

  @ParameterizedTest(name = "{0}: expression: ''{1}'' expectedResult: ''{2}''")
  @MethodSource
  void runtimeExpression(String description, Expression expression, Double expectedResult) {
    var evaluate = Runtime.evaluate(expression);
    assertThat(evaluate).isEqualTo(expectedResult, Offset.offset(1E-10));
  }

}
