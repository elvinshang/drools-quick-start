package com.yonyoucloud.drools.fact;

import lombok.*;

/**
 * 规则执行结果
 *
 * @author elvinshang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleExecutionResult {
    @Builder.Default
    private String code = "0";
    @Builder.Default
    private boolean success = true;
    private int count;
    private String message;
    private Object data;
}
