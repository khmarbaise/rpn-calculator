package com.soebes.rpn;

import java.util.ArrayDeque;

public class StackMemory {
  private final ArrayDeque<Element> stack;

  public StackMemory() {
    this.stack = new ArrayDeque<>();
  }

  public void push(Element item) {
    this.stack.addFirst(item);
  }

  public int size() {
    return this.stack.size();
  }

  public Object pop() {
    return this.stack.removeFirst();
  }
}
