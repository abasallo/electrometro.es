angular.module('electrometro.controllers').factory 'alarmCommand', (alarmHelper, earliestHourForFuture) ->

  ($scope) ->
    $scope.alarmCommand = {

      isThereAnAlarmSet: false
      alarmSetHour: undefined

      priceMinimumValue: '-'
      priceAverageValue: '-'
      priceMaximumValue: '-'
      priceActualValue: '-'

      isGreenZoneButtonDisabled: true
      isYellowZoneButtonDisabled: true
      isRedZoneButtonDisabled: true

      priceMinimumValue: undefined
      priceFutureMinimumValue: undefined
      priceAverageValue: undefined
      priceMaximumValue: undefined
      priceActualValue: undefined

      alarmOnGreenZone: ->
        earliestHourForFuture.greenZoneToday().then (hour) ->
          alarmHelper.setTo $scope.alarmCommand, hour

      alarmOnYellowZone: ->
        earliestHourForFuture.yellowZoneToday().then (hour) ->
          alarmHelper.setTo $scope.alarmCommand, hour

      alarmOnRedZone: ->
        earliestHourForFuture.redZoneToday().then (hour) ->
          alarmHelper.setTo $scope.alarmCommand, hour

      alarmOnGivenPrice: ->
        earliestHourForFuture.priceUnder($scope.alarmCommand.priceActualValue).then (hour) ->
          alarmHelper.setTo $scope.alarmCommand, hour

      cancelAlarm: ->
        alarmHelper.cancel $scope.alarmCommand
    }