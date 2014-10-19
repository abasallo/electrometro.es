gulp = require('gulp')
del = require('del')
gulpCoffee = require('gulp-coffee')
gulpSass = require('gulp-sass')
gulpMinifyCss = require('gulp-minify-css')
gulpRename = require('gulp-rename')
karma = require('karma').server


gulp.task 'clean', ->
  del ['www/js', 'www/css']

gulp.task 'clean-test', ->
  del ['test/js']

gulp.task 'coffee', ['clean'], ->
  gulp.src('src/coffee/**/*.coffee').pipe(gulpCoffee()).pipe gulp.dest('www/js')

gulp.task 'coffee-test', ['clean-test'], ->
  gulp.src('test/coffee/**/*.coffee').pipe(gulpCoffee()).pipe gulp.dest('test/js')

gulp.task 'sass', ['clean'], (done) ->
  gulp.src("scss/electrometro.app.scss").pipe(gulpSass()).pipe(gulpMinifyCss(keepSpecialComments: 0)).pipe(gulpRename(extname: ".min.css")).pipe(gulp.dest("www/css/"))

gulp.task 'res', ->
  gulp.src('www/res/android/**/*').pipe gulp.dest('platforms/android/res')
  gulp.src('www/res/ios/**/*').pipe gulp.dest('platforms/ios/electrometro.es/Resources')

gulp.task 'default', ['coffee', 'sass', 'res']


gulp.task 'test', ['coffee-test'], (done) ->
  karma.start
    configFile: __dirname + '/karma.conf.coffee'
    singleRun: true
    browsers: ['PhantomJS']
  , done

gulp.task 'tdd', ['coffee-test'], (done) ->
  karma.start
    configFile: __dirname + '/karma.conf.coffee'
    singleRun: false
    browsers: ['Chrome']
  , done


gulp.task 'watch', ['default'],  ->
  gulp.watch 'scss/**/*.scss', ['sass']
  gulp.watch 'src/**/*.coffee', ['coffee']
  gulp.watch 'test/**/*.coffee', ['coffee-test']
