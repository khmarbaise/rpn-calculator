package com.soebes.rpn.grammar;

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

import com.soebes.rpn.types.Complex;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Disabled
class ExpressionVisitorTest {

  static Stream<Arguments> createExpressionToParse() {
    return Stream.of(
        arguments("Real", "3.13", 3.13)
//        arguments("Complex", "(3.13,1.0)", 3.13),
//        arguments("Real Vector", "[1 2 3 4]", 3.13)
//        arguments("Complex Vector", "[(1,0) (2,0) (3,0) (4,0)]", 3.13)
    );
  }

  @ParameterizedTest(name = "{0}: expression: ''{1}'' expectedResult: ''{2}''")
  @MethodSource("createExpressionToParse")
  void name(String description, String expression, Double expectedResult) {
    var input = CharStreams.fromString(expression);
    com.soebes.rpn.grammar.ExprLexer exprLexer = new com.soebes.rpn.grammar.ExprLexer(input);
//    exprLexer.removeErrorListeners();
//    exprLexer.addErrorListener(ThrowingErrorListener.INSTANCE);
    var parser = new com.soebes.rpn.grammar.ExprParser(new CommonTokenStream(exprLexer));
//    parser.removeErrorListeners();
//    parser.addErrorListener(ThrowingErrorListener.INSTANCE);
    var tree = parser.hp28();
    var visitor = new ExpressionVisitor();
    visitor.visit(tree);
    Complex result = visitor.getComplex();

    assertThat(result).as("Expected: %s but got:%s", expectedResult, result).isEqualTo(expectedResult);
  }

}
