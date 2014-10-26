package es.electrometro.back

class EsiosScraperJob {

  static triggers = {
    simple name: 'bootstrapTrigger', startDelay: 30000, repeatInterval: 60000, repeatCount: 7
    cron name: 'cronTrigger', startDelay: 10000, cronExpression: '0 0/5 20 1/1 * ? *'
  }

  def execute() {

    Boolean hasTodayDataAlreadyBeenRecovered = HourlyEnergyBid.todayBidData()
    Boolean hasTomorrowDataAlreadyBeenRecovered = HourlyEnergyBid.afterTodayBidData()

    if (!hasTodayDataAlreadyBeenRecovered || !hasTomorrowDataAlreadyBeenRecovered) {

      def dateOfLatestPublishedEnergyBid = DateUtils.setToZeroTimePart(EsiosScraperHelper.getParsedFeesListLatestAvailableDate())

      def today = DateUtils.setToZeroTimePart(new Date())
      def tomorrow = DateUtils.setToZeroTimePart(today + 1)

      def recoveredBidData = EsiosScraperHelper.getParsedFeesList(today)
      def generalFeeBidData = recoveredBidData[0]

      if (!hasTodayDataAlreadyBeenRecovered && dateOfLatestPublishedEnergyBid >= today) {
        saveRecoveredDataFor(today, generalFeeBidData)
      }

      if (!hasTomorrowDataAlreadyBeenRecovered && dateOfLatestPublishedEnergyBid >= tomorrow) {
        saveRecoveredDataFor(tomorrow, generalFeeBidData)
      }
    }
  }

  def void saveRecoveredDataFor(Date today, generalFeeBidData) {
    def calendar = today.toCalendar()
    def hour = 0
    generalFeeBidData.each {
      calendar.set(Calendar.HOUR_OF_DAY, hour)
      hour++
      new HourlyEnergyBid(
          date: new Date(calendar.getTimeInMillis()),
          price: it / 10).save()
    }
  }
}