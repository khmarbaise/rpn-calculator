package com.soebes.rpn.grammar;

import com.soebes.rpn.types.Complex;

public class GrpComplex extends ExprBaseVisitor<Complex> {
  @Override
  public Complex visitGrpComplex(ExprParser.GrpComplexContext ctx) {
    System.out.println("GrpComplex.visitGrpComplex");
    ctx.complex().realPart().getText();
    ctx.complex().imagPart().getText();
    return super.visitGrpComplex(ctx);
  }
}
