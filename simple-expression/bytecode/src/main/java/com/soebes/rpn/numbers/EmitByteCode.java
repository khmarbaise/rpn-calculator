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

import com.soebes.expression.ast.BinaryExpression;
import com.soebes.expression.ast.DecimalLiteral;
import com.soebes.expression.ast.Expression;

public class EmitByteCode {
  private EmitByteCode() {
  }

  public static Double evaluate(Expression expression) {
    return switch (expression) {
      case DecimalLiteral(var value) -> value;
      case BinaryExpression(var operator, var left, var right) -> switch (operator) {
        case ADD -> evaluate(left) + evaluate(right);
        case SUB -> evaluate(left) - evaluate(right);
        case DIV -> evaluate(left) / evaluate(right);
        case MUL -> evaluate(left) * evaluate(right);
      };
    };
  }
}
