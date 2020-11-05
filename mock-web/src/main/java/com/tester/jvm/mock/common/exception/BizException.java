package com.tester.jvm.mock.common.exception;

/**
 * {@link BizException}
 * <p>
 *
 * @author fusheng.chu
 */
public class BizException extends Exception {

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
