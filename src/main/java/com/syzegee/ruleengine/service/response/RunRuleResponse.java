package com.syzegee.ruleengine.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Riya Patel
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunRuleResponse {
    private String PlatformName;
    private String ruleDesc;
    private List<PlanDetails> PlanDetails;

}
