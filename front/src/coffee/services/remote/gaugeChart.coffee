angular.module('electrometro.services.remote').factory 'gaugeChart', ($http) ->

  json: ->
    $http.get($rootScope.jsonServerUrl + 'gaugeChartJSON').then (result) ->
      result.data
