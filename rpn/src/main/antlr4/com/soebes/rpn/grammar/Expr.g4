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

start: expr*;

expr: complex
    | realVector
    | realMatrix
    | complexVector
    | complexMatrix
    | Real;

complex: COMPLEXLP realPart ',' imagPart COMPLEXRP;
complexVector: VECTORLP complex+ VECTORRP;
complexMatrix: VECTORLP complexVector+ VECTORRP;
realVector: VECTORLP Real+ VECTORRP;
realMatrix: VECTORLP realVector+ VECTORRP;

realPart: Real;
imagPart: Real;

Real: ([+-]? DIGIT* '.' DIGIT+ | '.' DIGIT+) EXPONENT?
    | PNDigit+ EXPONENT;

fragment EXPONENT:     EE PNDigit;
fragment NonZeroDigit: [1-9];
fragment PNDigit:      [+-]? DIGIT+;
fragment DIGIT:        [0-9];
fragment EE: 'eE';

WS: [ \t]+ -> skip;

VECTORLP: '[';
VECTORRP: ']';
COMPLEXLP: '(';
COMPLEXRP: ')';