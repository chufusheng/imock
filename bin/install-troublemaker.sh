#!/usr/bin/env bash

typeset SANDBOX_HOME=${HOME}/sandbox

typeset MODULE_HOME=${HOME}/.sandbox-module

printf ${HOME}

# exit shell with err_code
# $1 : err_code
# $2 : err_msg
exit_on_err()
{
    [[ ! -z "${2}" ]] && echo "${2}" 1>&2
    exit ${1}
}

main(){
    echo "======  begin to install sandbox and troublemaker module       ======";
    echo "======  step 0 begin to download sandbox package           ======";
    curl -s http://sandbox-ecological.oss-cn-hangzhou.aliyuncs.com/sandbox-1.2.1-bin.tar | tar xz -C ${HOME} || exit_on_err 1 "extract sandbox failed"
    echo "======  step 1 begin to download mock module package   ======";
    if [ ! -d ${MODULE_HOME} ]; then
        mkdir -p ${MODULE_HOME} || exit_on_err 1 "permission denied mkdir ${MODULE_HOME}"
    fi
    curl -s 'https://kunchu.oss-cn-beijing.aliyuncs.com/mock-core-bin.tar?Expires=1604595416&OSSAccessKeyId=TMP.3KhaVyRM6E1wGCoCPAneRYPNBM1Gg2eoCkV8j3efxg52LoRwgQVnDnDRyBtcGwB8nEMZTcgrszPM8jBCFAmBB8uUHgWvJ4&Signature=Owlvviqh%2B4io3d822oeBozune1s%3D&versionId=CAEQCRiBgMC_sr3krBciIDlmY2FiMzY2ZDkyNzQ5OTk4N2VlNWY5ZTdlNzUzNmZk&response-content-type=application%2Foctet-stream' | tar xz -C ${MODULE_HOME} || exit_on_err 1 "extract troublemaker failed"
    echo "======                 install finished                    ======";
}

main