angular.module('electrometro.services.remote').factory 'coloredZones', ($http) ->

  isThereAFutureGreenZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'isThereAFutureGreenZoneToday').then (result) ->
      result.data

  isThereAFutureYellowZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'isThereAFutureYellowZoneToday').then (result) ->
      result.data

  isThereAFutureRedZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'isThereAFutureRedZoneToday').then (result) ->
      result.data
