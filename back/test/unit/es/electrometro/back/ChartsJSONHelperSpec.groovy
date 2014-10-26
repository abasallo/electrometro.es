package es.electrometro.back

import spock.lang.Specification

class ChartsJSONHelperSpec extends Specification {

  def 'generateGaugeJSON'() {
    setup:
    HourlyEnergyBid.metaClass.static.todayMinimumPrice = { 7.699 }
    HourlyEnergyBid.metaClass.static.actualBidData = { [price: 8 ] }
    HourlyEnergyBid.metaClass.static.todayMaximumPrice = { 9.899 }

    expect:
    ChartsJSONHelper.generateGaugeJSON([gaugeOptions: 'gaugeOptions']) == [min: 7.70, value: 8, max: 9.90] + [gaugeOptions: 'gaugeOptions']

    cleanup:
    HourlyEnergyBid.metaClass = null
  }
}

