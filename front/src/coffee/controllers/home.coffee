angular.module('electrometro.controllers').controller 'HomeController', ($rootScope, $translate, gaugeChart, linesChart) ->

  gaugeChart.json().then (json) ->
    json.title = $translate.instant 'HOME_GAUGE_TITLE'
    json.label = $translate.instant 'HOME_GAUGE_LABEL'
    json.id = "gauge_chart"
    new JustGage(json)

  linesChart.json().then (json) ->
    json.element = "lines_chart"
    json.yLabelFormat = (y) ->
      (Math.round(y * 10) / 10).toString()
    new Morris.Area(json)
