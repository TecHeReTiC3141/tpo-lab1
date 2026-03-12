package lab1.task1.utils;

public class Trigonometric {

  public static double arctg(double x) {
    return arctg(x, Integer.MAX_VALUE);
  }

  public static double arctg(double x, int n) {

    if (Double.isNaN(x)) return Double.NaN;

    if (Double.isInfinite(x)) {
      return x > 0 ? Math.PI / 2 : -Math.PI / 2;
    }

    if (Math.abs(x) > 1) {
      if (x > 0)
        return Math.PI / 2 - arctg(1 / x, n);
      else
        return -Math.PI / 2 - arctg(1 / x, n);
    }

    if (Math.abs(x) >= 0.999999) {
      return Math.PI / 4 + arctg((x - 1) / (x + 1), n);
    }


    double result = 0;
    double term = x;

    for (int i = 0; i < n; i++) {
      double old = result;
      result += term / (2 * i + 1);
      term *= -x * x;

      if (Math.abs(result - old) < Double.MIN_VALUE)
        break;
    }

    return result;
  }
}
