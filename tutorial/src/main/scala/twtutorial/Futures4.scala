package twtutorial

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }
import scala.util.Random

object Futures4 extends App {

  def asyncWork(n: Int): Future[Int] = Future {
    blocking { Thread.sleep(Random.nextInt(500)) }
    n
  }

  val result1 = asyncWork(1)
  val result2 = asyncWork(2)
  val result3 = asyncWork(3)

  val sum = for {
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield (r1 + r2 + r3)

  val sqr = sum map { s => s * s }

  sqr onComplete {
    case Success(value) => println(s"Result = $value")
    case Failure(e)     => e.printStackTrace
  }

  Thread.sleep(20000)
}
