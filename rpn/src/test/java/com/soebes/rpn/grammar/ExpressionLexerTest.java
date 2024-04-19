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

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExpressionLexerTest {

  static Stream<Arguments> tokens() {
    return Stream.of(
        arguments("Binary", "#1", of("BINARY")),
        arguments("Binary", "#1ABCD", of("BINARY")),
        arguments("Binary", "#F189ABCDF", of("BINARY")),
        arguments("Binary", "#FFFFFFFFFFFFFFFF", of("BINARY")),
        arguments("Real", "3", of("REAL")),
        arguments("Real", "3.0", of("REAL")),
        arguments("Real", "3.", of("REAL")),
        arguments("Real", ".0", of("REAL")),
        arguments("Real", ".1", of("REAL")),
        arguments("Real", ".1e1", of("REAL")),
        arguments("Real", ".1e-1", of("REAL")),
        arguments("Real", ".1e-05", of("REAL")),
        arguments("Real", "-.e1", of("REAL")), //Is that a valid real?
        arguments("Real", "3.14159265359", of("REAL")),
        arguments("Real", "3.14E-10", of("REAL")),
        arguments("Real", "3.E-10", of("REAL")),
        arguments("Real Vector", "[3.E-10]", of("VECTORLP", "REAL", "VECTORRP")),
        arguments("Real Matrix", "[[3.E-10]]", of("VECTORLP", "VECTORLP", "REAL", "VECTORRP", "VECTORRP")),
        arguments("Complex", "(3.e0,2.31e-21)", of("COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP")),
        arguments("Complex Vector", "[(3.e0,2.31e-21)]", of("VECTORLP", "COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP", "VECTORRP")),
        arguments("Complex Matrix", "[[(3.e0,2.31e-21)]]", of("VECTORLP", "VECTORLP", "COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP", "VECTORRP", "VECTORRP")),
        arguments("Add Binary", "#1000+#2000", of("BINARY", "ADD", "BINARY")),
        arguments("Add Complex", "(3.0,2.75)+(1.75,6.5)", of("COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP", "ADD", "COMPLEXLP", "REAL", "SEP", "REAL", "COMPLEXRP")),
        arguments("Add Real Vectors", "[6.E-10]+[6.E-10]", of("VECTORLP", "REAL", "VECTORRP", "ADD", "VECTORLP", "REAL", "VECTORRP"))
    );
  }

  @ParameterizedTest(name = "{0}: expression: ''{1}'' expectedTokens: ''{2}''")
  @MethodSource
  void tokens(String description, String expression, List<String> expectedTokens) {
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
