angular.module('electrometro.services').factory 'persistence', () ->

  ok = (value) ->
    console.log 'preferences set operation ' + value

  ko = (error) ->
    console.log 'preferences set operation failed with error: ' + error

  setPreference: (key, value) ->
    plugins.appPreferences.store ok, ko, key, value if not (typeof plugins == 'undefined')

  getPreference: (key, callback) ->
    plugins.appPreferences.fetch callback, ko, key if not (typeof plugins == 'undefined')


