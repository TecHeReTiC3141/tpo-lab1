package lab1.task1.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigonometricTest {

  @ParameterizedTest
  @DisplayName("Check corner values")
  @ValueSource(doubles = {
    -1000,
    -10,
    -1.0,
    -0.999999,
    -0.5,
    -0.000001,
    -0.0,
    0.0,
    0.000001,
    0.5,
    0.999999,
    1.0,
    10,
    1000,
    -Math.PI / 4,
    Math.PI / 4,
    -Math.PI / 2,
    Math.PI / 2,
    -Math.PI,
    Math.PI,
    Double.NaN,
    Double.POSITIVE_INFINITY,
    Double.NEGATIVE_INFINITY
  })
  void checkCornerDots(double param) {
    assertEquals(Math.atan(param),
      Trigonometric.arctg(param),
      0.0001);
  }

  @ParameterizedTest(name = "arctg({0}) = {1}")
  @DisplayName("Check between dots [-1; +1]")
  @CsvFileSource(resources = "/table_values.csv", numLinesToSkip = 1, delimiter = ';')
  void checkBetweenDots(double x, double y) {
    assertEquals(y,
      Trigonometric.arctg(x),
      0.0001);
  }
}
