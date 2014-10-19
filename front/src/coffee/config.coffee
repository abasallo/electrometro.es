angular.module('electrometro').config ($stateProvider, $urlRouterProvider) ->

    $stateProvider.state('tab',
      url: "/tab",
      abstract: true,
      templateUrl: "views/tabs.html"
    )

    $stateProvider.state('tab.home',
      url: '/home'
      views:
        'tab-home':
          templateUrl: 'views/home.html'
          controller: 'HomeController'
    )

    $stateProvider.state('tab.alarm',
      url: '/alarm'
      views:
        'tab-alarm':
          templateUrl: 'views/alarm.html'
          controller: 'AlarmController'
    )

    $stateProvider.state('tab.help',
      url: '/help'
      views:
        'tab-help':
          templateUrl: 'views/help.html'
          controller: 'HelpController'
    )

    $urlRouterProvider.otherwise '/tab/home'












