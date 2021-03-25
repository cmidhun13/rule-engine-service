package com.dxunited.ruleengine.service.datafetcher;

import com.dxunited.ruleengine.service.adopter.RuleEngineConnector;
import com.dxunited.ruleengine.service.entity.SzRuleProject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleProjectDataFetcher implements DataFetcher<SzRuleProject> {


    private RuleEngineConnector ruleEngineConnector;

    @Autowired
    public RuleProjectDataFetcher(RuleEngineConnector ruleEngineConnector) {
        this.ruleEngineConnector=ruleEngineConnector;
    }

    @Override
    public SzRuleProject get(DataFetchingEnvironment environment) {
        String data = environment.getArgument("id");
        return  ruleEngineConnector.getRuleProjectByCustomerId(data);
    }


}
