#!/usr/bin/env bash

# mock's target dir
MOCK_TARGET_DIR=../target/mock

# exit shell with err_code
# $1 : err_code
# $2 : err_msg
exit_on_err() {
  [[ ! -z "${2}" ]] && echo "${2}" 1>&2
  exit ${1}
}

echo "======              begin to package              ======"

# package
mvn clean package -Dmaven.test.skip=true -f ../pom.xml || exit_on_err 1 "package mock failed."

mkdir -p ${MOCK_TARGET_DIR}
mkdir -p ${MOCK_TARGET_DIR}/cfg

echo "======            cp package to target            ======"

cp ./mock-logback.xml ${MOCK_TARGET_DIR}/cfg/mock-logback.xml &&
  cp ./mock.properties ${MOCK_TARGET_DIR}/cfg/mock.properties &&
  cp ../mock-module/target/mock-module-*-jar-with-dependencies.jar ${MOCK_TARGET_DIR}/mock-module.jar &&
  cp ../mock-web/target/mock-web-*.jar ${MOCK_TARGET_DIR}/mock-web.jar

# extract sandbox to ${HOME}
curl -s curl -s https://kunchu.oss-cn-beijing.aliyuncs.com/sandbox-1.2.1-bin.tar | tar xz -C ${HOME} || exit_on_err 1 "extract sandbox failed" | tar xz -C ${HOME} || exit_on_err 1 "extract sandbox failed"
echo "======        cp package to .sandbox-module       ======"

# copy module to ~/.sandbox-module
mkdir -p ${HOME}/.sandbox-module || exit_on_err 1 "permission denied, can not mkdir ~/.sandbox-module"
cp -r ${MOCK_TARGET_DIR}/* ${HOME}/.sandbox-module || exit_on_err 1 "permission denied, can not copy module to ~/.sandbox-module"

echo "======                  success                   ======"
