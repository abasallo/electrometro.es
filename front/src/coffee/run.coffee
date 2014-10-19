angular.module('electrometro').run(($rootScope, $ionicPlatform, $ionicLoading, $ionicModal) ->

  $ionicPlatform.ready ->

    deviceEvents =
      initialize: ->
        @bindEvents()
        console.log 'Device events initialized.'
        $ionicModal.fromTemplateUrl('views/modal/connectivity.html',
          scope: $rootScope
          animation: 'slide-in-up'
        ).then (modal) ->
          $rootScope.modalNetwork = modal
          console.log 'No network Ionic modal initialized.'

      bindEvents: ->
        document.addEventListener 'online', @onConnected, false
        document.addEventListener 'offline', @onDisconnected, false

      onConnected: ->
        $rootScope.modalNetwork.hide()
        $window.location.reload()

      onDisconnected: ->
        $rootScope.modalNetwork.show()

    $ionicPlatform.onHardwareBackButton ->
      navigator.app.exitApp()

    $rootScope.$on '$stateChangeSuccess', (event, toState, toParams, fromState, fromParams) ->
      $rootScope.previousState = fromState.name

    $rootScope.loadingModalCounter = 0

    $rootScope.$on 'loadingTaskStarted', ->
      $rootScope.loadingModalCounter++
      $ionicLoading.show templateUrl: 'views/modal/loading.html'

    $rootScope.$on 'loadingTaskFinished', ->
      $rootScope.loadingModalCounter--
      if not $rootScope.loadingModalCounter
        $ionicLoading.hide()

    deviceEvents.initialize()
)












