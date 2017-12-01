package twscala

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Try, Success, Failure}
import scala.util.Random

object Futures3 extends App {

  def delayedInt(n: Int, t: Duration): Future[Int] = future {
    blocking { Thread.sleep(t.toMillis) }
    n
  }

  val f1 = Future { 1 }
  val f2 = Future { 2 }
  val f3 = delayedInt(3, 10 seconds)
  val p = Promise[Int]
  val f4 = p.future

  f3 onComplete {
    case Success(x) => {  
      p success 4 // using promise, we can complete a future with value...
      //p failure (new Exception("Failure!")) // ... or fail it -> uncomment this line and try!
    }
    case Failure(err) => println("Error: " + err.getMessage)
  }

  val listF = List(f1, f2, f3, f4)
  val futureL = Future.sequence(listF) // list of futures -> future of list

  futureL onSuccess {
    case b => println("Succ: " + b)
  }

  futureL onFailure {
    case b => println("Fail: " + b)
  }

  Thread.sleep(20000)
  //Await.result(futureL, 2 seconds)
}
