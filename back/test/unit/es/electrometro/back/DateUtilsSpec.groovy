package es.electrometro.back

import spock.lang.Specification

class DateUtilsSpec extends Specification {

  def setupSpec() {
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

  def 'setToZeroTimePart'() {
    when:
    def calendar = DateUtils.setToZeroTimePart(new Date()).toCalendar()

    then:
    calendar.get(Calendar.HOUR_OF_DAY) == 0
    calendar.get(Calendar.MINUTE) == 0
    calendar.get(Calendar.SECOND) == 0
    calendar.get(Calendar.MILLISECOND) == 0
  }

  def 'lastMomentYesterday'() {
    when:
    def calendar = DateUtils.lastMomentYesterday().toCalendar()

    then:
    calendar.get(Calendar.DAY_OF_MONTH) == 29
    calendar.get(Calendar.MONTH) == 9
    calendar.get(Calendar.YEAR) == 1977
    calendar.get(Calendar.HOUR_OF_DAY) == 23
    calendar.get(Calendar.MINUTE) == 59
    calendar.get(Calendar.SECOND) == 59
    calendar.get(Calendar.MILLISECOND) == 30
  }

  def 'lastMomentToday'() {
    when:
    def calendar = DateUtils.lastMomentToday().toCalendar()

    then:
    calendar.get(Calendar.DAY_OF_MONTH) == 30
    calendar.get(Calendar.MONTH) == 9
    calendar.get(Calendar.YEAR) == 1977
    calendar.get(Calendar.HOUR_OF_DAY) == 23
    calendar.get(Calendar.MINUTE) == 59
    calendar.get(Calendar.SECOND) == 59
    calendar.get(Calendar.MILLISECOND) == 30
  }

  def 'lastMomentPreviousHour'() {
    when:
    def calendar = DateUtils.lastMomentPreviousHour().toCalendar()

    then:
    calendar.get(Calendar.DAY_OF_MONTH) == 30
    calendar.get(Calendar.MONTH) == 9
    calendar.get(Calendar.YEAR) == 1977
    calendar.get(Calendar.HOUR_OF_DAY) == 17
    calendar.get(Calendar.MINUTE) == 59
    calendar.get(Calendar.SECOND) == 59
    calendar.get(Calendar.MILLISECOND) == 30
  }

  def 'lastMomentActualHour'() {
    when:
    def calendar = DateUtils.lastMomentActualHour().toCalendar()

    then:
    calendar.get(Calendar.DAY_OF_MONTH) == 30
    calendar.get(Calendar.MONTH) == 9
    calendar.get(Calendar.YEAR) == 1977
    calendar.get(Calendar.HOUR_OF_DAY) == 18
    calendar.get(Calendar.MINUTE) == 59
    calendar.get(Calendar.SECOND) == 59
    calendar.get(Calendar.MILLISECOND) == 30
  }
}

