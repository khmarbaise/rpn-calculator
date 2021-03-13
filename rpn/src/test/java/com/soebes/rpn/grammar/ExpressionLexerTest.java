package com.soebes.rpn.grammar;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExpressionLexerTest {

  static Stream<Arguments> tokens() {
    return Stream.of(
        arguments("Real", "3", List.of("REAL")),
        arguments("Real", "3.0", List.of("REAL")),
        arguments("Real", "3.", List.of("REAL")),
        arguments("Real", ".0", List.of("REAL")),
        arguments("Real", ".1", List.of("REAL")),
        arguments("Real", ".1e1", List.of("REAL")),
        arguments("Real", ".1e-1", List.of("REAL")),
        arguments("Real", ".1e-05", List.of("REAL")),
        arguments("Real", "3.14159265359", List.of("REAL")),
        arguments("Real", "3.14E-10", List.of("REAL")),
        arguments("Real", "3.E-10", List.of("REAL")),
        arguments("Real Vector", "[3.E-10]", List.of("VECTORLP", "REAL", "VECTORRP")),
        arguments("Real Matrix", "[[3.E-10]]", List.of("VECTORLP", "VECTORLP", "REAL", "VECTORRP", "VECTORRP")),
        arguments("Complex", "(3.e0,2.31e-21)", List.of("COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP")),
        arguments("Complex Vector", "[(3.e0,2.31e-21)]", List.of("VECTORLP", "COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP", "VECTORRP")),
        arguments("Complex Matrix", "[[(3.e0,2.31e-21)]]", List.of("VECTORLP", "VECTORLP", "COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP", "VECTORRP", "VECTORRP"))
    );
  }

  @ParameterizedTest(name = "{0}: expression: ''{1}'' expectedTokens: ''{2}''")
  @MethodSource("tokens")
  void name(String description, String expression, List<String> expectedTokens) {
    var input = CharStreams.fromString(expression);
    var tokenStream = new CommonTokenStream(new ExprLexer(input));

    var tokenList = new ArrayList<String>();

    TokenSource tokenSource = tokenStream.getTokenSource();
    Token token = tokenSource.nextToken();
    while (token.getType() != Token.EOF) {
      String ruleName = ExprLexer.ruleNames[token.getType() - 1];
      tokenList.add(ruleName);
      token = tokenSource.nextToken();
    }

    assertThat(tokenList).as("Expected: %s but got: %s", expectedTokens, tokenList)
        .containsExactly(expectedTokens.toArray(new String[]{}));
  }

}
