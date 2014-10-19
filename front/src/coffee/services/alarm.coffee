angular.module('electrometro.services').factory 'alarmService', ($translate, $window, persistence) ->

  isSet: (callback) ->
    $window.plugin.notification.local.isScheduled '1', callback, this if $window.plugin

  getHour: (callback) ->
    persistence.getPreference 'alarmHour', callback

  setTo: (hour) ->
    if $window.plugin
      now = new Date()
      $window.plugin.notification.local.add
        id: '1'
        date: new Date(now.getFullYear(), now.getMonth(), now.getDate(), hour, 0, 0)
        message: $translate.instant 'ALARM_NOTIFICATION_MESSAGE'
        title: 'Electrómetro'
        badge: 1
        icon: 'notification'
        autoCancel: true
      persistence.setPreference 'alarmHour', hour

  cancel: () ->
    $window.plugin.notification.local.cancel '1' if $window.plugin



