object tutorial2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(342); val res$0 = 

  ////////////////
  // 1. Classes //
  ////////////////

  // The following definition (single line!) introduces:
  // - new type Rational
  // - object constructor Rational(x: Int, y: Int)
  // - two fields: numer: Int, denom: Int
  {
    class Rational(val numer: Int, val denom: Int)

    val r1 = new Rational(1, 2)
    r1.numer
  }

  // Let's add a few methods to this class:
  class Rational(val numer: Int, val denom: Int) {

    // another constructor can be added like this:
    def this(x: Int) = this(x, 1)

    // adds two Rational numbers and returns a new Rational which represents the sum
    def add(that: Rational) =
      new Rational(
        this.numer * that.denom + that.denom * this.denom,
        this.denom * that.denom)

    override def toString = numer + "/" + denom
  };System.out.println("""res0: Int = """ + $show(res$0));$skip(496); 

  val r1 = new Rational(2);System.out.println("""r1  : tutorial2.Rational = """ + $show(r1 ));$skip(30); 
  val r2 = new Rational(3, 4);System.out.println("""r2  : tutorial2.Rational = """ + $show(r2 ));$skip(246); val res$1 = 

  // all two-argument functions can be written in an infix notation
  // "r1.add(r2)" is equivalent to "r1 add r2"
  // in fact method "add" could be named "+" because it is a valid identifier!
  // -> please make this modification
  r1 add r2

  ////////////////////////////////////
  // 2. Abstract classes and traits //
  ////////////////////////////////////

  // When to use which -> read: http://www.artima.com/pins1ed/traits.html#12.7

  trait IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def isEmpty: Boolean
  }

  class Empty extends IntSet {
    def contains(x: Int): Boolean = false
    def isEmpty: Boolean = true
    def incl(x: Int) = new NonEmpty(List(x))
  }

  class NonEmpty(val elements: List[Int]) extends IntSet {
    def this(x: Int) = this(List(x))
    def contains(x: Int): Boolean = elements contains x
    def isEmpty: Boolean = false
    def incl(x: Int) =
      if (contains(x))
        this
      else
        new NonEmpty((x :: elements).sorted)

    override def toString = elements mkString ","
  };System.out.println("""res1: tutorial2.Rational = """ + $show(res$1));$skip(854); 

  val s1 = new NonEmpty(1);System.out.println("""s1  : tutorial2.NonEmpty = """ + $show(s1 ));$skip(21); 
  val s2 = s1 incl 2;System.out.println("""s2  : tutorial2.NonEmpty = """ + $show(s2 ));$skip(21); 
  val s3 = s2 incl 1;System.out.println("""s3  : tutorial2.NonEmpty = """ + $show(s3 ))}

// Exercise 1: implement methods "union", "intersect" and "diff" that return a new set which
// represents union, intersection, and difference of two sets.

}
