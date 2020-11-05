#!/usr/bin/env bash

# mock's target dir
MOCK_PACKAGE_DIR=package

# exit shell with err_code
# $1 : err_code
# $2 : err_msg
exit_on_err() {
  [[ ! -z "${2}" ]] && echo "${2}" 1>&2
  exit ${1}
}

# maven package the sandbox
mvn clean package -Dmaven.test.skip=true -f ../pom.xml || exit_on_err 1 "package mock failed."

mkdir -p ${MOCK_PACKAGE_DIR}
mkdir -p ${MOCK_PACKAGE_DIR}/cfg

cp mock-logback.xml package/cfg/mock-logback.xml &&
  cp mock.properties package/cfg/mock.properties &&
  cp ../mock-module/target/mock-module-*-jar-with-dependencies.jar package/mock-module.jar &&
  cp ../mock-web/target/mock-web-*.jar package/mock-web.jar

# tar
chmod -R 777 package && cd package
tar -cvf mock-core-bin.tar cfg mock-module.jar mock-web.jar
