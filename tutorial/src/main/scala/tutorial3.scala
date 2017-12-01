import scala.util.{ Try, Success, Failure }

object tutorial3 {

  class Rational(val numer: Int, val denom: Int) {

    require(denom != 0) // precondition for the main constructor

    // another constructor can be added like this:
    def this(x: Int) = this(x, 1)

    // adds two Rational numbers and returns a new Rational which represents the sum
    def +(that: Rational) =
      new Rational(
        this.numer * that.denom + that.denom * this.denom,
        this.denom * that.denom)

    override def toString = numer + "/" + denom
  }

  //////////////////////////////////////
  // 1. Companion objects for classes //
  //////////////////////////////////////

  // let's define a companion object for the class Rational
  object Rational {
    def apply(x: Int, y: Int) = new Rational(x, y)
    def apply(x: Int) = new Rational(x)
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1153); 

  // *** Note ***
  // object with method apply is automatically a function
  // F(x) actually means F.apply(x)
  // In fact, every function is translated to an object of class Function1, Function2, ...
  // depending on the number of arguments.

  // now we can write:
  val r1 = Rational(1, 2);System.out.println("""r1  : tutorial3.Rational = """ + $show(r1 ));$skip(444); 

  // *** Note ***
  // Companion objects can be used where static classes were used in Java
  // Typical use is an object factory.

  ///////////////////////////////////////////
  // 2. Exception handling: scala.util.Try //
  ///////////////////////////////////////////

  // documentation: http://www.scala-lang.org/files/archive/nightly/docs/library/index.html#scala.util.Try
  
  //val r2 = Rational(1, 0)
  val r3 = Try(Rational(1, 0));System.out.println("""r3  : scala.util.Try[tutorial3.Rational] = """ + $show(r3 ));$skip(150); 

  // pattern matching -> very powerful feature
  r3 match {
    case Success(x) => println(x)
    case Failure(err) => println("Error: " + err)
  };$skip(24); 

  println("Reached!");$skip(381); 

  //////////////////////////////////
  // 3. Lists and list operations //
  //////////////////////////////////

  // documentation: http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.List

  // Note that in the below:
  // - type of the list is inferred
  // - a List companion object is used to create the list instance
  var l1 = List(2, 1, 3, 5, 4);System.out.println("""l1  : List[Int] = """ + $show(l1 ));$skip(12); val res$0 = 

  l1.head;System.out.println("""res0: Int = """ + $show(res$0));$skip(10); val res$1 = 
  l1.tail;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(12); val res$2 = 
  l1.sorted;System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(173); 

  // note how pattern matching is used to decompose a list into head and tail
  def sum(l: List[Int]): Int = l match {
    case x :: xs => x + sum(xs)
    case _ => 0
  };System.out.println("""sum: (l: List[Int])Int""");$skip(12); val res$3 = 

  sum(l1);System.out.println("""res3: Int = """ + $show(res$3));$skip(24); val res$4 = 

  l1 map (x => 2 * x);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(17); val res$5 = 
  l1 map (2 * _);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(15); val res$6 = 
  l1 map (2 *);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(61); val res$7 = 

  List(List(1, 2), List(3), List(4, 5)) flatMap (identity);System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(32); val res$8 = 

  l1 reduce ((x, y) => x + y);System.out.println("""res8: Int = """ + $show(res$8));$skip(20); val res$9 = 
  l1 reduce (_ + _);System.out.println("""res9: Int = """ + $show(res$9));$skip(26); val res$10 = 

  l1.foldLeft(0)(_ + _);System.out.println("""res10: Int = """ + $show(res$10));$skip(52); val res$11 = 
  l1.sorted.foldLeft(List[Int]())((b, a) => a :: b);System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(142); val res$12 = 
  // List(a, b, c, d)
  // foldRight:  (a + (b + (c + (d + 0))))
  // foldLeft:   ((((0 + a) + b) + c) + d)

  l1.sorted.foldRight(0)(_ - _);System.out.println("""res12: Int = """ + $show(res$12));$skip(31); val res$13 = 
  l1.sorted.foldLeft(0)(_ - _);System.out.println("""res13: Int = """ + $show(res$13));$skip(119); 

  ///////////////////////////
  // 4. for comprehensions //
  ///////////////////////////

  val lx = List(1, 2, 3);System.out.println("""lx  : List[Int] = """ + $show(lx ));$skip(47); 

  val l2 = for {
    x <- lx
  } yield x * x;System.out.println("""l2  : List[Int] = """ + $show(l2 ));$skip(26); 

  val ly = List(4, 5, 0);System.out.println("""ly  : List[Int] = """ + $show(ly ));$skip(161); 
  
  val l3 = for {
    x <- lx      // generator 1
    y <- ly      // generator 2
    if y != 0    // filter
    r = Rational(x, y) // expression
  } yield r;System.out.println("""l3  : List[tutorial3.Rational] = """ + $show(l3 ))}
  
  // *** Note
  // for comprehension is just a convenient syntax for complex combinations of map/flatMap/filter operations:
  // for (x <- expr_1 if expr_2) yield expr_3
  //     is translated to:
  // expr_1 filter (x => expr_2) map (x => expr_3)
  // With many generators:
  // for (x <- expr_1; y <- expr_2; seq) yield expr_3     (where seq is arbitrary sequence of generators)
  //     is translated to
  // expr_1.flatMap(x => for (y <- expr_2; seq) yield expr_3)
  //
  // ***** any class which contains map/flatMap/filter can be used in for comprehensions *****

}
