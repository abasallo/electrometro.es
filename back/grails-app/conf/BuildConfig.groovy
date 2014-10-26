grails.servlet.version = '3.0'
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.work.dir = 'target/work'
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = 'ROOT.war'

grails.project.fork = [
    test: false,
    run: false,
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {

  inherits('global') {}
  log 'error'
  checksums true
  legacyResolve false

  repositories {
    grailsPlugins()
    grailsHome()
    mavenLocal()
    grailsCentral()
    mavenCentral()
  }

  dependencies {
    compile 'org.ccil.cowan.tagsoup:tagsoup:1.2.1'

    runtime 'mysql:mysql-connector-java:5.1.31'
  }

  plugins {
    build ':tomcat:7.0.52.1'

    compile ':scaffolding:2.1.0'
    compile ':cache:1.1.3'
    compile ':asset-pipeline:1.8.3'
    compile ':quartz:1.0.1'
    compile ':mail:1.0.6'

    runtime ':hibernate4:4.3.5.2'
    runtime ':database-migration:1.4.0'
    runtime ':jquery:1.11.0.2'
  }
}










