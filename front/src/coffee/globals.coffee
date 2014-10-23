angular.module('electrometro').config ($rootScope) ->

$rootScope.jsonServerProtocol = 'http'
$rootScope.jsonServerHost = 'electrometro.es'
$rootScope.jsonServerPort = '8080'
$rootScope.jsonServerUrl = $rootScope.jsonServerProtocol + '://' + $rootScope.jsonServerHost + ':' + $rootScope.jsonServerPort + '/'












