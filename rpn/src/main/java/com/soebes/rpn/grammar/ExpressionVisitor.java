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


import com.soebes.rpn.Element;
import com.soebes.rpn.types.Complex;

class ExpressionVisitor extends ExprBaseVisitor<Element> {

  private final Complex complex;

  public ExpressionVisitor() {
    this.complex = new Complex(1.0, 2.0);
  }

  public Complex getComplex() {
    return complex;
  }

  @Override
  public Element visitGrpComplex(ExprParser.GrpComplexContext ctx) {
    System.out.println("GrpComplex: " + ctx.complex().realPart().getText() + " " + ctx.complex().imagPart().getText());
    super.visitGrpComplex(ctx);
    return null;
  }

  @Override
  public Element visitGRPUNARY(ExprParser.GRPUNARYContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Element visitComplexVector(ExprParser.ComplexVectorContext ctx) {
    ctx.complex().stream().forEach(s -> System.out.println("s.realPart() = {" + s.realPart().getText() + " " + s.imagPart().getText() + "}"));
    super.visitComplexVector(ctx);
    return null;
  }

  @Override
  public Element visitRealVector(ExprParser.RealVectorContext ctx) {
    ctx.REAL().stream().forEach(s -> System.out.println("s.() = {" + s.getText() + "}"));
    System.out.println("ctx = " + ctx.REAL());
    super.visitRealVector(ctx);
    return null;
  }

  @Override
  public Element visitGrpBinary(ExprParser.GrpBinaryContext ctx) {
    String binaryNumber = ctx.BINARY().getText().replace("_", "");
    var binaryValue = Long.valueOf(binaryNumber, 16);
    System.out.println("ctx = " + ctx.BINARY() + " Value:" + binaryValue);
    super.visitGrpBinary(ctx);
    return null;
  }

}
