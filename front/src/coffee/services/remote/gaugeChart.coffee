angular.module('electrometro.services.remote').factory 'gaugeChart', ($http) ->
  json: ->
    $http.get('http://electrometro.es:8080/gaugeChartJSON').then (result) ->
      result.data