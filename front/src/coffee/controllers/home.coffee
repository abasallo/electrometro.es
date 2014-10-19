angular.module('electrometro.controllers').controller 'HomeController', ($rootScope, $translate, gaugeChart, linesChart) ->

  $rootScope.$emit 'loadingTaskStarted'
  gaugeChart.json().then (json) ->
    json.title = $translate.instant 'HOME_GAUGE_TITLE'
    json.label = $translate.instant 'HOME_GAUGE_LABEL'
    json.id = "gauge_chart"
    $rootScope.$emit 'loadingTaskFinished'
    new JustGage(json)

  $rootScope.$emit 'loadingTaskStarted'
  linesChart.json().then (json) ->
    json.element = "lines_chart"
    json.yLabelFormat = (y) ->
      (Math.round(y * 10) / 10).toString()
    $rootScope.$emit 'loadingTaskFinished'
    new Morris.Area(json)
