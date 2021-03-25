package com.dxunited.ruleengine.service.response;

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
public class Benefit {
    private String benefitName;
    private String benefitIcon;
    private String benefitRedirectUrl;
    private List<Vendor> vendorList;
}
