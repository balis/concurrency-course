package twtutorial

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import scala.util.Random

object Futures3 extends App {

  def asyncPrint(n: Int): Future[Int] = Future {
    blocking { Thread.sleep(Random.nextInt(500)) }
    println(n)
    n
  }

  val f1 = asyncPrint(1)
  val f2 = asyncPrint(2)
  val f3 = asyncPrint(3)
  
  Thread.sleep(20000)
  //Await.result(futureL, 2 seconds)
}
