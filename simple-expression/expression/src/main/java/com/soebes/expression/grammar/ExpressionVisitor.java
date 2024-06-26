package com.soebes.expression.grammar;

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
import org.antlr.v4.runtime.Token;

import static com.soebes.expression.ast.Operator.ADD;
import static com.soebes.expression.ast.Operator.DIV;
import static com.soebes.expression.ast.Operator.MUL;
import static com.soebes.expression.ast.Operator.SUB;

public class ExpressionVisitor extends SimpleExpressionBaseVisitor<Expression> {

  public ExpressionVisitor() {
    // intentionally empty.
  }

  @Override
  public Expression visitParExpression(SimpleExpressionParser.ParExpressionContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Expression visitAddExpression(SimpleExpressionParser.AddExpressionContext ctx) {
    return switch (ctx.operator.getText()) {
      case "+" -> new BinaryExpression(ADD, visit(ctx.left), visit(ctx.right));
      case "-" -> new BinaryExpression(SUB, visit(ctx.left), visit(ctx.right));
      default -> throw illegalOperatorException(ctx.operator.getText(), ctx.operator.getStart());
    };
  }


  @Override
  public Expression visitMulExpression(SimpleExpressionParser.MulExpressionContext ctx) {
    return switch (ctx.operator.getText()) {
      case "*" -> new BinaryExpression(MUL, visit(ctx.left), visit(ctx.right));
      case "/" -> new BinaryExpression(DIV, visit(ctx.left), visit(ctx.right));
      default -> throw illegalOperatorException(ctx.operator.getText(), ctx.operator.getStart());
    };
  }

  @Override
  public Expression visitGrpReal(SimpleExpressionParser.GrpRealContext ctx) {
    return new DecimalLiteral(Double.parseDouble(ctx.getText()));
  }

  private static IllegalStateException illegalOperatorException(String operator, Token ctx) {
    return new IllegalStateException("Unknown operator %s line:%d column:%d".formatted(operator, ctx.getLine(), ctx.getCharPositionInLine()));
  }

}
