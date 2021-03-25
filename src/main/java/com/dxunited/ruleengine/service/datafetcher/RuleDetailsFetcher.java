package com.dxunited.ruleengine.service.datafetcher;

import com.dxunited.ruleengine.service.adopter.RuleEngineConnector;
import com.dxunited.ruleengine.service.entity.SzRule;
import com.dxunited.ruleengine.service.entity.SzRuleDetails;
import com.dxunited.ruleengine.service.response.Benefit;
import com.dxunited.ruleengine.service.response.PlanDetails;
import com.dxunited.ruleengine.service.response.RunRuleResponse;
import com.dxunited.ruleengine.service.response.Vendor;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Riya Patel
 */
@Component
public class RuleDetailsFetcher implements DataFetcher<RunRuleResponse> {

    private RuleEngineConnector ruleEngineConnector;

    public RuleDetailsFetcher(RuleEngineConnector ruleEngineConnector) {
        this.ruleEngineConnector = ruleEngineConnector;
    }

    @Override
    public RunRuleResponse get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("name");
        System.out.println("==== "+data);
        SzRule szRule = getRule(data);
        RunRuleResponse runRuleResponse = new RunRuleResponse();
        runRuleResponse.setPlatformName(data);   //masterplans
        runRuleResponse.setRuleDesc(szRule.getRuleDesc());
        List<PlanDetails> planDetails = new ArrayList<>();
        for(SzRuleDetails szRuleDetails :szRule.getSzRuleDetailsCollection()){
            PlanDetails planDetail = new PlanDetails();
            planDetail.setPlanName(szRuleDetails.getRuleDetailValue());              // planName=Basics
            SzRule  benefits = getRule(szRuleDetails.getRuleDetailValue()); //call Basic and get child like bList and vList
            planDetail.setPlanDesc(benefits.getRuleDesc());
            List<Benefit> listOfBenefits = new ArrayList<>();
            List<Vendor>  listOfVendors = new ArrayList<>();
            Benefit benefit = new Benefit();
            List<String> vlist = Arrays.asList(benefits.getSzRuleDetailsCollection().stream().filter(b -> "vList".equals(b.getRuleDetailCode())).map(SzRuleDetails::getRuleDetailValue).collect(Collectors.toList()).get(0).split(","));
            List<String> listOfAttributes = new ArrayList<>();
            for(SzRuleDetails benefitAndVendorDetails : benefits.getSzRuleDetailsCollection()){ //bList and vList
                if("bList".equals(benefitAndVendorDetails.getRuleDetailCode())){
                    listOfBenefits = createBenefitsList(benefitAndVendorDetails,vlist);

                } else if("attributes".equals(benefitAndVendorDetails.getRuleDetailCode())){
                        listOfAttributes.add(benefitAndVendorDetails.getRuleDetailValue());
                }
                planDetail.setBenefits(listOfBenefits);
                planDetail.setAttributes(listOfAttributes);
            }
            planDetails.add(planDetail);

        }
        runRuleResponse.setPlanDetails(planDetails);
        return runRuleResponse;
    }
    private SzRule getRule(String ruleName){
        return ruleEngineConnector.getRuleByRuleName(ruleName);
    }
    private List<Benefit> createBenefitsList(SzRuleDetails benefitAndVendorDetails, List<String> vlist){
        Benefit benefit;
        List<Vendor>  listOfVendors = new ArrayList<>();
        List<Benefit> listOfBenefits = new ArrayList<>();
        List<String> benefitList =  Arrays.asList(benefitAndVendorDetails.getRuleDetailValue().split(","));
        for(String benefitRuleName :benefitList) {
            benefit = new Benefit();
            benefit.setBenefitName(benefitRuleName);                                              // benefitName =Shopping
            SzRule listOfBenefitDetails = getRule(benefitRuleName); //call bList
            for(SzRuleDetails benefitAttributes : listOfBenefitDetails.getSzRuleDetailsCollection()) {
                if("benefitIcon".equals(benefitAttributes.getRuleDetailCode())){
                    benefit.setBenefitIcon(benefitAttributes.getRuleDetailValue());               //benefitlcon
                }else if("benefitRedirectUrl".equals(benefitAttributes.getRuleDetailCode())){
                    benefit.setBenefitRedirectUrl(benefitAttributes.getRuleDetailValue());        //benefitRedirectUrl
                } else if("vendorList".equals(benefitAttributes.getRuleDetailCode())){
                    benefit.setVendorList(createVendorListForBenefit(benefitAttributes,vlist));
                }
            }
            listOfBenefits.add(benefit); //add benefit object
        }
        return listOfBenefits;
    }
    private List<Vendor> createVendorListForBenefit(SzRuleDetails benefitAttributes,List<String> vlist){
        List<Vendor>  listOfVendors = new ArrayList<>();
        List<String> benVendorList =  Arrays.asList(benefitAttributes.getRuleDetailValue().split(","));
        List<String> commonVendorList =  vlist.stream().filter(v -> benVendorList.contains(v)).collect(Collectors.toList());
        commonVendorList.forEach(c -> {
            Vendor vendor = new Vendor();
            SzRule vendorAttributes = getRule(c);
            vendor.setVendorName(c);
            vendor.setVendorId(String.valueOf(vendorAttributes.getRuleId()));
            vendorAttributes.getSzRuleDetailsCollection().forEach(vDetails -> {
                if("vendorIcon".equals(vDetails.getRuleDetailCode())){
                    vendor.setVendorIcon(vDetails.getRuleDetailValue());
                } else if("vendorDesc".equals(vDetails.getRuleDetailCode())){
                    vendor.setVendorDesc(vDetails.getRuleDetailValue());
                }
            });
            listOfVendors.add(vendor);

        });
        return listOfVendors;
    }
}

