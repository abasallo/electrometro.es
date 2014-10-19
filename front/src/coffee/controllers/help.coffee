angular.module('electrometro.controllers').controller 'HelpController', ($rootScope, $scope, $translate, helpCommand) ->

  helpCommand $scope
  
  if $rootScope.previousState is 'tab.home'
    $scope.helpCommand.fromHome = true
    $scope.helpCommand.helpTitle = $translate.instant 'HELP_HOME_TITLE'
  else if $rootScope.previousState is 'tab.alarm'
    $scope.helpCommand.fromAlarm = true
    $scope.helpCommand.helpTitle = $translate.instant 'HELP_ALARM_TITLE'
  else
    $scope.helpCommand.fromHome = true
    $scope.helpCommand.fromAlarm = true
    $scope.helpCommand.helpTitle = $translate.instant 'HELP_GENERAL_TITLE'


