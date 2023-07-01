<!---
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
# rpn-calculator

[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)][license]
[![Main](https://github.com/khmarbaise/rpn-calculator/actions/workflows/main.yaml/badge.svg)](https://github.com/khmarbaise/rpn-calculator/actions/workflows/main.yaml)
[![Issues](https://img.shields.io/github/issues/khmarbaise/rpn-calculator)](https://github.com/khmarbaise/rpn-calculator/issues)
[![Issues Closed](https://img.shields.io/github/issues-closed/khmarbaise/rpn-calculator)](https://github.com/khmarbaise/rpn-calculator/issues?q=is%3Aissue+is%3Aclosed)
[![codecov](https://codecov.io/gh/khmarbaise/rpn-calculator/branch/master/graph/badge.svg?token=RULU3ULC3O)](https://codecov.io/gh/khmarbaise/rpn-calculator)


 * Real Number `real`
 * Complex Number `(re,im)`
 * Binary Integer `#1234AB` (64 Bit)
 * String `"Text"`
 * Real Vector `[reall real]`
 * Real Matrix `[[r11,r21][r21,r22][r31,r32]]`
 * Complex Vector `[(a,b) (a,b)]`
 * Complex Matrix `[[(a,b),(a,b)][(a,b),(a,b)][(a,b),(a,b)]]`
 * List `{Object, Object,...}`
 * Expression `'Ausdruck'`

# Step 2

 * Fraction `(nom;denom)`

# Build

Code coverage via:
```bash
mvn clean verify org.jacoco:jacoco-maven-plugin:report
```

https://www.manualslib.com/manual/1527472/Hp-Hp-28s.html?page=44#manual
https://www.youtube.com/watch?v=eni1hv94gRA

Precision HP28S vs. IEEE 754 double precision (Java Double)
* http://thimet.de/CalcCollection/Calculators/HP-28SC/HP-28S-Quick-Reference.pdf
* https://en.wikipedia.org/wiki/Double-precision_floating-point_format


https://tredje.se/rpn28xcalc.html

https://github.com/sympy/sympy

* https://hackernoon.com/building-your-own-programming-language-from-scratch
* https://github.com/alexandermakeev/toy-language

* https://jakubdziworski.github.io/categories.html#Enkel-ref
 
[license]: https://www.apache.org/licenses/LICENSE-2.0
