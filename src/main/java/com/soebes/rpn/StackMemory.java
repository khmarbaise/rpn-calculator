package com.soebes.rpn;

import org.apiguardian.api.API;

import java.util.ArrayDeque;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
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
