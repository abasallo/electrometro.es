package es.electrometro.back

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class HourlyEnergyBid {

  Date date
  BigDecimal price

  static constraints = {
    date unique: true
  }

  static todayBidData() {
    HourlyEnergyBid.findAllByDateBetween(DateUtils.lastMomentYesterday(), DateUtils.lastMomentToday())
  }

  static afterTodayBidData() {
    HourlyEnergyBid.findAllByDateGreaterThan(DateUtils.lastMomentToday())
  }

  static actualBidData() {
    HourlyEnergyBid.findByDateBetween(DateUtils.lastMomentPreviousHour(), DateUtils.lastMomentActualHour())
  }

  static todayBidDataAfterActual() {
    HourlyEnergyBid.findAllByDateBetween(DateUtils.lastMomentActualHour(), DateUtils.lastMomentToday())
  }

  static todayMinimumPrice() {
    todayBidData()*.price.min()
  }

  static todayMinimumFuturePrice() {
    todayBidDataAfterActual()*.price.min()
  }

  static todayAveragePrice() {
    todayBidData()*.price.sum() / todayBidData()*.price.size()
  }

  static todayMaximumPrice() {
    todayBidData()*.price.max()
  }

  static todayPriceRange() {
    [from: todayMinimumPrice(), to: todayMaximumPrice()]
  }

  static todayGreenRange() {
    def rangeLength = (todayMaximumPrice() - todayMinimumPrice()) / 3
    [from: todayMinimumPrice(), to: todayMinimumPrice() + rangeLength]
  }

  static todayYellowRange() {
    def rangeLength = (todayMaximumPrice() - todayMinimumPrice()) / 3
    [from: todayMinimumPrice() + rangeLength, to: todayMinimumPrice() + 2 * rangeLength]
  }

  static todayRedRange() {
    def rangeLength = (todayMaximumPrice() - todayMinimumPrice()) / 3
    [from: todayMinimumPrice() + 2 * rangeLength, to: todayMaximumPrice()]
  }

  static isActualPriceIn(zoneRange) {
    zoneRange.from <= actualBidData().price && actualBidData().price <= zoneRange.to
  }

  static todayBidDataAfterActualWithPriceIn(zoneRange) {
    todayBidDataAfterActual().findAll { zoneRange.from <= it.price && it.price <= zoneRange.to }
  }

  static todayBidDataAfterActualWithPriceUnder(price) {
    todayBidDataAfterActualWithPriceIn([from: 0, to: price])
  }

  static isThereTodayBidDataAfterActualWithPriceIn(zoneRange) {
    !!todayBidDataAfterActualWithPriceIn(zoneRange)
  }

  static Boolean isThereTodayBidDataAfterActualWithPriceUnder(price) {
    isThereTodayBidDataAfterActualWithPriceIn([from: 0, to: price])
  }
}
