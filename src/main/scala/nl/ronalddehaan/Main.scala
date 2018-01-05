package nl.ronalddehaan

import fetcher.UfcFetcher

object Main extends App {

  // From the event overview page compile a list containing links
  // to all the detail pages.
  val links = UfcFetcher.links

  // From the list of links take some link and fetch the results.
  val details = UfcFetcher.eventDetails(links(1).get.event.get)

  // Print the to the console
  details.foreach(println)
}
