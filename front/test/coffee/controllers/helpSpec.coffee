describe 'HelpController', ->

  rootScope =
    previousState: undefined
  scope = helpCommand:
    fromHome: undefined
    fromAlarm: undefined
    helpTitle: undefined
  translate =
    instant: (text) -> text
  _helpCommand_ = jasmine.createSpy 'helpCommand'

  createController = undefined

  beforeEach  ->
    module 'electrometro.controllers'
    inject ($controller) ->
      createController = ->
        $controller "HelpController", $rootScope: rootScope, $scope: scope, $translate: translate, helpCommand: _helpCommand_

  it 'Should set appropiate var to true when previous tab is home', ->

    rootScope.previousState = 'tab.home'

    createController()

    expect(_helpCommand_).toHaveBeenCalledWith(scope)
    expect(scope.helpCommand.fromHome).toBe(true)
    expect(scope.helpCommand.helpTitle).toBe('HELP_HOME_TITLE')

  it 'Should set appropiate var to true when previous tab is alarm', ->

    rootScope.previousState = 'tab.alarm'

    createController()

    expect(_helpCommand_).toHaveBeenCalledWith(scope)
    expect(scope.helpCommand.fromAlarm).toBe(true)
    expect(scope.helpCommand.helpTitle).toBe('HELP_ALARM_TITLE')

  it 'Should set appropiate var to true when previous tab is alarm', ->

    rootScope.previousState = 'tab.other'

    createController()

    expect(_helpCommand_).toHaveBeenCalledWith(scope)
    expect(scope.helpCommand.fromHome).toBe(true)
    expect(scope.helpCommand.fromAlarm).toBe(true)
    expect(scope.helpCommand.helpTitle).toBe('HELP_GENERAL_TITLE')
