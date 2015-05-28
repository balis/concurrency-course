package webcrawler

object WebCrawler extends App {
  import scala.io.Source
  import org.htmlcleaner.HtmlCleaner
  import java.net.URL

  //val url = "http://www.icsr.agh.edu.pl/~balis"
  val url = "http://google.com"

  val cleaner = new HtmlCleaner
  val props = cleaner.getProperties

  val html = Source.fromURL(url)

  val rootNode = cleaner.clean(html.mkString) 
  // mozna tez od razu z URLa: cleaner.clean(new URL(url))

  val elements = rootNode.getElementsByName("a", true) 

  elements.toList map { elem => 
    val url = elem.getAttributeByName("href")
    println(url.toString) 
  }
}
