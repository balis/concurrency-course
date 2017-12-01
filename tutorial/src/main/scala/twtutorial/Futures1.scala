package twtutorial

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

// Documentation: http://www.scala-lang.org/api/current/scala/concurrent/index.html

object Futures1 extends App {

  // used by 'time' method
  implicit val baseTime = System.currentTimeMillis

  // 1. Create a Future - asynchronous computation
  val f = Future {
      Thread.sleep(500)
      1 + 1
  }

  // 2. Await for its result. 
  // Blocking -- SHOULD NOT be used!
  val result = Await.result(f, 1 second)
  println(result)
  Thread.sleep(1000)

}