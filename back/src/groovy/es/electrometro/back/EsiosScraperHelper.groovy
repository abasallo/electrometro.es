package es.electrometro.back

import org.ccil.cowan.tagsoup.Parser

import java.text.NumberFormat
import java.text.ParseException

class EsiosScraperHelper {

  static tagsoupParser = new Parser()
  static slurper = new XmlSlurper(tagsoupParser)
  static locale = new Locale('es', 'ES')
  static numberFormat = NumberFormat.getInstance(locale);

  static final protocol = 'http'
  static final host = 'www.esios.ree.es'
  static final FEES_LIST_LATEST_AVAILABLE_DATE_ADITIONAL_PARAM = 'NOMBRE=PVPC_DD'
  static final FEES_LIST_TARGET_FILE_TYPE = 'html'
  static final ESIOS_QUERY_TYPE_REQUEST = 'Solicitar'
  static final ESIOS_QUERY_TYPE_LIST = 'Listar'
  static final ESIOS_QUERY_TARGET_FEES_LIST = 'PVPC_DD_MW_'
  static final ESIOS_QUERY_TARGET_FEES_LIST_LATEST_AVAILABLE_DATE = 'FechaMaxima'
  static final ESIOS_QUERY_LANGUAGE_ES = 'es'
  static final ESIOS_DATE_FORMAT = 'dd/MM/yyyy'

  static getParsedFeesList(Date date) throws ParseException {
    def parsedFeesList = []
    getFeesList(date).each { fee ->
      def parsedFee = []
      fee.each { price ->
        parsedFee << numberFormat.parse(price)
      }
      parsedFeesList << parsedFee
    }
    parsedFeesList
  }

  static getParsedFeesListLatestAvailableDate() throws ParseException {
    Date.parse(ESIOS_DATE_FORMAT, getFeesListLatestAvailableDate())
  }

  static getFeesList(Date date) {

    def htmlParser = slurper.parse(buildFeesListUrl(date))
    def rows = htmlParser.body.table.tbody.tr

    def generalFee = []
    def nightFee = []
    def eCarFee = []

    rows.each { row ->
      generalFee << row.td[1].text()
      nightFee << row.td[2].text()
      eCarFee << row.td[3].text()
    }

    generalFee.remove(generalFee.last())
    nightFee.remove(nightFee.last())
    eCarFee.remove(eCarFee.last())

    [generalFee, nightFee, eCarFee]
  }

  static String getFeesListLatestAvailableDate() {

    def htmlParser = slurper.parse(buildFeesListLatestAvailableDateUrl())

    htmlParser.elemento.fecha.text()
  }

  static buildFeesListUrl(Date date) {
    buildBaseEsiosQueryUrl(ESIOS_QUERY_TYPE_REQUEST, ESIOS_QUERY_TARGET_FEES_LIST) + buildEsiosUrlDate(date) + '.' + FEES_LIST_TARGET_FILE_TYPE + '&' + ESIOS_QUERY_LANGUAGE_ES
  }

  static buildFeesListLatestAvailableDateUrl() {
    buildBaseEsiosQueryUrl(ESIOS_QUERY_TYPE_LIST, ESIOS_QUERY_TARGET_FEES_LIST_LATEST_AVAILABLE_DATE) + '?' + FEES_LIST_LATEST_AVAILABLE_DATE_ADITIONAL_PARAM
  }

  static buildBaseEsiosQueryUrl(String type, String target) {
    protocol + '://' + host + '/' + type + '/' + target
  }

  // TODO - Possible duplicated code with ChartsJSONHelper.generateLinesJSON
  static buildEsiosUrlDate(Date date) {

    def calendar = date.toCalendar()

    String year = calendar.get(Calendar.YEAR)
    String month = calendar.get(Calendar.MONTH) + 1
    String day = calendar.get(Calendar.DAY_OF_MONTH)

    year + (month.size() == 2 ? month : '0' + month) + (day.size() == 2 ? day : '0' + day)
  }

}

