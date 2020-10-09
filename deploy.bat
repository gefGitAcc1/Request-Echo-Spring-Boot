set JAVA_HOME=c:\Java\Jdk\jdk-11.0.8\
set PATH=%PATH%;c:\Java\Jdk\jdk-11.0.8\bin

call mvn clean install

call gcloud app deploy --project echo-291906

pause