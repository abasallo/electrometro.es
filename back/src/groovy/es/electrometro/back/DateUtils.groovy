package es.electrometro.back

class DateUtils {

  static Date setToZeroTimePart(Date date) {
    def calendar = date.toCalendar()
    calendar.clear(Calendar.HOUR_OF_DAY)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    calendar.getTime()
  }

  static Date lastMomentYesterday() {
    Calendar lastMomentYesterday = (new Date() -1).toCalendar()
    lastMomentYesterday.set(hourOfDay: 23, minute: 59, second: 59)
    lastMomentYesterday.getTime()
  }

  static Date lastMomentToday() {
    Calendar lastMomentToday = (new Date()).toCalendar()
    lastMomentToday.set(hourOfDay: 23, minute: 59, second: 59)
    lastMomentToday.getTime()
  }

  static Date lastMomentPreviousHour() {
    Calendar lastMomentPreviousHour = (new Date()).toCalendar()
    lastMomentPreviousHour.set(hourOfDay: lastMomentPreviousHour.get(Calendar.HOUR_OF_DAY) -1, minute: 59, second: 59)
    lastMomentPreviousHour.getTime()
  }

  static Date lastMomentActualHour() {
    Calendar lastMomentActualHour = (new Date()).toCalendar()
    lastMomentActualHour.set(minute: 59, second: 59)
    lastMomentActualHour.getTime()
  }
}






