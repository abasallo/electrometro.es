angular.module('electrometro.services.remote').factory 'linesChart', ($http, $rootScope) ->

  json: ->
    $http.get($rootScope.jsonServerUrl + 'linesChartJSON').then (result) ->
      result.data
