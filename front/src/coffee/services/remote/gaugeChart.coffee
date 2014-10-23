angular.module('electrometro.services.remote').factory 'gaugeChart', ($http, $rootScope) ->

  json: ->
    $http.get($rootScope.jsonServerUrl + 'gaugeChartJSON').then (result) ->
      result.data
