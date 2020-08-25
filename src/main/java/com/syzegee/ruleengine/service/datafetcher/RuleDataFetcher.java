package com.syzegee.ruleengine.service.datafetcher;

import com.syzegee.ruleengine.service.adopter.RuleEngineConnector;
import com.syzegee.ruleengine.service.entity.SzRule;
import com.syzegee.ruleengine.service.entity.SzRuleDetails;
import com.syzegee.ruleengine.service.response.Benefit;
import com.syzegee.ruleengine.service.response.PlanDetails;
import com.syzegee.ruleengine.service.response.RunRuleResponse;
import com.syzegee.ruleengine.service.response.Vendor;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RuleDataFetcher implements DataFetcher<SzRule> {

    private RuleEngineConnector ruleEngineConnector;
    @Autowired
    public RuleDataFetcher(RuleEngineConnector ruleEngineConnector) {
        this.ruleEngineConnector = ruleEngineConnector;
    }

    @Override
    public SzRule get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("name");
        System.out.println("==== "+data);
        return ruleEngineConnector.getRuleByRuleName(data);
    }
}
