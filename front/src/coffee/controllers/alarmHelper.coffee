angular.module('electrometro.controllers').factory 'alarmHelper', ($rootScope, $translate, dailyPrices, $ionicPopup, alarmService) ->

  alarmPopupTitle = $translate.instant 'ALARM_POPUP_TITLE'
  alarmPopupTemplate = $translate.instant 'ALARM_POPUP_TEMPLATE'

  initializeDailyPrices: (command) ->
    $rootScope.$emit 'loadingTaskStarted'
    dailyPrices.minimum().then (price) ->
      command.priceMinimumValue = price.value
      $rootScope.$emit 'loadingTaskFinished'

    $rootScope.$emit 'loadingTaskStarted'
    dailyPrices.futureMinimum().then (price) ->
      command.priceFutureMinimumValue = price.value
      $rootScope.$emit 'loadingTaskFinished'

    $rootScope.$emit 'loadingTaskStarted'
    dailyPrices.average().then (price) ->
      command.priceAverageValue = price.value
      $rootScope.$emit 'loadingTaskFinished'

    $rootScope.$emit 'loadingTaskStarted'
    dailyPrices.maximum().then (price) ->
      command.priceMaximumValue = price.value
      $rootScope.$emit 'loadingTaskFinished'

    $rootScope.$emit 'loadingTaskStarted'
    dailyPrices.actual().then (price) ->
      command.priceActualValue = price.value
      $rootScope.$emit 'loadingTaskFinished'

  setTo: (command, hour) ->
    $ionicPopup.confirm(
      title: '<i class="ion-ios7-alarm-outline popUpAlarmIcon"></i><span class="popUpAlarmTitle">' + alarmPopupTitle + hour + ':00</span>'
      template: '<p>' + alarmPopupTemplate + '</p>'
      okText: $translate.instant 'ALARM_POPUP_OK'
      okType: 'button-positive button-small'
      cancelText: $translate.instant 'ALARM_POPUP_CANCEL'
      cancelType: 'button-assertive button-small'
    ).then (okPressed) ->
      if (okPressed)
        alarmService.setTo hour
        command.isThereAnAlarmSet = true
        command.alarmSetHour = hour

  cancel: (command) ->
    alarmService.cancel()
    command.isThereAnAlarmSet = false
    command.alarmSetHour = undefined



