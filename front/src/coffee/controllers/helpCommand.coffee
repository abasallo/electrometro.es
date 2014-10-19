angular.module('electrometro.controllers').factory 'helpCommand', () ->

  ($scope) ->
    $scope.helpCommand = {
      fromHome: undefined
      fromAlarm: undefined
      helpTitle: undefined
    }
