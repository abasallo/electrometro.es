angular.module('electrometro.services.remote').factory 'dailyPrices', ($http) ->

  minimum: ->
    $http.get($rootScope.jsonServerUrl + 'todayMinimumPrice').then (result) ->
      result.data

  futureMinimum: ->
    $http.get($rootScope.jsonServerUrl + 'todayMinimumFuturePrice').then (result) ->
      result.data

  average: ->
    $http.get($rootScope.jsonServerUrl + 'todayAveragePrice').then (result) ->
      result.data


  maximum: ->
    $http.get($rootScope.jsonServerUrl + 'todayMaximumPrice').then (result) ->
      result.data

  actual: ->
    $http.get($rootScope.jsonServerUrl + 'actualPrice').then (result) ->
      result.data
