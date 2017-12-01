object tutorial {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(61); 
  println("Welcome to the Scala worksheet");$skip(246); 

  //////////////////////////////////
  // 1. Evaluation of expressions //
  //////////////////////////////////

  // a. Evaluation "by name" and "by value"
  /////////////////////////////////////////

  // def vs. val
  def loop: Boolean = loop;System.out.println("""loop: => Boolean""");$skip(16); 
  def x1 = loop;System.out.println("""x1: => Boolean""");$skip(130); 
  //val x2 = loop   // uncomment and try!

  // both parameters are evaluated "by value"
  def sqr(x: Double, y: Boolean) = x * x;System.out.println("""sqr: (x: Double, y: Boolean)Double""");$skip(127); 

  // "=>" means that the parameter will be evaluated "by name" (when referenced)
  def sqr2(x: Double, y: => Boolean) = x * x;System.out.println("""sqr2: (x: Double, y: => Boolean)Double""");$skip(59); val res$0 = 

  //sqr(2, loop)    // uncomment and try!
  sqr2(2, loop);System.out.println("""res0: Double = """ + $show(res$0));$skip(142); 

  // b. if expression
  //////////////////////

  // in Scala, "if" is an expression (has value)
  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(10); val res$1 = 
  abs(-2);System.out.println("""res1: Double = """ + $show(res$1));$skip(9); val res$2 = 
  abs(2);System.out.println("""res2: Double = """ + $show(res$2));$skip(454); 

  // c. Blocks: { .... }
  /////////////////////////

  // Blocks may contain a sequence of definitions or expressions.
  // - Definitions are only visible inside the block
  // - Last element of the block must be an expression whose value is also the value of the block.

  // Example: tail-recursive factorial

  def fact(n: Int): Int = {
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)

    loop(1, n)
  };System.out.println("""fact: (n: Int)Int""");$skip(11); val res$3 = 

  fact(6);System.out.println("""res3: Int = """ + $show(res$3));$skip(270); 

  /////////////////////////////
  // 2. High-order functions //
  /////////////////////////////

  // Exercise 1: write a function that computes SUM(f(x)) where
  //             x are all between a and b: a <= x <= b
  def sum(a: Int, b: Int, f: Int => Int): Int = ???;System.out.println("""sum: (a: Int, b: Int, f: Int => Int)Int""");$skip(135); 

  // For example, we can compute the sum of all integers from a given range:
  def sumInts(a: Int, b: Int) = sum(a, b, (x: Int) => x);System.out.println("""sumInts: (a: Int, b: Int)Int""");$skip(90); 

  // Or sume of squares:
  def sumSquares(a: Int, b: Int) = sum(a, b, (x: Int) => x * x);System.out.println("""sumSquares: (a: Int, b: Int)Int""");$skip(335); 

  
  // a. Functions returning functions
  ////////////////////////////////////

  // Function "sum" can also *return* a new "specialized" function:
  def sum2(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF // returned value is function sumF
  };System.out.println("""sum2: (f: Int => Int)(Int, Int) => Int""");$skip(130); 

  // the equivalent syntax is:
  // def sum2(f: Int => Int)(Int, Int): Int

  // we can now write:
  def sumInts2 = sum2(x => x);System.out.println("""sumInts2: => (Int, Int) => Int""");$skip(37); 
  def sumSquares2 = sum2(x => x * x);System.out.println("""sumSquares2: => (Int, Int) => Int""");$skip(49); val res$4 = 

  // or use sum2 directly:
  sum2(x => x)(1, 4);System.out.println("""res4: Int = """ + $show(res$4));$skip(25); val res$5 = 
  sum2(x => x * x)(1, 4);System.out.println("""res5: Int = """ + $show(res$5));$skip(286); 

  // We could even further generalize this example by writing a function that computes not only sum:
  def mapReduce(mapF: Int => Int, reduceF: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else reduceF(mapF(a), mapReduce(mapF, reduceF, zero)(a + 1, b));System.out.println("""mapReduce: (mapF: Int => Int, reduceF: (Int, Int) => Int, zero: Int)(a: Int, b: Int)Int""");$skip(46); val res$6 = 
  mapReduce(x => x, (x, y) => x + y, 0)(1, 4);System.out.println("""res6: Int = """ + $show(res$6));$skip(301); 

  // Or we can use type parameters to write a generic map-reduce:
  // - the elements are now passed as a list (not a range)
  // - the implementation is only a single line!
  def mapReduceG[T](mapF: T => T, reduceF: (T, T) => T, zero: T)(elems: List[T]): T =
    elems.map(mapF).fold(zero)(reduceF);System.out.println("""mapReduceG: [T](mapF: T => T, reduceF: (T, T) => T, zero: T)(elems: List[T])T""");$skip(65); val res$7 = 

  mapReduceG[Int](x => x, (x, y) => x + y, 0)(List(1, 2, 3, 4));System.out.println("""res7: Int = """ + $show(res$7));$skip(92); val res$8 = 
  mapReduceG[String](x => x.capitalize, (x, y) => x concat y, "")(List("a", "b", "c", "d"));System.out.println("""res8: String = """ + $show(res$8))}
}
