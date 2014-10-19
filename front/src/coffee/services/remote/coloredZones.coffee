angular.module('electrometro.services.remote').factory 'coloredZones', ($http) ->

  isThereAFutureGreenZoneToday: ->
    $http.get('http://electrometro.es:8080/isThereAFutureGreenZoneToday').then (result) ->
      result.data

  isThereAFutureYellowZoneToday: ->
    $http.get('http://electrometro.es:8080/isThereAFutureYellowZoneToday').then (result) ->
      result.data


  isThereAFutureRedZoneToday: ->
    $http.get('http://electrometro.es:8080/isThereAFutureRedZoneToday').then (result) ->
      result.data