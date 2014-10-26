package es.electrometro.back

import java.math.RoundingMode

class ChartsJSONHelper {

  static generateGaugeJSON(gaugeOptions) {
    // TODO - Extract rounding mode to config
    [min: HourlyEnergyBid.todayMinimumPrice().setScale(2, RoundingMode.CEILING),
        value: HourlyEnergyBid.actualBidData().price,
        max: HourlyEnergyBid.todayMaximumPrice().setScale(2, RoundingMode.CEILING)] + gaugeOptions
  }

  static generateLinesJSON(linesOptions) {

    def todayBidData = HourlyEnergyBid.todayBidData()
    def hourlyDate = todayBidData*.date
    def hourlyPrice = todayBidData*.price

    // TODO - Possible duplicated code with es.electrometro.back.EsiosScraperHelper.buildEsiosUrlDate
    def hourlyDateFormatted = []
    hourlyDate.each { date ->

      def calendar = date.toCalendar()
      String year = calendar.get(Calendar.YEAR)
      String month = calendar.get(Calendar.MONTH)
      String day = calendar.get(Calendar.DAY_OF_MONTH)
      String hour = calendar.get(Calendar.HOUR_OF_DAY)
      String minute = calendar.get(Calendar.MINUTE)

      hourlyDateFormatted << year + '-' + (month.size() == 2 ? month : '0' + month) + '-' + (day.size() == 2 ? day : '0' + day) + ' ' +
          (hour.size() == 2 ? hour : '0' + hour) + ':' + (minute.size() == 2 ? minute : '0' + minute)
    }

    def actualHour = HourlyEnergyBid.actualBidData().date.toCalendar().get(Calendar.HOUR_OF_DAY)

    [data: [
        [hour: hourlyDateFormatted[0], price: hourlyPrice[0]],
        [hour: hourlyDateFormatted[1], price: hourlyPrice[1]],
        [hour: hourlyDateFormatted[2], price: hourlyPrice[2]],
        [hour: hourlyDateFormatted[3], price: hourlyPrice[3]],
        [hour: hourlyDateFormatted[4], price: hourlyPrice[4]],
        [hour: hourlyDateFormatted[5], price: hourlyPrice[5]],
        [hour: hourlyDateFormatted[6], price: hourlyPrice[6]],
        [hour: hourlyDateFormatted[7], price: hourlyPrice[7]],
        [hour: hourlyDateFormatted[8], price: hourlyPrice[8]],
        [hour: hourlyDateFormatted[9], price: hourlyPrice[9]],
        [hour: hourlyDateFormatted[10], price: hourlyPrice[10]],
        [hour: hourlyDateFormatted[11], price: hourlyPrice[11]],
        [hour: hourlyDateFormatted[12], price: hourlyPrice[12]],
        [hour: hourlyDateFormatted[13], price: hourlyPrice[13]],
        [hour: hourlyDateFormatted[14], price: hourlyPrice[14]],
        [hour: hourlyDateFormatted[15], price: hourlyPrice[15]],
        [hour: hourlyDateFormatted[16], price: hourlyPrice[16]],
        [hour: hourlyDateFormatted[17], price: hourlyPrice[17]],
        [hour: hourlyDateFormatted[18], price: hourlyPrice[18]],
        [hour: hourlyDateFormatted[19], price: hourlyPrice[19]],
        [hour: hourlyDateFormatted[20], price: hourlyPrice[20]],
        [hour: hourlyDateFormatted[21], price: hourlyPrice[21]],
        [hour: hourlyDateFormatted[22], price: hourlyPrice[22]],
        [hour: hourlyDateFormatted[23], price: hourlyPrice[23]],
    ],
        events: [hourlyDateFormatted[actualHour]],
        goals: [HourlyEnergyBid.todayAveragePrice().setScale(2, RoundingMode.CEILING)], // TODO - Extract rounding mode to config
        xkey: 'hour',
        ykeys: ['price'],
        ymin: HourlyEnergyBid.todayMinimumPrice().setScale(2, RoundingMode.CEILING), // TODO - Extract rounding mode to config
        ymax: HourlyEnergyBid.todayMaximumPrice().setScale(2, RoundingMode.CEILING), // TODO - Extract rounding mode to config

    ] + linesOptions
  }
}
