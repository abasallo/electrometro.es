package es.electrometro.back

import grails.converters.JSON

import java.math.RoundingMode

class MainController {

  def grailsApplication

  def beforeInterceptor = {
    header 'Access-Control-Allow-Origin', '*'
    header 'Access-Control-Allow-Methods', 'GET'
  }

  // basallo.es

  def index() {
    sendMail {
      to "alvaro@basallo.es"
      subject 'Formulario de contacto de basallo.es de ' + params.name + ' (' + params.email + ')'
      body params.message
    }

    sendMail {
      to params.email
      subject 'Confirmación de contacto de basallo.es'
      body 'Recientemente ha enviado un mensaje con el siguiente contenido:\n\n' + params.message
    }
  }

  // electrometro.es

  def gaugeChartJSON() {
    render ChartsJSONHelper.generateGaugeJSON(grailsApplication.config.gaugeChart) as JSON
  }

  def linesChartJSON() {
    render ChartsJSONHelper.generateLinesJSON(grailsApplication.config.linesChart) as JSON
  }

  def todayMinimumPrice() {
    // TODO - Extract rounding mode to config
    render([value: HourlyEnergyBid.todayMinimumPrice().setScale(2, RoundingMode.CEILING)] as JSON)
  }

  def todayMinimumFuturePrice() {
    // TODO - Extract rounding mode to config
    render([value: HourlyEnergyBid.todayMinimumFuturePrice().setScale(2, RoundingMode.CEILING)] as JSON)
  }

  def todayAveragePrice() {
    // TODO - Extract rounding mode to config
    render([value: HourlyEnergyBid.todayAveragePrice().setScale(2, RoundingMode.CEILING)] as JSON)
  }

  def todayMaximumPrice() {
    render([value: HourlyEnergyBid.todayMaximumPrice().setScale(2, RoundingMode.CEILING)] as JSON)
  }

  def actualPrice() {
    // TODO - Extract rounding mode to config
    render([value: HourlyEnergyBid.actualBidData().price.setScale(2, RoundingMode.CEILING)] as JSON)
  }

  def isThereAFutureGreenZoneToday() {

    def isThereAFutureGreenZoneToday = false
    def actuallyInGreenZone = HourlyEnergyBid.isActualPriceIn(HourlyEnergyBid.todayGreenRange())
    if (!actuallyInGreenZone)
      isThereAFutureGreenZoneToday = HourlyEnergyBid.isThereTodayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayGreenRange())

    render([value: isThereAFutureGreenZoneToday] as JSON)
  }

  def isThereAFutureYellowZoneToday() {

    def isThereAFutureYellowZoneToday = false
    def actuallyInYellowZone = HourlyEnergyBid.isActualPriceIn(HourlyEnergyBid.todayYellowRange())
    if (!actuallyInYellowZone)
      isThereAFutureYellowZoneToday = HourlyEnergyBid.isThereTodayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayYellowRange())

    render([value: isThereAFutureYellowZoneToday] as JSON)
  }

  def isThereAFutureRedZoneToday() {

    def isThereAFutureRedZoneToday = false
    def actuallyInRedZone = HourlyEnergyBid.isActualPriceIn(HourlyEnergyBid.todayRedRange())
    if (!actuallyInRedZone)
      isThereAFutureRedZoneToday = HourlyEnergyBid.isThereTodayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayRedRange())

    render([value: isThereAFutureRedZoneToday] as JSON)
  }

  def earliestHourForFutureGreenZoneToday() {
    render([value: HourlyEnergyBid.todayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayGreenRange()).first().date.toCalendar().get(Calendar.HOUR_OF_DAY)] as JSON)
  }

  def earliestHourForFutureYellowZoneToday() {
    render([value: HourlyEnergyBid.todayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayYellowRange()).first().date.toCalendar().get(Calendar.HOUR_OF_DAY)]
    as JSON)
  }

  def earliestHourForFutureRedZoneToday() {
    render([value: HourlyEnergyBid.todayBidDataAfterActualWithPriceIn(HourlyEnergyBid.todayRedRange()).first().date.toCalendar().get(Calendar.HOUR_OF_DAY)] as
    JSON)
  }

  def earliestHourTodayForFuturePriceUnder() {

    def parsedPrice = new BigDecimal(params.price)

    render([value: HourlyEnergyBid.todayBidDataAfterActualWithPriceUnder(parsedPrice).first().date.toCalendar().get(Calendar.HOUR_OF_DAY)] as JSON)
  }
}
