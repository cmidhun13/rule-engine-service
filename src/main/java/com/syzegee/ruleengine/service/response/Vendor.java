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
public class Vendor {
    private String VendorId;
    private String vendorName;
    private String vendorDesc;
    private String vendorIcon;
}
