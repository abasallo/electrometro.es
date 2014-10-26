package es.electrometro.back

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(MainController)
class MainControllerSpec extends Specification {

  def 'gaugeChartJSON'() {
    given:
    grailsApplication.config.gaugeChart = [config: 'gaugeChart']
    ChartsJSONHelper.metaClass.static.generateGaugeJSON = { config ->
      config
    }

    when:
    controller.gaugeChartJSON()

    then:
    response.text == '{"config":"gaugeChart"}'
    
    cleanup:
    ChartsJSONHelper.metaClass = null
  }

  def 'linesChartJSON'() {
    given:
    grailsApplication.config.linesChart = [config: 'linesChart']
    ChartsJSONHelper.metaClass.static.generateLinesJSON = { config ->
      config
    }

    when:
    controller.linesChartJSON()

    then:
    response.text == '{"config":"linesChart"}'

    cleanup:
    ChartsJSONHelper.metaClass = null
  }

  def 'todayMinimumPrice'() {
    given:
    HourlyEnergyBid.metaClass.static.todayMinimumPrice = { 7.767 }

    when:
    controller.todayMinimumPrice()

    then:
    response.text == '{"value":7.77}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  def 'todayMinimumFuturePrice'() {
    given:
    HourlyEnergyBid.metaClass.static.todayMinimumFuturePrice = { 8.767 }

    when:
    controller.todayMinimumFuturePrice()

    then:
    response.text == '{"value":8.77}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  def 'todayAveragePrice'() {
    given:
    HourlyEnergyBid.metaClass.static.todayAveragePrice = { 7.767 }

    when:
    controller.todayAveragePrice()

    then:
    response.text == '{"value":7.77}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  def 'todayMaximumPrice'() {
    given:
    HourlyEnergyBid.metaClass.static.todayMaximumPrice = { 7.767 }

    when:
    controller.todayMaximumPrice()

    then:
    response.text == '{"value":7.77}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  def 'actualPrice'() {
    given:
    HourlyEnergyBid.metaClass.static.actualBidData = { [price: 7.767] }

    when:
    controller.actualPrice()

    then:
    response.text == '{"value":7.77}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  @Unroll
  def 'isThereAFutureGreenZoneToday'() {
    given:
    HourlyEnergyBid.metaClass.static.todayGreenRange = {}
    HourlyEnergyBid.metaClass.static.isActualPriceIn = { range -> isActualPriceIn }
    HourlyEnergyBid.metaClass.static.isThereTodayBidDataAfterActualWithPriceIn = { range -> isThereTodayBidDataAfterActualWithPriceIn }

    when:
    controller.isThereAFutureGreenZoneToday()

    then:
    response.text == '{"value":' + result + '}'

    cleanup:
    HourlyEnergyBid.metaClass = null

    where:
    isActualPriceIn | isThereTodayBidDataAfterActualWithPriceIn | result
    true            | null                                      | false
    false           | true                                      | true
    false           | false                                     | false
  }

  @Unroll
  def 'isThereAFutureYellowZoneToday'() {
    given:
    HourlyEnergyBid.metaClass.static.todayYellowRange = {}
    HourlyEnergyBid.metaClass.static.isActualPriceIn = { range -> isActualPriceIn }
    HourlyEnergyBid.metaClass.static.isThereTodayBidDataAfterActualWithPriceIn = { range -> isThereTodayBidDataAfterActualWithPriceIn }

    when:
    controller.isThereAFutureYellowZoneToday()

    then:
    response.text == '{"value":' + result + '}'

    cleanup:
    HourlyEnergyBid.metaClass = null

    where:
    isActualPriceIn | isThereTodayBidDataAfterActualWithPriceIn | result
    true            | null                                      | false
    false           | true                                      | true
    false           | false                                     | false
  }

  @Unroll
  def 'isThereAFutureRedZoneToday'() {
    given:
    HourlyEnergyBid.metaClass.static.todayRedRange = {}
    HourlyEnergyBid.metaClass.static.isActualPriceIn = { range -> isActualPriceIn }
    HourlyEnergyBid.metaClass.static.isThereTodayBidDataAfterActualWithPriceIn = { range -> isThereTodayBidDataAfterActualWithPriceIn }

    when:
    controller.isThereAFutureRedZoneToday()

    then:
    response.text == '{"value":' + result + '}'

    cleanup:
    HourlyEnergyBid.metaClass = null

    where:
    isActualPriceIn | isThereTodayBidDataAfterActualWithPriceIn | result
    true            | null                                      | false
    false           | true                                      | true
    false           | false                                     | false
  }

  def 'earliestHourForFutureGreenZoneToday'() {

    given:
    def calendar = new Date().toCalendar()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    HourlyEnergyBid.metaClass.static.todayGreenRange = { [to: null] }
    HourlyEnergyBid.metaClass.static.todayBidDataAfterActualWithPriceIn = { startPrice -> [[date: calendar.getTime()]] }

    when:
    controller.earliestHourForFutureGreenZoneToday()

    then:
    response.text == '{"value":18}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  def 'earliestHourForFutureYellowZoneToday'() {

    given:
    def calendar = new Date().toCalendar()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    HourlyEnergyBid.metaClass.static.todayYellowRange = { [to: null] }
    HourlyEnergyBid.metaClass.static.todayBidDataAfterActualWithPriceIn = { range -> [[date: calendar.getTime()]] }

    when:
    controller.earliestHourForFutureYellowZoneToday()

    then:
    response.text == '{"value":18}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  @Unroll
  def 'earliestHourForFutureRedZoneToday'() {

    given:
    def calendar = new Date().toCalendar()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    HourlyEnergyBid.metaClass.static.todayRedRange = { [to: null] }
    HourlyEnergyBid.metaClass.static.todayBidDataAfterActualWithPriceIn = { range -> [[date: calendar.getTime()]] }

    when:
    controller.earliestHourForFutureRedZoneToday()

    then:
    response.text == '{"value":18}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }

  @Unroll
  def 'earliestHourTodayForFuturePriceUnder'() {

    given:
    def calendar = new Date().toCalendar()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    params.price = '7.0'
    HourlyEnergyBid.metaClass.static.todayBidDataAfterActualWithPriceUnder = { price -> [[date: calendar.getTime()]] }

    when:
    controller.earliestHourTodayForFuturePriceUnder()

    then:
    response.text == '{"value":18}'

    cleanup:
    HourlyEnergyBid.metaClass = null
  }
}
