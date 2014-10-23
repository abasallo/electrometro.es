angular.module('electrometro.services.remote').factory 'earliestHourForFuture', ($http) ->

  greenZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'earliestHourForFutureGreenZoneToday').then (result) ->
      result.data.value


  yellowZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'earliestHourForFutureYellowZoneToday').then (result) ->
      result.data.value


  redZoneToday: ->
    $http.get($rootScope.jsonServerUrl + 'earliestHourForFutureRedZoneToday').then (result) ->
      result.data.value

  priceUnder: (price) ->
    $http.get($rootScope.jsonServerUrl + 'earliestHourTodayForFuturePriceUnder?price=' + price).then (result) ->
      result.data.value
