grails.project.groupId = jsonServer

grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

grails.views.default.codec = "html"

grails.controllers.defaultScope = 'singleton'

grails {
  views {
    gsp {
      encoding = 'UTF-8'
      htmlcodec = 'xml'
      codecs {
        expression = 'html'
        scriptlet = 'html'
        taglib = 'none'
        staticparts = 'none'
      }
    }
  }
}

grails.converters.encoding = "UTF-8"

grails.scaffolding.templates.domainSuffix = 'Instance'

grails.json.legacy.builder = false

grails.enable.native2ascii = true

grails.spring.bean.packages = []

grails.web.disable.multipart = false

grails.exceptionresolver.params.exclude = ['password']

grails.hibernate.cache.queries = false

environments {
  development {
    grails.logging.jul.usebridge = true
  }
  production {
    grails.logging.jul.usebridge = false
    grails.serverURL = "http://basallo.es"
  }
}

log4j.main = {
  error  'org.codehaus.groovy.grails.web.servlet',
      'org.codehaus.groovy.grails.web.pages',
      'org.codehaus.groovy.grails.web.sitemesh',
      'org.codehaus.groovy.grails.web.mapping.filter',
      'org.codehaus.groovy.grails.web.mapping',
      'org.codehaus.groovy.grails.commons',
      'org.codehaus.groovy.grails.plugins',
      'org.codehaus.groovy.grails.orm.hibernate',
      'org.springframework',
      'org.hibernate',
      'net.sf.ehcache.hibernate'
}

gaugeChart = [
    title: 'Ahora',
    titleFontColor: '#000000',
    label: 'céntimos por kWh',
    labelFontColor: '#000000',
    showMinMax: true,
    gaugeWidthScale: 1.1,
    levelColorsGradient: true,
    levelColors: ['#3b9637', '#eb8a1f', '#c51b22'],
]

linesChart = [
    behaveLikeLine: true,
    labels: ['Precio'],
    lineColors: ['#0066ff'],
    fillOpacity: 0.5,
    lineWidth: 0,
    pointSize: 2,
    smooth: true,
    hideHover: 'always',
    xLabels: 'hour',
    goalStrokeWidth: 3,
    goalLineColors: ['#eb8a1f'],
    eventStrokeWidth: 1,
    eventLineColors: ['#0c00ff']
]

grails {
    mail {
        host = 'smtp.gmail.com'
        port = 465
        props = ['mail.transport.protocol':'smtps', 'mail.smtps.auth':'true']
    }
}

grails.config.locations = [ SecretConfig ]

