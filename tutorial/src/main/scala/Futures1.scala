package twscala

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.Random

// documentation: http://www.scala-lang.org/api/current/index.html#scala.concurrent.Future$

object Futures1 extends App {

  val f = Future { 1 }
  
  def asyncInt(n: Int) = Future {
    //println(n + ". " + Thread.currentThread.getName + ".")
    n
  }

  val futures = for {
    n <- 1 to 100
  } yield asyncInt(n)
  
  val sumF = Future.fold(futures)(0)(_ + _)
  
  val sum = Await.result(sumF, 2 seconds)
  println(sum)
}