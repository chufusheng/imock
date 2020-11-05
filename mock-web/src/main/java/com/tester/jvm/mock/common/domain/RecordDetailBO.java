package com.tester.jvm.mock.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * {@link RecordDetailBO}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
@Setter
@ToString
public class RecordDetailBO extends RecordBO {

    private String request;

    private String response;

    private String subInvocations;
}
