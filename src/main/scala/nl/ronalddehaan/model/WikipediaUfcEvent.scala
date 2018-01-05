package nl.ronalddehaan
package model

object WikipediaUfcModel {

  /**
    * Representation of the 'Past events' table on wikipedia.
    *
    * @param number     event number
    * @param title      title of the event
    * @param date       date the event occurred
    * @param venue      event venue
    * @param location   city/country of the event
    */
  case class WikipediaUfcEvent(
    number: String,
    title: String,
    date: String,
    venue: String,
    location: String
  )

  case class UfcEventLinks(
    event: Option[String],
    venue: Option[String] = None,
    location: Option[String] = None
  )

  case class UfcEventDetails(
    weightClass: String,
    fighterA: String,
    fighterB: String,
    method: String,
    round: String,
    time: String,
    notes: String
  )
}
