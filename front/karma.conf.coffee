module.exports = (config) ->
  config.set
    basePath: ''
    frameworks: ['jasmine']
    files: [
      'www/lib/ionic/js/ionic.bundle.js'
      'www/lib/angular-translate/angular-translate.min.js'
      'www/lib/jquery/dist/jquery.js'
      'www/lib/justgage-official/justgage.js'
      'www/lib/morris.js/morris.min.js'
      'www/lib/raphael/raphael-min.js'

      'www/lib/angular-mocks/angular-mocks.js'

      'www/js/modules.js'
      'www/js/config.js'
      'www/js/i18n.js'
      'www/js/run.js'

      'www/js/services/remote/coloredZones.js'
      'www/js/services/remote/dailyPrices.js'
      'www/js/services/remote/earliestHourForFuture.js'
      'www/js/services/remote/gaugeChart.js'
      'www/js/services/remote/linesChart.js'
      'www/js/services/alarm.js'
      'www/js/services/persistence.js'

      'www/js/controllers/alarm.js'
      'www/js/controllers/alarmHelper.js'
      'www/js/controllers/help.js'
      'www/js/controllers/home.js'

      'test/js/**/*.js'
    ]
    reporters: ['progress']
    port: 9876
    colors: true
    logLevel: config.LOG_INFO
    singleRun: true
    browsers: ['Chrome']







