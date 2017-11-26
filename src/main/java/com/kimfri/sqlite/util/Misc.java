package com.kimfri.sqlite.util;

/**
 *
 * @author kimfr
 */
public class Misc {
  public static void main(String[] args) {
    Misc misc = new Misc();
    misc.doit();
  }

  private void doit() {
    System.err.println("Homdir: " + System.getProperty("user.home"));
  }
}
