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
      case DecimalLiteral dl -> dl.value();
      case BinaryExpression be -> switch (be.operator()) {
        case ADD -> evaluate(be.left()) + evaluate(be.right());
        case SUB -> evaluate(be.left()) - evaluate(be.right());
        case DIV -> evaluate(be.left()) / evaluate(be.right());
        case MUL -> evaluate(be.left()) * evaluate(be.right());
      };
    };
  }
}
