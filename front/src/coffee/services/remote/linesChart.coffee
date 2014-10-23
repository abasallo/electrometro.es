angular.module('electrometro.services.remote').factory 'linesChart', ($http) ->

  json: ->
    $http.get($rootScope.jsonServerUrl + 'linesChartJSON').then (result) ->
      result.data
