angular.module('electrometro.services.remote').factory 'linesChart', ($http) ->
  json: ->
    $http.get('http://electrometro.es:8080/linesChartJSON').then (result) ->
      result.data