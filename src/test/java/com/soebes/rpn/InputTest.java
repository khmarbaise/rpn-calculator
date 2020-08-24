package com.soebes.rpn;

import org.junit.jupiter.api.Test;

class InputTest {

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
