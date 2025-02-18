run:
	/home/alius/.jdks/corretto-17.0.14/bin/java \
	-Dfile.encoding=UTF-8 \
	-classpath out/production/biblioteka:libs/jackson-annotations-2.18.2.jar:libs/jackson-core-2.18.2.jar:libs/jackson-databind-2.18.2.jar \
	lt.alius.library.Main
