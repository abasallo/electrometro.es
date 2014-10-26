package es.electrometro.back

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import spock.lang.Specification
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Unroll

@TestFor(HourlyEnergyBid)
@TestMixin(DomainClassUnitTestMixin)
class HourlyEnergyBidSpec extends Specification {

  def today
  def tomorrow

  def todayCalendar
  def tomorrowCalendar

  def hourlyEnergyBidNullDate
  def hourlyEnergyBidNullPrice

  def hourlyEnergyBid
  def hourlyEnergyBidWithDuplicatedDate

  def todayDates
  def tomorrowDates

  def todayBids
  def tomorrowBids

  def setupSpec() {
    mockDomain HourlyEnergyBid

    def calendar = new Date().toCalendar()
    Date.metaClass.constructor = {
      calendar.set(Calendar.DAY_OF_MONTH, 30)
      calendar.set(Calendar.MONTH, 9)
      calendar.set(Calendar.YEAR, 1977)
      calendar.set(Calendar.HOUR_OF_DAY, 18)
      calendar.set(Calendar.MINUTE, 30)
      calendar.set(Calendar.SECOND, 30)
      calendar.set(Calendar.MILLISECOND, 30)
      calendar.getTime()
    }
  }

  def cleanupSpec() {
    Date.metaClass = null
  }

  def setup() {
    today = new Date()
    tomorrow = today + 1

    todayCalendar = today.toCalendar()
    tomorrowCalendar = tomorrow.toCalendar()

    hourlyEnergyBidNullDate = new HourlyEnergyBid(date: null, price: 1.0)
    hourlyEnergyBidNullPrice = new HourlyEnergyBid(date: today, price: null)

    hourlyEnergyBid = new HourlyEnergyBid(date: today, price: 1.0)
    hourlyEnergyBidWithDuplicatedDate = new HourlyEnergyBid(date: today, price: 2.0)

    todayDates = []
    tomorrowDates = []
    (0..23).each { hour ->
      todayCalendar.set(Calendar.HOUR_OF_DAY, hour)
      tomorrowCalendar.set(Calendar.HOUR_OF_DAY, hour)
      todayDates << todayCalendar.time
      tomorrowDates << tomorrowCalendar.time
    }

    todayBids = []
    todayDates.eachWithIndex { date, index ->
      todayBids << new HourlyEnergyBid(date: date, price: index).save()
    }

    tomorrowBids = []
    tomorrowDates.eachWithIndex { date, index ->
      tomorrowBids << new HourlyEnergyBid(date: date, price: index).save()
    }
  }

  def 'all fields are mandatory'() {
    expect:
    !hourlyEnergyBidNullDate.validate()
    !hourlyEnergyBidNullPrice.validate()
  }

  def 'date is unique'() {
    when:
    hourlyEnergyBid.save(flush:true, failOnError:true)
    hourlyEnergyBidWithDuplicatedDate.save(flush:true, failOnError:true)

    then:
    thrown(Exception)
  }

  def 'afterTodayBidData'() {
    expect:
    HourlyEnergyBid.afterTodayBidData()*.date == tomorrowDates
  }

  def 'todayBidData'() {
    expect:
    HourlyEnergyBid.todayBidData()*.date == todayDates
  }

  def 'todayFutureBidData'() {
    given:

    when:
    def futureBidData = HourlyEnergyBid.todayBidDataAfterActual()

    then:
    futureBidData == todayBids.findAll {
      it.date.toCalendar().get(Calendar.HOUR_OF_DAY) > 18
    }

    cleanup:
    DateUtils.metaClass.static = null
  }

  def 'actualBidData'() {
    expect:
    HourlyEnergyBid.actualBidData().price == 18.0
  }

  def 'todayMinimumPrice'() {
    expect:
    HourlyEnergyBid.todayMinimumPrice() == 0
  }

  def 'todayMinimumFuturePrice'() {
    expect:
    HourlyEnergyBid.todayMinimumFuturePrice() == 19
  }

  def 'todayAveragePrice'() {
    expect:
    HourlyEnergyBid.todayAveragePrice() == 11.5
  }

  def 'todayMaximumPrice'() {
    expect:
    HourlyEnergyBid.todayMaximumPrice() == 23
  }

  def 'todayPriceRange'() {
    expect:
    HourlyEnergyBid.todayPriceRange() == [from: 0, to: 23]
  }

  def 'todayGreenRange'() {
    expect:
    HourlyEnergyBid.todayGreenRange() == [from: 0, to: 7.6666666667]
  }

  def 'todayYellowRange'() {
    expect:
    HourlyEnergyBid.todayYellowRange() == [from:7.6666666667, to:15.3333333334]
  }

  def 'todayRedRange'() {
    expect:
    HourlyEnergyBid.todayRedRange() == [from:15.3333333334, to:23]
  }

  def 'isActualPriceIn'() {
    expect:
    HourlyEnergyBid.isActualPriceIn([from: 15.3333333333, to: 23])
  }

  def 'todayBidDataAfterActualWithPriceIn'() {
    when:
    def bidData = HourlyEnergyBid.todayBidDataAfterActualWithPriceIn([from: 22, to: 23]).first()

    then:
    bidData.price == 22
    bidData.date.toCalendar().get(Calendar.DAY_OF_MONTH) == 30
  }

  def 'todayBidDataAfterActualWithPriceUnder'() {
    when:
    def bidData = HourlyEnergyBid.todayBidDataAfterActualWithPriceUnder(20).first()

    then:
    bidData.price == 19
    bidData.date.toCalendar().get(Calendar.DAY_OF_MONTH) == 30
  }

  def 'isThereTodayBidDataAfterActualWithPriceIn'() {
    expect:
    HourlyEnergyBid.isThereTodayBidDataAfterActualWithPriceIn([from: 22, to: 23])
  }

  @Unroll
  def 'isThereTodayBidDataAfterActualWithPriceUnder'() {

    expect:
    HourlyEnergyBid.isThereTodayBidDataAfterActualWithPriceUnder(price) == result

    where:
    price << [16, 17, 18, 19, 20, 21, 22, 23]
    result << [false, false, false, true, true, true, true, true]
  }
}




