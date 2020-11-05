package com.tester.jvm.mock.common.domain;

public class ResultHelper {

    public static <T> MockResult<T> copy(MockResult result) {
        MockResult<T> rr = MockResult.builder().build();
        rr.setSuccess(result.isSuccess());
        rr.setMessage(result.getMessage());
        return rr;
    }

    public static <T> MockResult<T> fail(String message) {
        return MockResult.builder().message(message).build();
    }

    public static <T> MockResult<T> fail() {
        return fail("operate failed");
    }


    public static <T> MockResult<T> success(String message, T t) {
        return MockResult.builder().message(message).success(true).data(t).build();
    }
    public static <T> MockResult<T> success(String message) {
        return success(message, null);
    }

    public static <T> MockResult<T> success() {
        return success("operate success", null);
    }

    public static <T> MockResult<T> success(T t) {
        return success("operate success", t);
    }

    public static MockResult<String> fs(boolean success) {
        return success ? success() : fail();
    }
}
