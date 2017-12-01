package twscala

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.Random

object Futures2 extends App {
  
  var counter: Int = 0
  
  val incCounter = Future {
    (1 to 1000) map ( _ => counter += 1 )
    counter
  } 

  val decCounter = Future {
    (1 to 1000) map ( _ => counter -= 1 )
    counter
  } 
  
  val countingResult = for {
    x <- incCounter
    y <- decCounter
  } yield (x, y)
  
  countingResult onSuccess {
    case b => println(b, counter)
  }

  Await.result(countingResult, 2 seconds) 
}