angular.module('electrometro.services.remote').factory 'dailyPrices', ($http) ->

  minimum: ->
    $http.get('http://electrometro.es:8080/todayMinimumPrice').then (result) ->
      result.data

  futureMinimum: ->
    $http.get('http://electrometro.es:8080/todayMinimumFuturePrice').then (result) ->
      result.data

  average: ->
    $http.get('http://electrometro.es:8080/todayAveragePrice').then (result) ->
      result.data


  maximum: ->
    $http.get('http://electrometro.es:8080/todayMaximumPrice').then (result) ->
      result.data


  actual: ->
    $http.get('http://electrometro.es:8080/actualPrice').then (result) ->
      result.data