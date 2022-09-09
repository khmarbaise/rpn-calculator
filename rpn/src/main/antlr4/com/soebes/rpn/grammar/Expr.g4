grammar Expr;

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

hp28: expr*;

expr: '-' expr # GRPUNARY
    | expr multiplicationOp expr # GRPMULTIPLICATION
    | expr additionOp expr # GRPADDITION
    | '(' expr ')' # GRPPARENT
    | complex #grpComplex
    | realVector # grpRealVector
    | realMatrix # grpRealMatrix
    | complexVector # grpComplexVector
    | complexMatrix # grpComplexMatrix
    | REAL # grpReal
    | BINARY # grpBinary
    ;

complex: COMPLEXLP realPart SEP imagPart COMPLEXRP;
complexVector: VECTORLP complex+ VECTORRP;
complexMatrix: VECTORLP complexVector+ VECTORRP;
realVector: VECTORLP REAL+ VECTORRP;
realMatrix: VECTORLP realVector+ VECTORRP;

realPart: REAL;
imagPart: REAL;

multiplicationOp: MUL | DIV | MOD ;
additionOp: ADD | SUB;

REAL: [+-]? ([0-9])* '.' ([0-9])* (('e' | 'E') [+-]? ([0-9])+)?
    | [+-]? ([0-9])+ (('e' | 'E') [+-]? ([0-9])+)?;

BINARY: '#' [a-fA-F0-9][_a-fA-F0-9]*;

WS: [ \t\n]+ -> skip;

VECTORLP: '[';
VECTORRP: ']';
COMPLEXLP: '(';
COMPLEXRP: ')';
SEP: ',';

MUL: '*';
DIV: '/';
MOD: '%';
ADD: '+';
SUB: '-';