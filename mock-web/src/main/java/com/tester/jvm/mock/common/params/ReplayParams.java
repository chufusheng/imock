package com.tester.jvm.mock.common.params;

import lombok.*;

/**
 * {@link ReplayParams}
 * <p>
 *
 * @author fusheng.chu
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplayParams extends BaseParams {

    private String ip;

    private String repeatId;

    private String port;

    private boolean mock;

}
