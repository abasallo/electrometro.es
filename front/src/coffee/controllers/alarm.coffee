angular.module('electrometro.controllers').controller 'AlarmController', ($rootScope, $scope, alarmHelper, alarmCommand, earliestHourForFuture, alarmService, coloredZones) ->

  alarmCommand $scope

  alarmService.isSet (isSet) -> $scope.alarmCommand.isThereAnAlarmSet = isSet

  alarmService.getHour (hour) ->
    $scope.alarmCommand.alarmSetHour = hour
    console.log 'getPreference ' + hour

  coloredZones.isThereAFutureGreenZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isGreenZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value

  coloredZones.isThereAFutureYellowZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isYellowZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value

  coloredZones.isThereAFutureRedZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isRedZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value

  alarmHelper.initializeDailyPrices $scope.alarmCommand
