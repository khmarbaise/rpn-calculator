package com.soebes.rpn.numbers;

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

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Type.VOID_TYPE;

class EmitByteCodeTest {

  @Test
  void first_xxx() throws IOException {
    ClassWriter writer = new ClassWriter(COMPUTE_FRAMES);

    writer.visit(Opcodes.V19, ACC_PUBLIC, "com/soebes/rpn/bytecode/emitter/First", null, Type.getInternalName(Object.class), null);
    writer.visitEnd();

    //Create constructor of the class:
    var constructorVisitor = writer.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);

    constructorVisitor.visitCode();
    constructorVisitor.visitVarInsn(ALOAD, 0);
    constructorVisitor.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", Type.getMethodDescriptor(VOID_TYPE),false);
    constructorVisitor.visitInsn(RETURN);
    constructorVisitor.visitMaxs(1,1);
    constructorVisitor.visitEnd();

    var voidMainVisitor = writer.visitMethod(
        ACC_PUBLIC | ACC_STATIC,
        "main",
        "([Ljava/lang/String;)V",
        null,
        null
    );
    voidMainVisitor.visitInsn(RETURN);
    voidMainVisitor.visitEnd();

//    writer.visitSource("Generated", "x");

    writer.visitEnd();

    var classDirectory = Path.of("target", "com", "soebes", "rpn", "bytecode", "emitter");

    Files.createDirectories(classDirectory);
    Files.write(classDirectory.resolve("First.class"), writer.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  @Test
  void name() throws IOException {
    File resultClassFile = Path.of("target", "generated").toFile();
    DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
        .subclass(Object.class)
        .name("codeemitter.Main")
        .make();

    dynamicType.saveIn(resultClassFile);
  }
}
