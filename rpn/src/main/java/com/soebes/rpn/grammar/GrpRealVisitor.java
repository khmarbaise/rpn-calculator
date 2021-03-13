package com.soebes.rpn.grammar;

import com.soebes.rpn.types.RealValue;

public class GrpRealVisitor extends ExprBaseVisitor<RealValue> {

  @Override
  public RealValue visitGrpReal(ExprParser.GrpRealContext ctx) {
    System.out.println("GrpRealVisitor.visitGrpReal");
    return new RealValue(Double.valueOf(ctx.REAL().getText()));
  }
}
