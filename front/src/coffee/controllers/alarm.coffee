angular.module('electrometro.controllers').controller 'AlarmController', ($rootScope, $scope, alarmHelper, alarmCommand, earliestHourForFuture, alarmService, coloredZones) ->

  alarmCommand $scope

  alarmService.isSet (isSet) -> $scope.alarmCommand.isThereAnAlarmSet = isSet

  alarmService.getHour (hour) -> $scope.alarmCommand.alarmSetHour = hour

  $rootScope.$emit 'loadingTaskStarted'
  coloredZones.isThereAFutureGreenZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isGreenZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value
    $rootScope.$emit 'loadingTaskFinished'

  $rootScope.$emit 'loadingTaskStarted'
  coloredZones.isThereAFutureYellowZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isYellowZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value
    $rootScope.$emit 'loadingTaskFinished'

  $rootScope.$emit 'loadingTaskStarted'
  coloredZones.isThereAFutureRedZoneToday().then (isThereAFuturePriceInPriceInZone) ->
    $scope.alarmCommand.isRedZoneButtonDisabled = not isThereAFuturePriceInPriceInZone.value
    $rootScope.$emit 'loadingTaskFinished'

  alarmHelper.initializeDailyPrices $scope.alarmCommand