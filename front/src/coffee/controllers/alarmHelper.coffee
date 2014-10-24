angular.module('electrometro.controllers').factory 'alarmHelper', ($rootScope, $translate, dailyPrices, $ionicPopup, alarmService) ->

  alarmPopupTitle = $translate.instant 'ALARM_POPUP_TITLE'
  alarmPopupTemplate = $translate.instant 'ALARM_POPUP_TEMPLATE'

  initializeDailyPrices: (command) ->
    dailyPrices.minimum().then (price) ->
      command.priceMinimumValue = price.value

    dailyPrices.futureMinimum().then (price) ->
      command.priceFutureMinimumValue = price.value

    dailyPrices.average().then (price) ->
      command.priceAverageValue = price.value

    dailyPrices.maximum().then (price) ->
      command.priceMaximumValue = price.value

    dailyPrices.actual().then (price) ->
      command.priceActualValue = price.value

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



