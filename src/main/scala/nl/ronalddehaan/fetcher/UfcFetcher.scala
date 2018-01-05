package nl.ronalddehaan
package fetcher

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.Element

import model.WikipediaUfcModel._

/**
  * Responsible for fetching ufc data from wikipedia.
  *
  * The path to fetching UFC data:
  * - get ufc events page
  * - foreach event
  * -- get event detail page
  * -- fetch results
  */
object UfcFetcher {

  private val browser = JsoupBrowser()
  private val wikipediaBaseUrl = "https://en.wikipedia.org"
  private val ufcEventList     = "wiki/List_of_UFC_events"

  def eventRows: List[List[Element]] = {
    val url = s"$wikipediaBaseUrl/$ufcEventList"
    browser.get(url) >> elementList("#Past_events tr")
      .map(_ >> elementList("td"))
  }


  //todo: this UfcEvent creation is kinda clunky and needs some love ;)
  def events: List[Option[WikipediaUfcEvent]] =
    eventRows
      .map {
        case x@List(_, _, _, _, _, _) =>
          Some(WikipediaUfcEvent(x(0).text, x(1).text, x(2).text, x(3).text, x(4).text))
        case List()                   => None
      }

  def links: Seq[Option[UfcEventLinks]] =
    eventRows
      .map(_ >> elementList("a"))
      .map(_ >> attr("href"))
      .map {
        case List() => None
        case x      => Some(UfcEventLinks(Some(x(1).head)))
      }

  def eventDetailRows(path: String) =
    browser.get(s"$wikipediaBaseUrl$path") >> elementList(".toccolours tr")
      .map(_ >> elementList("td"))


  //todo: Same as above, the way the EventDetails are created is kinda clunky
  def eventDetails(path: String) = {
    eventDetailRows(path)
      .map {
        case x@List(_, _, _, _, _, _, _, _) =>
          Some(UfcEventDetails(x(0).text, x(1).text, x(3).text, x(4).text, x(5).text, x(6).text, x(7).text))
        case List() =>
      }
  }

}
