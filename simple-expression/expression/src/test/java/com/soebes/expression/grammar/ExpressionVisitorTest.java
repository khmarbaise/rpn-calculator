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
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.soebes.expression.ast.Operator.ADD;
import static com.soebes.expression.ast.Operator.DIV;
import static com.soebes.expression.ast.Operator.MUL;
import static com.soebes.expression.ast.Operator.SUB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExpressionVisitorTest {

  private static final DecimalLiteral LITERAL_1 = new DecimalLiteral(1.0d);
  private static final DecimalLiteral LITERAL_2 = new DecimalLiteral(2.0d);
  private static final DecimalLiteral LITERAL_3 = new DecimalLiteral(3.0d);
  private static final DecimalLiteral LITERAL_4 = new DecimalLiteral(4.0d);
  private static final DecimalLiteral LITERAL_5 = new DecimalLiteral(5.0d);

  private static final Expression MULT = new BinaryExpression(MUL, LITERAL_5, LITERAL_3);
  private static final Expression LEFT = new BinaryExpression(ADD, LITERAL_2, MULT);
  private static final Expression EXPECTED_RESULT = new BinaryExpression(SUB, LEFT, LITERAL_5);

  static Stream<Arguments> parseExpressions() {
    return Stream.of(
        arguments("Addition", "3+5", new BinaryExpression(ADD, LITERAL_3, LITERAL_5)),
        arguments("Subtraction", "5-3", new BinaryExpression(SUB, LITERAL_5, LITERAL_3)),
        arguments("Division", "5/3", new BinaryExpression(DIV, LITERAL_5, LITERAL_3)),
        arguments("Multiplication", "5*3", new BinaryExpression(MUL, LITERAL_5, LITERAL_3)),
        arguments("Combination", "2+5*3-5", EXPECTED_RESULT),
        arguments("Combination", "(1+2)*3", new BinaryExpression(MUL, new BinaryExpression(ADD, LITERAL_1, LITERAL_2), LITERAL_3)),
        arguments("Combination", "1+2*3",   new BinaryExpression(ADD, LITERAL_1, new BinaryExpression(MUL, LITERAL_2,LITERAL_3))),
        arguments("Combination", "1+2*(2+4)/(3+2)", new BinaryExpression(ADD,
            LITERAL_1, new BinaryExpression(DIV,
                new BinaryExpression(MUL, LITERAL_2,
                  new BinaryExpression(ADD, LITERAL_2, LITERAL_4)), new BinaryExpression(ADD, LITERAL_3, LITERAL_2))))
    );
  }


  @ParameterizedTest(name = "{0}: expression: ''{1}'' expectedResult: ''{2}''")
  @MethodSource
  void parseExpressions(String description, String expression, Expression expectedResult) {
    var input = CharStreams.fromString(expression);
    var exprLexer = new SimpleExpressionLexer(input);
    var parser = new SimpleExpressionParser(new CommonTokenStream(exprLexer));
    var tree = parser.expr();
    var visitor = new ExpressionVisitor();
    var result = visitor.visit(tree);

    assertThat(result).isEqualTo(expectedResult);
  }

//  @Test
//  void expectException() {
//    var input = CharStreams.fromString("5%9");
//    var exprLexer = new SimpleExpressionLexer(input);
//    var parser = new SimpleExpressionParser(new CommonTokenStream(exprLexer));
//    var tree = parser.expr();
//    var visitor = new ExpressionVisitor();
//    assertThatIllegalStateException().isThrownBy(() -> visitor.visit(tree)).withMessage("Unknown operator '%' line:1 column:1");
//  }
}
