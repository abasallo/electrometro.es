angular.module('electrometro.services.remote').factory 'earliestHourForFuture', ($http) ->

  greenZoneToday: ->
    $http.get('http://electrometro.es:8080/earliestHourForFutureGreenZoneToday').then (result) ->
      result.data.value


  yellowZoneToday: ->
    $http.get('http://electrometro.es:8080/earliestHourForFutureYellowZoneToday').then (result) ->
      result.data.value


  redZoneToday: ->
    $http.get('http://electrometro.es:8080/earliestHourForFutureRedZoneToday').then (result) ->
      result.data.value


  priceUnder: (price) ->
    $http.get('http://electrometro.es:8080/earliestHourTodayForFuturePriceUnder?price=' + price).then (result) ->
      result.data.value