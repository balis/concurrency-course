import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.Random

object futures {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(170); 

  var counter: Int = 0;System.out.println("""counter  : Int = """ + $show(counter ));$skip(145); 

  val incCounter = future {
    println("INC: " + Thread.currentThread.getName)
    (1 to 1000) map { case _ => counter += 1 }
    counter
  };System.out.println("""incCounter  : scala.concurrent.Future[Int] = """ + $show(incCounter ));$skip(145); 

  val decCounter = future {
    println("DEC: " + Thread.currentThread.getName)
    (1 to 1000) map { case _ => counter -= 1 }
    counter
  };System.out.println("""decCounter  : scala.concurrent.Future[Int] = """ + $show(decCounter ));$skip(133); 

  val decFail = future {
    val p = promise[Unit]
    Thread.sleep(5000)
    p failure (new Exception("lalala"))
    p.future
  };System.out.println("""decFail  : scala.concurrent.Future[scala.concurrent.Future[Unit]] = """ + $show(decFail ));$skip(54); 

  val listF = List(incCounter, decCounter, decFail);System.out.println("""listF  : List[scala.concurrent.Future[Any]] = """ + $show(listF ));$skip(40); 
  val counters = Future.sequence(listF);System.out.println("""counters  : scala.concurrent.Future[List[Any]] = """ + $show(counters ));$skip(54); 

  counters onSuccess {
    case x => println(x)
  };$skip(54); 

  counters onFailure {
    case x => println(x)
  };$skip(39); val res$0 = 

  Await.result(counters, 10 seconds);System.out.println("""res0: List[Any] = """ + $show(res$0));$skip(89); 

  val countingResult = for {
    x <- incCounter
    y <- decCounter
  } yield counter;System.out.println("""countingResult  : scala.concurrent.Future[Int] = """ + $show(countingResult ));$skip(68); 

  countingResult onSuccess {
    case b => Console.println(b)
  };$skip(44); val res$1 = 

  Await.result(countingResult, 2 seconds);System.out.println("""res1: Int = """ + $show(res$1))}
}
